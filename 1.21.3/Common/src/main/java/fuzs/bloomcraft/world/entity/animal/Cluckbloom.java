package fuzs.bloomcraft.world.entity.animal;

import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.ai.goal.BlockTrailRandomStrollGoal;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Cluckbloom extends Chicken implements Shearable, VariantHolder<Holder<CluckbloomVariant>> {
    public static final EntityDataAccessor<Holder<CluckbloomVariant>> DATA_VARIANT = SynchedEntityData.defineId(
            Cluckbloom.class,
            ModRegistry.CLUCKBLOOM_VARIANT_ENTITY_DATA_SERIALIZER.value());

    @Nullable
    private UUID lastLightningBoltUUID;

    public Cluckbloom(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.removeAllGoals(WaterAvoidingRandomStrollGoal.class::isInstance);
        this.goalSelector.addGoal(5, new BlockTrailRandomStrollGoal(this, 1.0));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        Registry<CluckbloomVariant> registry = this.registryAccess()
                .lookupOrThrow(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY);
        builder.define(DATA_VARIANT, registry.getAny().orElseThrow());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.is(Items.SHEARS) && this.readyForShearing()) {
            if (this.level() instanceof ServerLevel serverLevel) {
                this.shear(serverLevel, SoundSource.PLAYERS, itemInHand);
                this.gameEvent(GameEvent.SHEAR, player);
                itemInHand.hurtAndBreak(1, player, getSlotForHand(hand));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void aiStep() {
        if (this.getVariant().value().layingLootTable().isEmpty()) {
            // prevent chicken from laying an egg
            this.eggTime = 6000;
        }
        super.aiStep();
    }

    @Override
    public boolean dropFromGiftLootTable(ServerLevel level, ResourceKey<LootTable> lootTable, BiConsumer<ServerLevel, ItemStack> dropConsumer) {
        return super.dropFromGiftLootTable(level,
                lootTable == BuiltInLootTables.CHICKEN_LAY ?
                        this.getVariant().value().layingLootTable().orElse(lootTable) : lootTable,
                dropConsumer);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        Holder<CluckbloomVariant> variant;
        if (spawnGroupData instanceof FlowerMobVariantUtil.VariantGroupData) {
            variant = ((FlowerMobVariantUtil.VariantGroupData<CluckbloomVariant>) spawnGroupData).variant;
        } else {
            Holder<Biome> biome = level.getBiome(this.blockPosition());
            variant = FlowerMobVariantUtil.getSpawnVariant(this.registryAccess()
                    .lookupOrThrow(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY), biome, level.getRandom());
            spawnGroupData = new FlowerMobVariantUtil.VariantGroupData<>(variant);
        }

        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            Registry<CluckbloomVariant> registry = this.registryAccess()
                    .lookupOrThrow(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY);
            int newIndex = (registry.getIdOrThrow(this.getVariant().value()) + 1) % registry.size();
            this.setVariant(registry.get(newIndex).orElseThrow(NoSuchElementException::new));
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public void shear(ServerLevel serverLevel, SoundSource soundSource, ItemStack shearsItemStack) {
        Optional<ResourceKey<LootTable>> optional = this.getVariant().value().shearingLootTable();
        if (optional.isPresent()) {
            serverLevel.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, soundSource, 1.0F, 1.0F);
            this.convertTo(EntityType.CHICKEN, ConversionParams.single(this, false, false), (Chicken chicken) -> {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                        this.getX(),
                        this.getY(0.5),
                        this.getZ(),
                        1,
                        0.0,
                        0.0,
                        0.0,
                        0.0);
                this.dropFromShearingLootTable(serverLevel,
                        optional.get(),
                        shearsItemStack,
                        (ServerLevel serverLevelX, ItemStack itemStackX) -> {
                            for (int i = 0; i < itemStackX.getCount(); i++) {
                                serverLevelX.addFreshEntity(new ItemEntity(serverLevelX,
                                        this.getX(),
                                        this.getY(1.0),
                                        this.getZ(),
                                        itemStackX.copyWithCount(1)));
                            }
                        });
            });
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void setVariant(Holder<CluckbloomVariant> variant) {
        this.entityData.set(DATA_VARIANT, variant);
    }

    @Override
    public Holder<CluckbloomVariant> getVariant() {
        return this.entityData.get(DATA_VARIANT);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        CluckbloomVariant.CODEC.encodeStart(NbtOps.INSTANCE, this.getVariant())
                .ifSuccess((Tag tagX) -> tag.put("variant", tagX));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        CluckbloomVariant.CODEC.parse(NbtOps.INSTANCE, tag.get("variant")).ifSuccess(this::setVariant);
    }

    @Nullable
    @Override
    public Cluckbloom getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Cluckbloom cluckbloom = (Cluckbloom) this.getType().create(level, EntitySpawnReason.BREEDING);
        if (cluckbloom != null) {
            cluckbloom.setVariant(this.getVariant());
        }

        return cluckbloom;
    }
}

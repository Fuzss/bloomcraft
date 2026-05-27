package fuzs.bloomcraft.world.entity.animal;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.ai.goal.BlockTrailRandomStrollGoal;
import fuzs.puzzleslib.api.util.v1.CompoundTagHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Moobloom extends Cow implements Shearable {
    public static final EntityDataAccessor<Holder<FlowerMobVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(
            Moobloom.class,
            ModRegistry.MOOBLOOM_VARIANT_ENTITY_DATA_SERIALIZER.value());

    @Nullable
    private UUID lastLightningBoltUUID;

    public Moobloom(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.removeAllGoals(WaterAvoidingRandomStrollGoal.class::isInstance);
        this.goalSelector.addGoal(5, new BlockTrailRandomStrollGoal(this, 1.0) {
            @Override
            protected BlockState getBlockState() {
                return ((Moobloom) this.mob).getFlowerVariant().value().blockState();
            }
        });
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        Registry<FlowerMobVariant> registry = this.registryAccess()
                .registryOrThrow(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY);
        builder.define(DATA_VARIANT_ID, registry.getAny().orElseThrow());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        // need both checks for clients, do not use age getter as it will never return zero
        if (itemInHand.is(Items.BOWL) && !this.isBaby() && this.age == 0) {
            Block block = this.getFlowerVariant().value().blockState().getBlock();
            if (block instanceof SuspiciousEffectHolder suspiciousEffectHolder) {

                if (this.level() instanceof ServerLevel serverLevel) {
                    SuspiciousStewEffects stewEffects = suspiciousEffectHolder.getSuspiciousEffects();
                    ItemStack itemStack = new ItemStack(Items.SUSPICIOUS_STEW);
                    itemStack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, stewEffects);

                    ItemStack newItemInHand = ItemUtils.createFilledResult(itemInHand, player, itemStack, false);
                    player.setItemInHand(interactionHand, newItemInHand);

                    // use this as a cooldown, it will tick down to zero again
                    this.setAge(6000);

                    for (int j = 0; j < 4; j++) {
                        serverLevel.addParticle(ParticleTypes.EFFECT,
                                this.getX() + this.random.nextDouble() / 2.0,
                                this.getY(0.5),
                                this.getZ() + this.random.nextDouble() / 2.0,
                                0.0,
                                this.random.nextDouble() / 5.0,
                                0.0);
                    }

                    this.playSound(SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY, 1.0F, 1.0F);
                }

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        } else if (itemInHand.is(Items.SHEARS) && this.readyForShearing()) {
            if (this.level() instanceof ServerLevel serverLevel) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player);
                itemInHand.hurtAndBreak(1, player, getSlotForHand(interactionHand));
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        Holder<FlowerMobVariant> variant;
        if (spawnGroupData instanceof FlowerMobVariantUtil.VariantGroupData variantGroupData) {
            variant = variantGroupData.variant;
        } else {
            Holder<Biome> biome = level.getBiome(this.blockPosition());
            variant = FlowerMobVariantUtil.getSpawnVariant(this.registryAccess()
                    .registryOrThrow(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY), biome, level.getRandom());
            spawnGroupData = new FlowerMobVariantUtil.VariantGroupData(variant);
        }

        this.setFlowerVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
        UUID uuid = lightningBolt.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            Registry<FlowerMobVariant> registry = this.registryAccess()
                    .registryOrThrow(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY);
            int newIndex = (registry.getIdOrThrow(this.getFlowerVariant().value()) + 1) % registry.size();
            this.setFlowerVariant(registry.getHolder(newIndex).orElseThrow(NoSuchElementException::new));
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public void shear(SoundSource soundSource) {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, soundSource, 1.0F, 1.0F);
            if (this.convertTo(EntityType.COW, false) != null) {
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
                        this.getFlowerVariant().value().shearingLootTable(),
                        (ServerLevel serverLevelX, ItemStack itemStackX) -> {
                            for (int i = 0; i < itemStackX.getCount(); i++) {
                                serverLevelX.addFreshEntity(new ItemEntity(serverLevelX,
                                        this.getX(),
                                        this.getY(1.0),
                                        this.getZ(),
                                        itemStackX.copyWithCount(1)));
                            }
                        });
            }
        }
    }

    /**
     * Copied from Minecraft 26.1.
     */
    protected void dropFromShearingLootTable(ServerLevel level, ResourceKey<LootTable> key, BiConsumer<ServerLevel, ItemStack> consumer) {
        this.dropFromLootTable(level,
                key,
                params -> params.withParameter(LootContextParams.ORIGIN, this.position())
                        .withParameter(LootContextParams.THIS_ENTITY, this)
                        .create(LootContextParamSets.SHEARING),
                consumer);
    }

    /**
     * Copied from Minecraft 26.1.
     */
    protected boolean dropFromLootTable(ServerLevel level, ResourceKey<LootTable> key, Function<LootParams.Builder, LootParams> paramsBuilder, BiConsumer<ServerLevel, ItemStack> consumer) {
        LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(key);
        LootParams params = paramsBuilder.apply(new LootParams.Builder(level));
        List<ItemStack> drops = lootTable.getRandomItems(params);
        if (!drops.isEmpty()) {
            drops.forEach(stack -> consumer.accept(level, stack));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    public void setFlowerVariant(Holder<FlowerMobVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    public Holder<FlowerMobVariant> getFlowerVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        RegistryOps<Tag> registryOps = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        CompoundTagHelper.store(valueOutput,
                Bloomcraft.id("variant").toString(),
                FlowerMobVariant.codec(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY),
                registryOps,
                this.getFlowerVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag valueInput) {
        super.readAdditionalSaveData(valueInput);
        RegistryOps<Tag> registryOps = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        CompoundTagHelper.read(valueInput,
                Bloomcraft.id("variant").toString(),
                FlowerMobVariant.codec(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY),
                registryOps).ifPresent(this::setFlowerVariant);
    }

    @Nullable
    @Override
    public Moobloom getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Moobloom moobloom = (Moobloom) this.getType().create(level);
        if (moobloom != null) {
            moobloom.setFlowerVariant(this.getFlowerVariant());
        }

        return moobloom;
    }
}

package fuzs.bloomcraft.world.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.bloomcraft.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

public record CluckbloomVariant(ResourceLocation textureLocation,
                                BlockState blockState,
                                Optional<ResourceKey<LootTable>> shearingLootTable,
                                Optional<ResourceKey<LootTable>> layingLootTable,
                                HolderSet<Biome> biomes) implements FlowerMobVariant {
    public static final Codec<CluckbloomVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(CluckbloomVariant::textureLocation),
            BlockState.CODEC.fieldOf("carried_block").forGetter(CluckbloomVariant::blockState),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .optionalFieldOf("shearing_loot_table")
                    .forGetter(CluckbloomVariant::shearingLootTable),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .optionalFieldOf("laying_loot_table")
                    .forGetter(CluckbloomVariant::layingLootTable),
            RegistryCodecs.homogeneousList(Registries.BIOME)
                    .optionalFieldOf("biomes", HolderSet.empty())
                    .forGetter(CluckbloomVariant::biomes)).apply(instance, CluckbloomVariant::new));
    public static final Codec<Holder<CluckbloomVariant>> CODEC = RegistryFileCodec.create(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
            DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, CluckbloomVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            CluckbloomVariant::textureLocation,
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
            CluckbloomVariant::blockState,
            ResourceKey.streamCodec(Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
            CluckbloomVariant::shearingLootTable,
            ResourceKey.streamCodec(Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
            CluckbloomVariant::layingLootTable,
            ByteBufCodecs.holderSet(Registries.BIOME),
            CluckbloomVariant::biomes,
            CluckbloomVariant::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CluckbloomVariant>> STREAM_CODEC = ByteBufCodecs.holder(
            ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
            DIRECT_STREAM_CODEC);

    public CluckbloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<CluckbloomVariant> resourceKey, Block block) {
        this(entityType, resourceKey, block, HolderSet.empty());
    }

    public CluckbloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<CluckbloomVariant> resourceKey, Block block, Holder<Biome> biome) {
        this(entityType, resourceKey, block, HolderSet.direct(biome));
    }

    public CluckbloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<CluckbloomVariant> resourceKey, Block block, HolderSet<Biome> biomes) {
        this(FlowerMobVariant.getTextureLocation(entityType, resourceKey),
                block.defaultBlockState(),
                Optional.of(FlowerMobVariant.getShearingLootTable(entityType, resourceKey)),
                Optional.of(getLayingLootTable(entityType, resourceKey)),
                biomes);
    }

    public static ResourceKey<LootTable> getLayingLootTable(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<CluckbloomVariant> resourceKey) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                resourceKey.location()
                        .withPath((String string) -> "gameplay/" + entityType.key().location().getPath() + "/laying/" +
                                string));
    }
}

package fuzs.bloomcraft.world.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
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

public record FlowerMobVariant(ResourceLocation textureLocation,
                               BlockState blockState,
                               ResourceKey<LootTable> shearingLootTable,
                               HolderSet<Biome> biomes) {
    public static final Codec<FlowerMobVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(FlowerMobVariant::textureLocation),
            BlockState.CODEC.fieldOf("carried_block").forGetter(FlowerMobVariant::blockState),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .fieldOf("shearing_loot_table")
                    .forGetter(FlowerMobVariant::shearingLootTable),
            RegistryCodecs.homogeneousList(Registries.BIOME)
                    .optionalFieldOf("biomes", HolderSet.empty())
                    .forGetter(FlowerMobVariant::biomes)).apply(instance, FlowerMobVariant::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, FlowerMobVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            FlowerMobVariant::textureLocation,
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
            FlowerMobVariant::blockState,
            ResourceKey.streamCodec(Registries.LOOT_TABLE),
            FlowerMobVariant::shearingLootTable,
            ByteBufCodecs.holderSet(Registries.BIOME),
            FlowerMobVariant::biomes,
            FlowerMobVariant::new);

    public FlowerMobVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        this(entityType, resourceKey, block, HolderSet.empty());
    }

    public FlowerMobVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<FlowerMobVariant> resourceKey, Block block, Holder<Biome> biome) {
        this(entityType, resourceKey, block, HolderSet.direct(biome));
    }

    public FlowerMobVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<FlowerMobVariant> resourceKey, Block block, HolderSet<Biome> biomes) {
        this(getTextureLocation(entityType, resourceKey),
                block.defaultBlockState(),
                getShearingLootTable(entityType, resourceKey),
                biomes);
    }

    public static Codec<Holder<FlowerMobVariant>> codec(ResourceKey<Registry<FlowerMobVariant>> registryKey) {
        return RegistryFileCodec.create(registryKey, DIRECT_CODEC);
    }

    public static StreamCodec<RegistryFriendlyByteBuf, Holder<FlowerMobVariant>> streamCodec(ResourceKey<Registry<FlowerMobVariant>> registryKey) {
        return ByteBufCodecs.holder(registryKey, DIRECT_STREAM_CODEC);
    }

    public static ResourceLocation transformTextureLocation(ResourceLocation resourceLocation) {
        return resourceLocation.withPath((String string) -> "textures/" + string + ".png");
    }

    public static ResourceLocation getTextureLocation(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<FlowerMobVariant> resourceKey) {
        String path = entityType.key().location().getPath();
        return resourceKey.location().withPath((String string) -> "entity/" + path + "/" + string + "_" + path);
    }

    public static ResourceKey<LootTable> getShearingLootTable(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<FlowerMobVariant> resourceKey) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                resourceKey.location()
                        .withPath((String string) -> "shearing/" + entityType.key().location().getPath() + "/" +
                                string));
    }
}

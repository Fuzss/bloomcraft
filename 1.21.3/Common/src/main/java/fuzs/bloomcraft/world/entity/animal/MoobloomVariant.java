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
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

public record MoobloomVariant(ResourceLocation textureLocation,
                              BlockState blockState,
                              Optional<ResourceKey<LootTable>> shearingLootTable,
                              Optional<SuspiciousStewEffects> suspiciousStewEffects,
                              HolderSet<Biome> biomes) implements FlowerMobVariant {
    public static final Codec<MoobloomVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(MoobloomVariant::textureLocation),
            BlockState.CODEC.fieldOf("carried_block").forGetter(MoobloomVariant::blockState),
            ResourceKey.codec(Registries.LOOT_TABLE)
                    .optionalFieldOf("shearing_loot_table")
                    .forGetter(MoobloomVariant::shearingLootTable),
            SuspiciousStewEffects.CODEC.optionalFieldOf("suspicious_stew_effects")
                    .forGetter(MoobloomVariant::suspiciousStewEffects),
            RegistryCodecs.homogeneousList(Registries.BIOME)
                    .optionalFieldOf("biomes", HolderSet.empty())
                    .forGetter(MoobloomVariant::biomes)).apply(instance, MoobloomVariant::new));
    public static final Codec<Holder<MoobloomVariant>> CODEC = RegistryFileCodec.create(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
            DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, MoobloomVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            MoobloomVariant::textureLocation,
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
            MoobloomVariant::blockState,
            ResourceKey.streamCodec(Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
            MoobloomVariant::shearingLootTable,
            SuspiciousStewEffects.STREAM_CODEC.apply(ByteBufCodecs::optional),
            MoobloomVariant::suspiciousStewEffects,
            ByteBufCodecs.holderSet(Registries.BIOME),
            MoobloomVariant::biomes,
            MoobloomVariant::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<MoobloomVariant>> STREAM_CODEC = ByteBufCodecs.holder(
            ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
            DIRECT_STREAM_CODEC);

    public MoobloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MoobloomVariant> resourceKey, FlowerBlock block) {
        this(entityType, resourceKey, block, block.getSuspiciousEffects(), HolderSet.empty());
    }

    public MoobloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MoobloomVariant> resourceKey, FlowerBlock block, Holder<Biome> biome) {
        this(entityType, resourceKey, block, block.getSuspiciousEffects(), HolderSet.direct(biome));
    }

    public MoobloomVariant(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<MoobloomVariant> resourceKey, Block block, SuspiciousStewEffects suspiciousStewEffects, HolderSet<Biome> biomes) {
        this(FlowerMobVariant.getTextureLocation(entityType, resourceKey),
                block.defaultBlockState(),
                Optional.of(FlowerMobVariant.getShearingLootTable(entityType, resourceKey)),
                Optional.of(suspiciousStewEffects),
                biomes);
    }
}

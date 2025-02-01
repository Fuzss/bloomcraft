package fuzs.bloomcraft.world.entity.animal;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

public interface FlowerMobVariant {

    ResourceLocation textureLocation();

    BlockState blockState();

    Optional<ResourceKey<LootTable>> shearingLootTable();

    HolderSet<Biome> biomes();

    static ResourceLocation transformTextureLocation(ResourceLocation resourceLocation) {
        return resourceLocation.withPath((String string) -> "textures/" + string + ".png");
    }

    static ResourceLocation getTextureLocation(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<? extends FlowerMobVariant> resourceKey) {
        String path = entityType.key().location().getPath();
        return resourceKey.location().withPath((String string) -> "entity/" + path + "/" + string + "_" + path);
    }

    static ResourceKey<LootTable> getShearingLootTable(Holder.Reference<? extends EntityType<?>> entityType, ResourceKey<? extends FlowerMobVariant> resourceKey) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                resourceKey.location()
                        .withPath((String string) -> "shearing/" + entityType.key().location().getPath() + "/" +
                                string));
    }
}

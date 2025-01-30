package fuzs.bloomcraft;

import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.api.biome.v1.BiomeLoadingPhase;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.*;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bloomcraft implements ModConstructor {
    public static final String MOD_ID = "bloomcraft";
    public static final String MOD_NAME = "Bloomcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onDataPackRegistriesContext(DataPackRegistriesContext context) {
        context.registerSynced(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY, FlowerMobVariant.DIRECT_CODEC);
        context.registerSynced(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, FlowerMobVariant.DIRECT_CODEC);
    }

    @Override
    public void onEntityAttributeCreation(EntityAttributesCreateContext context) {
        context.registerEntityAttributes(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), Cow.createAttributes());
        context.registerEntityAttributes(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), Chicken.createAttributes());
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules);
        context.registerSpawnPlacement(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules);
    }

    @Override
    public void onRegisterCompostableBlocks(CompostableBlocksContext context) {
        context.registerCompostable(0.65F, ModItems.BUTTERCUP, ModItems.PINK_DAISY);
    }

    @Override
    public void onRegisterBiomeModifications(BiomeModificationsContext context) {
        context.register(BiomeLoadingPhase.ADDITIONS, biomeLoadingContext -> {
            return biomeLoadingContext.is(Biomes.FLOWER_FOREST);
        }, biomeModificationContext -> {
            biomeModificationContext.mobSpawnSettings()
                    .addSpawn(MobCategory.CREATURE,
                            new MobSpawnSettings.SpawnerData(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), 16, 4, 8));
            biomeModificationContext.mobSpawnSettings()
                    .addSpawn(MobCategory.CREATURE,
                            new MobSpawnSettings.SpawnerData(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), 20, 4, 8));
        });
        context.register(BiomeLoadingPhase.REMOVALS, biomeLoadingContext -> {
            return biomeLoadingContext.is(Biomes.FLOWER_FOREST);
        }, biomeModificationContext -> {
            biomeModificationContext.mobSpawnSettings().removeSpawnsOfEntityType(EntityType.COW);
            biomeModificationContext.mobSpawnSettings().removeSpawnsOfEntityType(EntityType.CHICKEN);
        });
    }

    @Override
    public ContentRegistrationFlags[] getContentRegistrationFlags() {
        return new ContentRegistrationFlags[]{ContentRegistrationFlags.BIOME_MODIFICATIONS};
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}

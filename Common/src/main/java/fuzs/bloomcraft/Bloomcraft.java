package fuzs.bloomcraft;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.util.FlowerPatchFeatureHelper;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.api.biome.v1.BiomeLoadingContext;
import fuzs.puzzleslib.api.biome.v1.BiomeLoadingPhase;
import fuzs.puzzleslib.api.biome.v1.BiomeModificationContext;
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
import net.minecraft.world.level.levelgen.GenerationStep;
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
    public void onRegisterEntityAttributes(EntityAttributesContext context) {
        context.registerAttributes(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), Cow.createAttributes());
        context.registerAttributes(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), Chicken.createAttributes());
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
    public void onRegisterGameplayContent(GameplayContentContext context) {
        context.registerFlammable(ModBlocks.BUTTERCUP, 60, 100);
        context.registerFlammable(ModBlocks.PINK_DAISY, 60, 100);
        context.registerCompostable(ModItems.BUTTERCUP, 0.65F);
        context.registerCompostable(ModItems.PINK_DAISY, 0.65F);
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY, FlowerMobVariant.DIRECT_CODEC);
        context.registerSyncedRegistry(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, FlowerMobVariant.DIRECT_CODEC);
    }

    @Override
    public void onRegisterBiomeModifications(BiomeModificationsContext context) {
        context.registerBiomeModification(BiomeLoadingPhase.ADDITIONS, (BiomeLoadingContext biomeLoadingContext) -> {
            return biomeLoadingContext.is(Biomes.FLOWER_FOREST);
        }, (BiomeModificationContext biomeModificationContext) -> {
            biomeModificationContext.mobSpawnSettings()
                    .addSpawn(MobCategory.CREATURE,
                            16,
                            new MobSpawnSettings.SpawnerData(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), 4, 8));
            biomeModificationContext.mobSpawnSettings()
                    .addSpawn(MobCategory.CREATURE,
                            20,
                            new MobSpawnSettings.SpawnerData(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), 4, 8));
        });
        context.registerBiomeModification(BiomeLoadingPhase.REMOVALS, (BiomeLoadingContext biomeLoadingContext) -> {
            return biomeLoadingContext.is(Biomes.FLOWER_FOREST);
        }, (BiomeModificationContext biomeModificationContext) -> {
            biomeModificationContext.mobSpawnSettings().removeSpawnsOfEntityType(EntityType.COW);
            biomeModificationContext.mobSpawnSettings().removeSpawnsOfEntityType(EntityType.CHICKEN);
        });
        context.registerBiomeModification(BiomeLoadingPhase.MODIFICATIONS,
                (BiomeLoadingContext biomeLoadingContext) -> {
                    return biomeLoadingContext.is(ModRegistry.HAS_BUTTERCUP_BIOME_TAG);
                },
                (BiomeModificationContext biomeModificationContext) -> {
                    FlowerPatchFeatureHelper.registerFlowerFeatureModification(biomeModificationContext.generationSettings()
                                    .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                            ModBlocks.BUTTERCUP.value().defaultBlockState());
                });
        context.registerBiomeModification(BiomeLoadingPhase.MODIFICATIONS,
                (BiomeLoadingContext biomeLoadingContext) -> {
                    return biomeLoadingContext.is(ModRegistry.HAS_PINK_DAISY_BIOME_TAG);
                },
                (BiomeModificationContext biomeModificationContext) -> {
                    FlowerPatchFeatureHelper.registerFlowerFeatureModification(biomeModificationContext.generationSettings()
                                    .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                            ModBlocks.PINK_DAISY.value().defaultBlockState());
                });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}

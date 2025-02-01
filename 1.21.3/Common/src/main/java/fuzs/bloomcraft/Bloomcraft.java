package fuzs.bloomcraft;

import fuzs.bloomcraft.handler.HugeFlowerBoneMealHandler;
import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.util.FlowerPatchFeatureHelper;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import fuzs.puzzleslib.api.biome.v1.BiomeLoadingPhase;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.api.core.v1.context.*;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.entity.player.UseBoneMealCallback;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
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
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        UseBoneMealCallback.EVENT.register(HugeFlowerBoneMealHandler::onUseBoneMeal);
    }

    @Override
    public void onCommonSetup() {
        if (ModLoaderEnvironment.INSTANCE.getModLoader().isForgeLike()) {
            ModRegistry.registerTerrablenderRegions();
        }
    }

    @Override
    public void onDataPackRegistriesContext(DataPackRegistriesContext context) {
        context.registerSynced(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY, MoobloomVariant.DIRECT_CODEC);
        context.registerSynced(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, CluckbloomVariant.DIRECT_CODEC);
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
    public void onRegisterFlammableBlocks(FlammableBlocksContext context) {
        context.registerFlammable(60,
                100,
                ModBlocks.BUTTERCUP.value(),
                ModBlocks.PINK_DAISY.value(),
                ModBlocks.ROSE.value());
        context.registerFlammable(30,
                60,
                ModBlocks.YELLOW_PETAL_BLOCK.value(),
                ModBlocks.RED_PETAL_BLOCK.value(),
                ModBlocks.PINK_PETAL_BLOCK.value(),
                ModBlocks.ORANGE_PETAL_BLOCK.value());
    }

    @Override
    public void onRegisterCompostableBlocks(CompostableBlocksContext context) {
        context.registerCompostable(0.65F, ModItems.BUTTERCUP, ModItems.PINK_DAISY, ModItems.ROSE);
        context.registerCompostable(0.3F,
                ModBlocks.YELLOW_PETAL_BLOCK,
                ModBlocks.RED_PETAL_BLOCK,
                ModBlocks.PINK_PETAL_BLOCK,
                ModBlocks.ORANGE_PETAL_BLOCK);
    }

    @Override
    public void onRegisterBlockInteractions(BlockInteractionsContext context) {
        context.registerTillable(Blocks.FARMLAND, ModBlocks.FLOWERING_GRASS_BLOCK.value());
        context.registerFlattenable(Blocks.DIRT_PATH, ModBlocks.FLOWERING_GRASS_BLOCK.value());
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
        context.register(BiomeLoadingPhase.MODIFICATIONS, biomeLoadingContext -> {
            return biomeLoadingContext.is(ModRegistry.HAS_BUTTERCUP_BIOME_TAG);
        }, biomeModificationContext -> {
            FlowerPatchFeatureHelper.registerFlowerFeatureModification(biomeModificationContext.generationSettings()
                            .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                    ModBlocks.BUTTERCUP.value().defaultBlockState());
        });
        context.register(BiomeLoadingPhase.MODIFICATIONS, biomeLoadingContext -> {
            return biomeLoadingContext.is(ModRegistry.HAS_PINK_DAISY_BIOME_TAG);
        }, biomeModificationContext -> {
            FlowerPatchFeatureHelper.registerFlowerFeatureModification(biomeModificationContext.generationSettings()
                            .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                    ModBlocks.PINK_DAISY.value().defaultBlockState());
        });
        context.register(BiomeLoadingPhase.MODIFICATIONS, biomeLoadingContext -> {
            return biomeLoadingContext.is(ModRegistry.HAS_ROSE_BIOME_TAG);
        }, biomeModificationContext -> {
            FlowerPatchFeatureHelper.registerFlowerFeatureModification(biomeModificationContext.generationSettings()
                            .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                    ModBlocks.ROSE.value().defaultBlockState());
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

package fuzs.bloomcraft.common;

import fuzs.bloomcraft.common.init.*;
import fuzs.bloomcraft.common.util.FlowerPatchFeatureHelper;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.common.api.biome.v2.BiomeLoadingPhase;
import fuzs.puzzleslib.common.api.biome.v2.BiomeTransformer;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
        context.registerAttributes(ModEntityTypes.MOOBLOOM_ENTITY_TYPE.value(), Cow.createAttributes());
        context.registerAttributes(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE.value(), Chicken.createAttributes());
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModEntityTypes.MOOBLOOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules);
        context.registerSpawnPlacement(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules);
    }

    @Override
    public void onRegisterGameplayContent(GameplayContentContext context) {
        context.registerFlammable(ModBlocks.BUTTERCUP, 60, 100);
        context.registerFlammable(ModBlocks.PINK_DAISY, 60, 100);
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(MoobloomVariants.MOOBLOOM_VARIANT_KEY, FlowerMobVariant.DIRECT_CODEC);
        context.registerSyncedRegistry(CluckbloomVariants.CLUCKBLOOM_VARIANT_KEY, FlowerMobVariant.DIRECT_CODEC);
    }

    @Override
    public void onRegisterBiomeTransformations(BiomeTransformationsContext context) {
        context.registerBiomeTransformation(BiomeLoadingPhase.ADD,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.FLOWER_FOREST);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    transformation.mobSpawns().addSpawn(ModEntityTypes.MOOBLOOM_ENTITY_TYPE.value(), 16, 4, 8);
                    transformation.mobSpawns().addSpawn(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE.value(), 20, 4, 8);
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.REMOVE,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(Biomes.FLOWER_FOREST);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    transformation.mobSpawns().removeSpawn(EntityTypes.COW);
                    transformation.mobSpawns().removeSpawn(EntityTypes.CHICKEN);
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.MODIFY,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(ModTags.Biomes.HAS_BUTTERCUP_BIOME_TAG);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    FlowerPatchFeatureHelper.registerFlowerFeatureModification(transformation.generation()
                                    .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                            ModBlocks.BUTTERCUP.value().defaultBlockState(),
                            BlockTags.SMALL_FLOWERS);
                });
        context.registerBiomeTransformation(BiomeLoadingPhase.MODIFY,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return biome.is(ModTags.Biomes.HAS_PINK_DAISY_BIOME_TAG);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    FlowerPatchFeatureHelper.registerFlowerFeatureModification(transformation.generation()
                                    .getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION),
                            ModBlocks.PINK_DAISY.value().defaultBlockState(),
                            BlockTags.SMALL_FLOWERS);
                });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

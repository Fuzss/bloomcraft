package fuzs.bloomcraft.data;

import fuzs.bloomcraft.init.*;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import fuzs.puzzleslib.api.data.v2.AbstractDatapackRegistriesProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomBooleanFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class ModDatapackRegistriesProvider extends AbstractDatapackRegistriesProvider {

    public ModDatapackRegistriesProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBootstrap(RegistryBoostrapConsumer consumer) {
        consumer.add(Registries.CONFIGURED_FEATURE, ModDatapackRegistriesProvider::bootstrapConfiguredFeatures);
        consumer.add(Registries.PLACED_FEATURE, ModDatapackRegistriesProvider::bootstrapPlacedFeatures);
        consumer.add(Registries.BIOME, ModDatapackRegistriesProvider::bootstrapBiomes);
        consumer.add(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
                ModDatapackRegistriesProvider::bootstrapMoobloomVariants);
        consumer.add(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
                ModDatapackRegistriesProvider::bootstrapCluckbloomVariants);
    }

    static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context,
                ModFeatures.FLOWERING_GARDEN_VEGETATION_CONFIGURED_FEATURE,
                Feature.RANDOM_BOOLEAN_SELECTOR,
                new RandomBooleanFeatureConfiguration(PlacementUtils.inlinePlaced(ModFeatures.HUGE_YELLOW_FLOWER_FEATURE.value(),
                        FeatureConfiguration.NONE),
                        PlacementUtils.inlinePlaced(ModFeatures.HUGE_RED_FLOWER_FEATURE.value(),
                                FeatureConfiguration.NONE)));
        FeatureUtils.register(context,
                ModFeatures.FLOWER_GARDEN_CONFIGURED_FEATURE,
                Feature.FLOWER,
                new RandomPatchConfiguration(64,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(new NoiseThresholdProvider(2345L,
                                        new NormalNoise.NoiseParameters(0, 1.0),
                                        0.005F,
                                        -0.8F,
                                        0.33333334F,
                                        Blocks.DANDELION.defaultBlockState(),
                                        List.of(Blocks.ORANGE_TULIP.defaultBlockState(),
                                                Blocks.RED_TULIP.defaultBlockState(),
                                                Blocks.PINK_TULIP.defaultBlockState(),
                                                Blocks.WHITE_TULIP.defaultBlockState()),
                                        List.of(ModBlocks.ROSE.value().defaultBlockState(),
                                                ModBlocks.BUTTERCUP.value().defaultBlockState(),
                                                ModBlocks.PINK_DAISY.value().defaultBlockState()))))));
    }

    static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context,
                ModFeatures.FLOWERING_GARDEN_VEGETATION_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.FLOWERING_GARDEN_VEGETATION_CONFIGURED_FEATURE),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
        PlacementUtils.register(context,
                ModFeatures.FLOWER_GARDEN_PLACED_FEATURE,
                configuredFeatureLookup.getOrThrow(ModFeatures.FLOWER_GARDEN_CONFIGURED_FEATURE),
                NoiseThresholdCountPlacement.of(-0.8, 15, 4),
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
    }

    static void bootstrapBiomes(BootstrapContext<Biome> context) {
        context.register(ModRegistry.FLOWERING_GARDEN_BIOME,
                floweringGarden(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)));
    }

    public static Biome floweringGarden(HolderGetter<PlacedFeature> placedFeatureLookup, HolderGetter<ConfiguredWorldCarver<?>> worldCarverLookup) {
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        addFloweringGardenSpawns(mobSpawnSettings);
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(
                placedFeatureLookup,
                worldCarverLookup);
        OverworldBiomes.globalOverworldGeneration(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);
        addFloweringGardenVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeGenerationSettings);
        return OverworldBiomes.biome(true, 0.9F, 1.0F, mobSpawnSettings, biomeGenerationSettings, null);
    }

    public static void addFloweringGardenSpawns(MobSpawnSettings.Builder builder) {
        builder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), 8, 4, 8));
        builder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), 8, 4, 8));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 4));
        BiomeDefaultFeatures.caveSpawns(builder);
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 2, 1, 2));
    }

    public static void addFloweringGardenVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                ModFeatures.FLOWERING_GARDEN_VEGETATION_PLACED_FEATURE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS_2);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.FLOWER_GARDEN_PLACED_FEATURE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
    }

    static void bootstrapMoobloomVariants(BootstrapContext<MoobloomVariant> context) {
        registerMoobloomVariant(context, ModMoobloomVariants.DANDELION, Blocks.DANDELION);
        registerMoobloomVariant(context, ModMoobloomVariants.POPPY, Blocks.POPPY);
        registerMoobloomVariant(context, ModMoobloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        registerMoobloomVariant(context, ModMoobloomVariants.ALLIUM, Blocks.ALLIUM);
        registerMoobloomVariant(context, ModMoobloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        registerMoobloomVariant(context, ModMoobloomVariants.RED_TULIP, Blocks.RED_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        registerMoobloomVariant(context, ModMoobloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        registerMoobloomVariant(context, ModMoobloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        registerMoobloomVariant(context, ModMoobloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        registerMoobloomVariant(context, ModMoobloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        registerMoobloomVariant(context, ModMoobloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        registerMoobloomVariant(context, ModMoobloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
        registerMoobloomVariant(context, ModMoobloomVariants.ROSE, ModBlocks.ROSE.value());
    }

    static void registerMoobloomVariant(BootstrapContext<MoobloomVariant> context, ResourceKey<MoobloomVariant> resourceKey, Block block) {
        context.register(resourceKey,
                new MoobloomVariant(ModRegistry.MOOBLOOM_ENTITY_TYPE, resourceKey, (FlowerBlock) block));
    }

    static void bootstrapCluckbloomVariants(BootstrapContext<CluckbloomVariant> context) {
        registerCluckbloomVariant(context, ModCluckbloomVariants.DANDELION, Blocks.DANDELION);
        registerCluckbloomVariant(context, ModCluckbloomVariants.POPPY, Blocks.POPPY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        registerCluckbloomVariant(context, ModCluckbloomVariants.ALLIUM, Blocks.ALLIUM);
        registerCluckbloomVariant(context, ModCluckbloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        registerCluckbloomVariant(context, ModCluckbloomVariants.RED_TULIP, Blocks.RED_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        registerCluckbloomVariant(context, ModCluckbloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        registerCluckbloomVariant(context, ModCluckbloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        registerCluckbloomVariant(context, ModCluckbloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        registerCluckbloomVariant(context, ModCluckbloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
        registerCluckbloomVariant(context, ModCluckbloomVariants.ROSE, ModBlocks.ROSE.value());
    }

    static void registerCluckbloomVariant(BootstrapContext<CluckbloomVariant> context, ResourceKey<CluckbloomVariant> resourceKey, Block block) {
        context.register(resourceKey, new CluckbloomVariant(ModRegistry.CLUCKBLOOM_ENTITY_TYPE, resourceKey, block));
    }
}

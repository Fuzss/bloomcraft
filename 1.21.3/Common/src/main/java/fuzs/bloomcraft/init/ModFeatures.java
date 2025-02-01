package fuzs.bloomcraft.init;

import fuzs.bloomcraft.world.level.levelgen.feature.HugeRedFlowerFeature;
import fuzs.bloomcraft.world.level.levelgen.feature.HugeYellowFlowerFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModFeatures {
    public static final Holder.Reference<Feature<NoneFeatureConfiguration>> HUGE_YELLOW_FLOWER_FEATURE = ModRegistry.REGISTRIES.register(
            Registries.FEATURE,
            "huge_yellow_mushroom",
            () -> new HugeYellowFlowerFeature(NoneFeatureConfiguration.CODEC));
    public static final Holder.Reference<Feature<NoneFeatureConfiguration>> HUGE_RED_FLOWER_FEATURE = ModRegistry.REGISTRIES.register(
            Registries.FEATURE,
            "huge_red_mushroom",
            () -> new HugeRedFlowerFeature(NoneFeatureConfiguration.CODEC));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_GARDEN_VEGETATION_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "flowering_garden_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_GARDEN_CONFIGURED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.CONFIGURED_FEATURE,
            "flower_garden");
    public static final ResourceKey<PlacedFeature> FLOWERING_GARDEN_VEGETATION_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "flowering_garden_vegetation");
    public static final ResourceKey<PlacedFeature> FLOWER_GARDEN_PLACED_FEATURE = ModRegistry.REGISTRIES.makeResourceKey(
            Registries.PLACED_FEATURE,
            "flower_garden");

    public static void bootstrap() {
        // NO-OP
    }
}

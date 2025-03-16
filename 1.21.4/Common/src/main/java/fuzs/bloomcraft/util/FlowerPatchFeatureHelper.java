package fuzs.bloomcraft.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Collections;
import java.util.List;

/**
 * A helper class for adding flowers to biomes. The block state providers are intentionally mutated instead of simply
 * replacing them with custom ones, so that multiple mods may add their flowers without interference.
 */
public final class FlowerPatchFeatureHelper {

    private FlowerPatchFeatureHelper() {
        // NO-OP
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, BlockState blockState) {
        registerFlowerFeatureModification(vegetalDecoration, Collections.singletonList(blockState));
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, List<BlockState> blockStates) {
        for (Holder<PlacedFeature> holder : vegetalDecoration) {
            ConfiguredFeature<?, ?> flowerConfiguredFeature = holder.value().feature().value();
            if (flowerConfiguredFeature.feature() == Feature.FLOWER &&
                    flowerConfiguredFeature.config() instanceof RandomPatchConfiguration randomPatchConfiguration) {
                ConfiguredFeature<?, ?> blockConfigurationConfiguredFeature = randomPatchConfiguration.feature()
                        .value()
                        .feature()
                        .value();
                if (blockConfigurationConfiguredFeature.config() instanceof SimpleBlockConfiguration simpleBlockConfiguration) {
                    if (simpleBlockConfiguration.toPlace() instanceof NoiseProvider noiseProvider) {
                        addNoiseProviderStates(noiseProvider, blockStates);
                        break;
                    } else if (simpleBlockConfiguration.toPlace() instanceof NoiseThresholdProvider noiseThresholdProvider) {
                        addNoiseThresholdProviderStates(noiseThresholdProvider, blockStates);
                        break;
                    } else if (simpleBlockConfiguration.toPlace() instanceof WeightedStateProvider weightedStateProvider) {
                        addWeightedStateProviderStates(weightedStateProvider, blockStates);
                        break;
                    } else if (simpleBlockConfiguration.toPlace() instanceof SimpleStateProvider simpleStateProvider) {
                        addSimpleStateProviderStates(simpleBlockConfiguration, simpleStateProvider, blockStates);
                        break;
                    }
                }
            }
        }
    }

    private static void addNoiseProviderStates(NoiseProvider noiseProvider, List<BlockState> blockStates) {
        noiseProvider.states = ImmutableList.<BlockState>builder()
                .addAll(noiseProvider.states)
                .addAll(blockStates)
                .build();
    }

    private static void addNoiseThresholdProviderStates(NoiseThresholdProvider noiseThresholdProvider, List<BlockState> blockStates) {
        noiseThresholdProvider.highStates = ImmutableList.<BlockState>builder()
                .addAll(noiseThresholdProvider.highStates)
                .addAll(blockStates)
                .build();
    }

    private static void addWeightedStateProviderStates(WeightedStateProvider weightedStateProvider, List<BlockState> blockStates) {
        List<WeightedEntry.Wrapper<BlockState>> list = weightedStateProvider.weightedList.unwrap();
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        int maxWeight = 1;
        for (WeightedEntry.Wrapper<BlockState> blockStateWrapper : list) {
            int weight = blockStateWrapper.getWeight().asInt();
            if (weight > maxWeight) maxWeight = weight;
            builder.add(blockStateWrapper.data(), weight);
        }
        for (BlockState blockState : blockStates) {
            builder.add(blockState, maxWeight);
        }
        weightedStateProvider.weightedList = builder.build();
    }

    private static void addSimpleStateProviderStates(SimpleBlockConfiguration simpleBlockConfiguration, SimpleStateProvider simpleStateProvider, List<BlockState> blockStates) {
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        builder.add(simpleStateProvider.state);
        blockStates.forEach(builder::add);
        simpleBlockConfiguration.toPlace = new WeightedStateProvider(builder.build());
    }
}

package fuzs.bloomcraft.common.util;

import com.google.common.collect.ImmutableList;
import fuzs.bloomcraft.common.mixin.accessor.SimpleBlockFeatureAccessor;
import fuzs.bloomcraft.common.mixin.accessor.WeightedStateProviderAccessor;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * A helper class for adding flowers to biomes. The block state providers are intentionally mutated instead of simply
 * replacing them with custom ones so that multiple mods may add their flowers without interference.
 */
public final class FlowerPatchFeatureHelper {

    private FlowerPatchFeatureHelper() {
        // NO-OP
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, BlockState blockState, TagKey<Block> blockFilter) {
        registerFlowerFeatureModification(vegetalDecoration, blockState, (BlockState block) -> block.is(blockFilter));
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, BlockState blockState, Predicate<BlockState> blockFilter) {
        registerFlowerFeatureModification(vegetalDecoration, Collections.singletonList(blockState), blockFilter);
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, List<BlockState> blockStates, TagKey<Block> blockFilter) {
        registerFlowerFeatureModification(vegetalDecoration, blockStates, (BlockState block) -> block.is(blockFilter));
    }

    public static void registerFlowerFeatureModification(Iterable<Holder<PlacedFeature>> vegetalDecoration, List<BlockState> blockStates, Predicate<BlockState> blockFilter) {
        for (Holder<PlacedFeature> holder : vegetalDecoration) {
            if (holder.value().feature().value() instanceof SimpleBlockFeature simpleBlockFeature
                    && simpleBlockFeature.toPlace().value() instanceof BlockStateProvider blockStateProvider) {
                if (blockStateProvider instanceof NoiseProvider noiseProvider) {
                    if (addNoiseProviderStates(noiseProvider, blockStates, blockFilter)) {
                        break;
                    }
                } else if (blockStateProvider instanceof NoiseThresholdProvider noiseThresholdProvider) {
                    if (addNoiseThresholdProviderStates(noiseThresholdProvider, blockStates, blockFilter)) {
                        break;
                    }
                } else if (blockStateProvider instanceof WeightedStateProvider weightedStateProvider) {
                    if (addWeightedStateProviderStates(weightedStateProvider, blockStates, blockFilter)) {
                        break;
                    }
                } else if (blockStateProvider instanceof SimpleStateProvider simpleStateProvider) {
                    if (addSimpleStateProviderStates(simpleBlockFeature,
                            simpleStateProvider,
                            blockStates,
                            blockFilter)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean addNoiseProviderStates(NoiseProvider noiseProvider, List<BlockState> blockStates, Predicate<BlockState> blockFilter) {
        if (noiseProvider.states.stream().allMatch(blockFilter)) {
            noiseProvider.states = ImmutableList.<BlockState>builder()
                    .addAll(noiseProvider.states)
                    .addAll(blockStates)
                    .build();
            return true;
        } else {
            return false;
        }
    }

    private static boolean addNoiseThresholdProviderStates(NoiseThresholdProvider noiseThresholdProvider, List<BlockState> blockStates, Predicate<BlockState> blockFilter) {
        if (noiseThresholdProvider.highStates.stream().allMatch(blockFilter)) {
            noiseThresholdProvider.highStates = ImmutableList.<BlockState>builder()
                    .addAll(noiseThresholdProvider.highStates)
                    .addAll(blockStates)
                    .build();
            return true;
        } else {
            return false;
        }
    }

    private static boolean addWeightedStateProviderStates(WeightedStateProvider provider, List<BlockState> blockStates, Predicate<BlockState> blockFilter) {
        List<Weighted<BlockState>> providerStates = provider.weightedList().unwrap();
        if (providerStates.stream().map(Weighted::value).allMatch(blockFilter)) {
            WeightedList.Builder<BlockState> builder = WeightedList.builder();
            int maxWeight = 1;
            for (Weighted<BlockState> blockStateWrapper : providerStates) {
                int weight = blockStateWrapper.weight();
                if (weight > maxWeight) {
                    maxWeight = weight;
                }

                builder.add(blockStateWrapper.value(), weight);
            }

            for (BlockState blockState : blockStates) {
                builder.add(blockState, maxWeight);
            }

            WeightedStateProviderAccessor.class.cast(provider).bloomcraft$setWeightedList(builder.build());
            return true;
        } else {
            return false;
        }
    }

    private static boolean addSimpleStateProviderStates(SimpleBlockFeature feature, SimpleStateProvider provider, List<BlockState> blockStates, Predicate<BlockState> blockFilter) {
        if (blockFilter.test(provider.state())) {
            WeightedList.Builder<BlockState> builder = WeightedList.builder();
            builder.add(provider.state());
            blockStates.forEach(builder::add);
            SimpleBlockFeatureAccessor.class.cast(feature)
                    .bloomcraft$setToPlace(Holder.direct(new WeightedStateProvider(builder.build())));
            return true;
        } else {
            return false;
        }
    }
}

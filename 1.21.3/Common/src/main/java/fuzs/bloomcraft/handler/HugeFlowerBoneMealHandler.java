package fuzs.bloomcraft.handler;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModFeatures;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HugeFlowerBoneMealHandler {

    public static EventResult onUseBoneMeal(Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) {
        if (level.getBlockState(blockPos.below()).is(ModBlocks.FLOWERING_GRASS_BLOCK)) {
            if (blockState.is(Blocks.DANDELION)) {
                growHugeFlower(level, blockPos, blockState, ModFeatures.HUGE_YELLOW_FLOWER_FEATURE);
                return EventResult.ALLOW;
            } else if (blockState.is(ModBlocks.ROSE)) {
                growHugeFlower(level, blockPos, blockState, ModFeatures.HUGE_RED_FLOWER_FEATURE);
                return EventResult.ALLOW;
            }
        }

        return EventResult.PASS;
    }

    static boolean growHugeFlower(Level level, BlockPos blockPos, BlockState blockState, Holder<Feature<NoneFeatureConfiguration>> feature) {
        if (level instanceof ServerLevel serverLevel && level.getRandom().nextFloat() < 0.4F) {
            serverLevel.removeBlock(blockPos, false);
            if (feature.value()
                    .place(NoneFeatureConfiguration.INSTANCE,
                            serverLevel,
                            serverLevel.getChunkSource().getGenerator(),
                            level.getRandom(),
                            blockPos)) {
                return true;
            } else {
                serverLevel.setBlock(blockPos, blockState, 3);
                return false;
            }
        } else {
            return false;
        }
    }
}

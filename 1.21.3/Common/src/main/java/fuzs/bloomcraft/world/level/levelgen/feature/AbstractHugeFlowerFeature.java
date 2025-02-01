package fuzs.bloomcraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import fuzs.bloomcraft.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public abstract class AbstractHugeFlowerFeature extends Feature<NoneFeatureConfiguration> {

    public AbstractHugeFlowerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        RandomSource randomSource = context.random();
        int i = this.getTreeHeight(randomSource);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldGenLevel, blockPos, i, mutableBlockPos)) {
            return false;
        } else {
            this.placePetals(worldGenLevel, randomSource, blockPos, i, mutableBlockPos);
            this.placeTrunk(worldGenLevel, randomSource, blockPos, i, mutableBlockPos);
            return true;
        }
    }

    protected void placePetals(LevelAccessor level, RandomSource random, BlockPos pos, int treeHeight, BlockPos.MutableBlockPos mutablePos) {
        for (int y = 0; y < this.getFoliageHeight(); y++) {
            for (int x = -this.getFoliageRadius(); x <= this.getFoliageRadius(); x++) {
                for (int z = -this.getFoliageRadius(); z <= this.getFoliageRadius(); z++) {
                    if (this.placePetalAtHeight(x, y, z)) {
                        mutablePos.setWithOffset(pos, x, treeHeight + y, z);
                        this.setBlock(level, mutablePos, this.getPetalBlock());
                    }
                }
            }
        }
    }

    protected abstract boolean placePetalAtHeight(int x, int y, int z);

    protected abstract int getFoliageRadius();

    protected abstract int getFoliageHeight();

    protected abstract BlockState getPetalBlock();

    protected void placeTrunk(LevelAccessor level, RandomSource random, BlockPos pos, int maxHeight, BlockPos.MutableBlockPos mutablePos) {
        for (int i = 0; i < maxHeight; i++) {
            mutablePos.set(pos).move(Direction.UP, i);
            if (!level.getBlockState(mutablePos).isSolidRender()) {
                this.setBlock(level, mutablePos, ModBlocks.STEMWOOD_LOG.value().defaultBlockState());
            }
        }
    }

    protected int getTreeHeight(RandomSource random) {
        int i = random.nextInt(3) + 4;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i;
    }

    protected boolean isValidPosition(LevelAccessor level, BlockPos pos, int maxHeight, BlockPos.MutableBlockPos mutablePos) {
        int i = pos.getY();
        if (i >= level.getMinY() + 1 && i + maxHeight + 1 <= level.getMaxY()) {
            BlockState blockState = level.getBlockState(pos.below());
            if (!isDirt(blockState)) {
                return false;
            } else {
                for (int j = 0; j <= maxHeight; j++) {
                    int k = this.getFoliageRadius();

                    for (int l = -k; l <= k; l++) {
                        for (int m = -k; m <= k; m++) {
                            BlockState blockState2 = level.getBlockState(mutablePos.setWithOffset(pos, l, j, m));
                            if (!blockState2.isAir() && !blockState2.is(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }
}

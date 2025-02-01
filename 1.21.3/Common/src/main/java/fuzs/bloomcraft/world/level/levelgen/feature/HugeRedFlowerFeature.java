package fuzs.bloomcraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import fuzs.bloomcraft.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HugeRedFlowerFeature extends AbstractHugeFlowerFeature {

    public HugeRedFlowerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected boolean placePetalAtHeight(int x, int y, int z) {
        if (y == 0) {
            return Math.abs(x) + Math.abs(z) <= 1;
        } else if (y == 1) {
            return Math.abs(x) + Math.abs(z) == 2;
        } else if (y == 2) {
            return Math.abs(x) + Math.abs(z) == 2 || Math.abs(x) == 2 && Math.abs(z) == 2;
        } else if (y == 3) {
            return Math.abs(x) == 2 && z == 0 || Math.abs(z) == 2 && x == 0;
        } else if (y == 4) {
            return Math.abs(x) == 3 && z == 0 || Math.abs(z) == 3 && x == 0;
        } else {
            return false;
        }
    }

    @Override
    protected int getFoliageRadius() {
        return 3;
    }

    @Override
    protected int getFoliageHeight() {
        return 5;
    }

    @Override
    protected BlockState getPetalBlock() {
        return ModBlocks.RED_PETAL_BLOCK.value().defaultBlockState();
    }
}

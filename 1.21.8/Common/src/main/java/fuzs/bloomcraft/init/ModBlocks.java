package fuzs.bloomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final Holder.Reference<Block> BUTTERCUP = ModRegistry.REGISTRIES.registerBlock("buttercup",
            (BlockBehaviour.Properties properties) -> new FlowerBlock(MobEffects.SATURATION, 0.35F, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Holder.Reference<Block> PINK_DAISY = ModRegistry.REGISTRIES.registerBlock("pink_daisy",
            (BlockBehaviour.Properties properties) -> new FlowerBlock(MobEffects.REGENERATION, 8.0F, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY));
    public static final Holder.Reference<Block> POTTED_BUTTERCUP = ModRegistry.REGISTRIES.registerBlock(
            "potted_buttercup",
            (BlockBehaviour.Properties properties) -> new FlowerPotBlock(BUTTERCUP.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> POTTED_PINK_DAISY = ModRegistry.REGISTRIES.registerBlock(
            "potted_pink_daisy",
            (BlockBehaviour.Properties properties) -> new FlowerPotBlock(PINK_DAISY.value(), properties),
            Blocks::flowerPotProperties);

    public static void bootstrap() {
        // NO-OP
    }
}

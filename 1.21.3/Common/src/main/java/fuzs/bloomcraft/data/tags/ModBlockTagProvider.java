package fuzs.bloomcraft.data.tags;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {

    public ModBlockTagProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.add(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.BUTTERCUP.value(), ModBlocks.PINK_DAISY.value(), ModBlocks.ROSE.value());
        this.add(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_BUTTERCUP.value(),
                        ModBlocks.POTTED_PINK_DAISY.value(),
                        ModBlocks.POTTED_ROSE.value());
        this.add(BlockTags.DIRT).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.ANIMALS_SPAWNABLE_ON).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.RABBITS_SPAWNABLE_ON).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.YELLOW_PETAL_BLOCK.value(),
                        ModBlocks.RED_PETAL_BLOCK.value(),
                        ModBlocks.PINK_PETAL_BLOCK.value(),
                        ModBlocks.ORANGE_PETAL_BLOCK.value());
        this.add(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.YELLOW_PETAL_BLOCK.value(),
                        ModBlocks.RED_PETAL_BLOCK.value(),
                        ModBlocks.PINK_PETAL_BLOCK.value(),
                        ModBlocks.ORANGE_PETAL_BLOCK.value());
    }
}

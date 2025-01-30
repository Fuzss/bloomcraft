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
        this.add(BlockTags.SMALL_FLOWERS).add(ModBlocks.BUTTERCUP.value(), ModBlocks.PINK_DAISY.value());
        this.add(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_BUTTERCUP.value(), ModBlocks.POTTED_PINK_DAISY.value());
    }
}

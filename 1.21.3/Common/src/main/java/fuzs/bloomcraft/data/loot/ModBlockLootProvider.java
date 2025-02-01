package fuzs.bloomcraft.data.loot;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.dropSelf(ModBlocks.BUTTERCUP.value());
        this.dropSelf(ModBlocks.PINK_DAISY.value());
        this.dropSelf(ModBlocks.ROSE.value());
        this.dropPottedContents(ModBlocks.POTTED_BUTTERCUP.value());
        this.dropPottedContents(ModBlocks.POTTED_PINK_DAISY.value());
        this.dropPottedContents(ModBlocks.POTTED_ROSE.value());
        this.add(ModBlocks.FLOWERING_GRASS_BLOCK.value(),
                (Block block) -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
        this.add(ModBlocks.YELLOW_PETAL_BLOCK.value(),
                (Block block) -> this.createPetalBlockDrop(block, Items.YELLOW_DYE));
        this.add(ModBlocks.RED_PETAL_BLOCK.value(), (Block block) -> this.createPetalBlockDrop(block, Items.RED_DYE));
        this.add(ModBlocks.PINK_PETAL_BLOCK.value(), (Block block) -> this.createPetalBlockDrop(block, Items.PINK_DYE));
        this.add(ModBlocks.ORANGE_PETAL_BLOCK.value(),
                (Block block) -> this.createPetalBlockDrop(block, Items.ORANGE_DYE));
    }

    /**
     * Copied from {@link #createMushroomBlockDrop(Block, ItemLike)}.
     */
    public LootTable.Builder createPetalBlockDrop(Block block, ItemLike item) {
        // also allow for using shears
        return this.createSilkTouchOrShearsDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F)))
                                .apply(LimitCount.limitCount(IntRange.lowerBound(0)))));
    }
}

package fuzs.bloomcraft.common.data.loot;

import fuzs.bloomcraft.common.init.ModBlocks;
import fuzs.puzzleslib.common.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.dropSelf(ModBlocks.BUTTERCUP.value());
        this.dropSelf(ModBlocks.PINK_DAISY.value());
        this.dropPottedContents(ModBlocks.POTTED_BUTTERCUP.value());
        this.dropPottedContents(ModBlocks.POTTED_PINK_DAISY.value());
    }
}

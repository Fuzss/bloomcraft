package fuzs.bloomcraft.common.data.loot;

import fuzs.bloomcraft.common.init.ModBlocks;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.BUTTERCUP.value());
        this.dropSelf(ModBlocks.PINK_DAISY.value());
        this.dropPottedContents(ModBlocks.POTTED_BUTTERCUP.value());
        this.dropPottedContents(ModBlocks.POTTED_PINK_DAISY.value());
    }
}

package fuzs.bloomcraft.data.client;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators builder) {
        builder.createPlant(ModBlocks.BUTTERCUP.value(),
                ModBlocks.POTTED_BUTTERCUP.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.PINK_DAISY.value(),
                ModBlocks.POTTED_PINK_DAISY.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        builder.generateFlatItem(ModItems.MOOBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
    }
}

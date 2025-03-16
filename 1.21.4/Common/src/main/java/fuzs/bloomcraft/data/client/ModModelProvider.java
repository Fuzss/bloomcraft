package fuzs.bloomcraft.data.client;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators builder) {
        builder.createPlantWithDefaultItem(ModBlocks.BUTTERCUP.value(),
                ModBlocks.POTTED_BUTTERCUP.value(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        builder.createPlantWithDefaultItem(ModBlocks.PINK_DAISY.value(),
                ModBlocks.POTTED_PINK_DAISY.value(),
                BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        builder.generateFlatItem(ModItems.MOOBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
    }
}

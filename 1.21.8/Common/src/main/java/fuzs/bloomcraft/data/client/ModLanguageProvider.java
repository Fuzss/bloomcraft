package fuzs.bloomcraft.data.client;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), Bloomcraft.MOD_NAME);
        builder.add(ModBlocks.BUTTERCUP.value(), "Buttercup");
        builder.add(ModBlocks.POTTED_BUTTERCUP.value(), "Potted Buttercup");
        builder.add(ModBlocks.PINK_DAISY.value(), "Pink Daisy");
        builder.add(ModBlocks.POTTED_PINK_DAISY.value(), "Potted Pink Daisy");
        builder.add(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), "Moobloom");
        builder.add(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), "Cluckbloom");
        builder.addSpawnEgg(ModItems.MOOBLOOM_SPAWN_EGG.value(), "Moobloom");
        builder.addSpawnEgg(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), "Cluckbloom");
    }
}

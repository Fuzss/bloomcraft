package fuzs.bloomcraft.common.data.client;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.bloomcraft.common.init.ModBlocks;
import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.bloomcraft.common.init.ModItems;
import fuzs.bloomcraft.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), Bloomcraft.MOD_NAME);
        this.add(ModBlocks.BUTTERCUP.value(), "Buttercup");
        this.add(ModBlocks.POTTED_BUTTERCUP.value(), "Potted Buttercup");
        this.add(ModBlocks.PINK_DAISY.value(), "Pink Daisy");
        this.add(ModBlocks.POTTED_PINK_DAISY.value(), "Potted Pink Daisy");
        this.add(ModEntityTypes.MOOBLOOM_ENTITY_TYPE.value(), "Moobloom");
        this.add(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE.value(), "Cluckbloom");
        this.addSpawnEgg(ModItems.MOOBLOOM_SPAWN_EGG.value(), "Moobloom");
        this.addSpawnEgg(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), "Cluckbloom");
    }
}

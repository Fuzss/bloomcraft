package fuzs.bloomcraft.data.client;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.init.ModBlockFamilies;
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
        builder.add(ModBlocks.ROSE.value(), "Rose");
        builder.add(ModBlocks.POTTED_ROSE.value(), "Potted Rose");
        builder.add(ModBlocks.FLOWERING_GRASS_BLOCK.value(), "Flowering Grass Block");
        builder.add(ModBlocks.YELLOW_PETAL_BLOCK.value(), "Yellow Petal Block");
        builder.add(ModBlocks.RED_PETAL_BLOCK.value(), "Red Petal Block");
        builder.add(ModBlocks.PINK_PETAL_BLOCK.value(), "Pink Petal Block");
        builder.add(ModBlocks.ORANGE_PETAL_BLOCK.value(), "Orange Petal Block");
        builder.add(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), "Moobloom");
        builder.add(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), "Cluckbloom");
        builder.addSpawnEgg(ModItems.MOOBLOOM_SPAWN_EGG.value(), "Moobloom");
        builder.addSpawnEgg(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), "Cluckbloom");
        builder.blockFamily("Stemwood", "Stemwood Planks")
                .generateFor(ModBlockFamilies.STEMWOOD_FAMILY.getWoodenFamily());
        builder.addBlock(ModBlockFamilies.STEMWOOD_FAMILY.hangingSignBlock(), "Stemwood Hanging Sign");
        builder.add(ModBlockFamilies.STEMWOOD_FAMILY.boatItem().value(), "Stemwood Boat");
        builder.add(ModBlockFamilies.STEMWOOD_FAMILY.chestBoatItem().value(), "Stemwood Chest Boat");
        builder.add(ModBlockFamilies.STEMWOOD_FAMILY.boatEntityType().value(), "Stemwood Boat");
        builder.add(ModBlockFamilies.STEMWOOD_FAMILY.chestBoatEntityType().value(), "Stemwood Chest Boat");
    }
}

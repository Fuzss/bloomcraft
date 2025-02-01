package fuzs.bloomcraft.data;

import fuzs.bloomcraft.init.BlockFamilyRegistrar;
import fuzs.bloomcraft.init.ModBlockFamilies;
import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.oneToOneConversionRecipe(Items.YELLOW_DYE, ModItems.BUTTERCUP.value(), getItemName(Items.YELLOW_DYE));
        this.oneToOneConversionRecipe(Items.PINK_DYE, ModItems.PINK_DAISY.value(), getItemName(Items.PINK_DYE));
        this.oneToOneConversionRecipe(Items.RED_DYE, ModItems.ROSE.value(), getItemName(Items.RED_DYE));
        this.oneToOneConversionRecipe(Items.YELLOW_DYE,
                ModItems.YELLOW_PETAL_BLOCK.value(),
                getItemName(Items.YELLOW_DYE),
                4);
        this.oneToOneConversionRecipe(Items.RED_DYE, ModItems.RED_PETAL_BLOCK.value(), getItemName(Items.RED_DYE), 4);
        this.oneToOneConversionRecipe(Items.PINK_DYE,
                ModItems.PINK_PETAL_BLOCK.value(),
                getItemName(Items.PINK_DYE),
                4);
        this.oneToOneConversionRecipe(Items.ORANGE_DYE,
                ModItems.ORANGE_PETAL_BLOCK.value(),
                getItemName(Items.ORANGE_DYE),
                4);
        this.generateForBlockFamilies(ModBlockFamilies.getAllFamilies());
        this.planksFromLog(ModItems.STEMWOOD_PLANKS.value(), ModRegistry.STEMWOOD_LOGS_ITEM_TAG, 4);
        this.woodFromLogs(ModItems.STRIPPED_STEMWOOD_WOOD.value(), ModItems.STRIPPED_STEMWOOD_LOG.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            if (registrar.hangingSignItem() != null) {
                this.hangingSign(registrar.hangingSignItem().value(), registrar.getBaseBlock().value());
            }
            if (registrar.boatItem() != null) {
                this.woodenBoat(registrar.boatItem().value(), registrar.getBaseBlock().value());
                if (registrar.chestBoatItem() != null) {
                    this.chestBoat(registrar.chestBoatItem().value(), registrar.boatItem().value());
                }
            }
        });
    }
}

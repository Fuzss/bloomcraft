package fuzs.bloomcraft.data;

import fuzs.bloomcraft.init.ModItems;
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
    }
}

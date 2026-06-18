package fuzs.bloomcraft.common.data;

import fuzs.bloomcraft.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.oneToOneConversionRecipe(Items.DYE.yellow(), ModItems.BUTTERCUP.value(), getItemName(Items.DYE.yellow()));
        this.oneToOneConversionRecipe(Items.DYE.pink(), ModItems.PINK_DAISY.value(), getItemName(Items.DYE.pink()));
    }
}

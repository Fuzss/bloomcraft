package fuzs.bloomcraft.common.data.recipes;

import fuzs.bloomcraft.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        this.oneToOneConversionRecipe(Items.DYE.yellow(), ModItems.BUTTERCUP.value(), getItemName(Items.DYE.yellow()));
        this.oneToOneConversionRecipe(Items.DYE.pink(), ModItems.PINK_DAISY.value(), getItemName(Items.DYE.pink()));
    }
}

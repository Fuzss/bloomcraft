package fuzs.bloomcraft.neoforge;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.bloomcraft.common.data.recipes.ModRecipeProvider;
import fuzs.bloomcraft.common.data.loot.ModBlockLootProvider;
import fuzs.bloomcraft.common.data.loot.ModEntityLootProvider;
import fuzs.bloomcraft.common.data.loot.ModShearingLootProvider;
import fuzs.bloomcraft.common.data.tags.ModBiomeTagsProvider;
import fuzs.bloomcraft.common.data.tags.ModBlockTagsProvider;
import fuzs.bloomcraft.common.data.tags.ModEntityTypeTagsProvider;
import fuzs.bloomcraft.common.init.CluckbloomVariants;
import fuzs.bloomcraft.common.init.MoobloomVariants;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(Bloomcraft.MOD_ID)
public class BloomcraftNeoForge {

    public BloomcraftNeoForge() {
        ModConstructor.construct(Bloomcraft.MOD_ID, Bloomcraft::new);
        DataProviderBuilder.of(Bloomcraft.MOD_ID)
                .addWorldBootstrap(MoobloomVariants.MOOBLOOM_VARIANT_KEY, MoobloomVariants::bootstrap)
                .addWorldBootstrap(CluckbloomVariants.CLUCKBLOOM_VARIANT_KEY, CluckbloomVariants::bootstrap)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addLootProvider(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                .addLootProvider(ModShearingLootProvider::new, LootContextParamSets.SHEARING)
                .addProvider(ModBlockTagsProvider::new, ModEntityTypeTagsProvider::new, ModBiomeTagsProvider::new)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}

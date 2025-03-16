package fuzs.bloomcraft.neoforge;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.data.ModDatapackRegistriesProvider;
import fuzs.bloomcraft.data.ModRecipeProvider;
import fuzs.bloomcraft.data.loot.ModBlockLootProvider;
import fuzs.bloomcraft.data.loot.ModEntityLootProvider;
import fuzs.bloomcraft.data.loot.ModShearingLootProvider;
import fuzs.bloomcraft.data.tags.ModBiomeTagProvider;
import fuzs.bloomcraft.data.tags.ModBlockTagProvider;
import fuzs.bloomcraft.data.tags.ModEntityTypeTagProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(Bloomcraft.MOD_ID)
public class BloomcraftNeoForge {

    public BloomcraftNeoForge() {
        ModConstructor.construct(Bloomcraft.MOD_ID, Bloomcraft::new);
        DataProviderHelper.registerDataProviders(Bloomcraft.MOD_ID,
                ModDatapackRegistriesProvider::new,
                ModBlockLootProvider::new,
                ModEntityLootProvider::new,
                ModShearingLootProvider::new,
                ModBlockTagProvider::new,
                ModEntityTypeTagProvider::new,
                ModBiomeTagProvider::new,
                ModRecipeProvider::new);
    }
}

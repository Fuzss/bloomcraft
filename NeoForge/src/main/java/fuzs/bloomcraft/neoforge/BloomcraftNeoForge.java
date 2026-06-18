package fuzs.bloomcraft.neoforge;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.bloomcraft.common.data.ModRecipeProvider;
import fuzs.bloomcraft.common.data.loot.ModBlockLootProvider;
import fuzs.bloomcraft.common.data.loot.ModEntityLootProvider;
import fuzs.bloomcraft.common.data.loot.ModShearingLootProvider;
import fuzs.bloomcraft.common.data.tags.ModBiomeTagsProvider;
import fuzs.bloomcraft.common.data.tags.ModBlockTagsProvider;
import fuzs.bloomcraft.common.data.tags.ModEntityTypeTagsProvider;
import fuzs.bloomcraft.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(Bloomcraft.MOD_ID)
public class BloomcraftNeoForge {

    public BloomcraftNeoForge() {
        ModConstructor.construct(Bloomcraft.MOD_ID, Bloomcraft::new);
        DataProviderHelper.registerDataProviders(Bloomcraft.MOD_ID,
                ModRegistry.REGISTRY_SET_BUILDER,
                ModBlockLootProvider::new,
                ModEntityLootProvider::new,
                ModShearingLootProvider::new,
                ModBlockTagsProvider::new,
                ModEntityTypeTagsProvider::new,
                ModBiomeTagsProvider::new,
                ModRecipeProvider::new);
    }
}

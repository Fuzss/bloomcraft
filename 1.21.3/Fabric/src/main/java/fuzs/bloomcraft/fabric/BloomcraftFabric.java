package fuzs.bloomcraft.fabric;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;
import terrablender.api.TerraBlenderApi;

public class BloomcraftFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Bloomcraft.MOD_ID, Bloomcraft::new);
    }

    @Override
    public void onTerraBlenderInitialized() {
        ModRegistry.registerTerrablenderRegions();
    }
}

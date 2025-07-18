package fuzs.bloomcraft.fabric;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class BloomcraftFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Bloomcraft.MOD_ID, Bloomcraft::new);
    }
}

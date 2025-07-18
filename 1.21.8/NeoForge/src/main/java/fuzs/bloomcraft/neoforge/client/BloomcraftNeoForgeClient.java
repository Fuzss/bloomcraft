package fuzs.bloomcraft.neoforge.client;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.client.BloomcraftClient;
import fuzs.bloomcraft.data.client.ModLanguageProvider;
import fuzs.bloomcraft.data.client.ModModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Bloomcraft.MOD_ID, dist = Dist.CLIENT)
public class BloomcraftNeoForgeClient {

    public BloomcraftNeoForgeClient() {
        ClientModConstructor.construct(Bloomcraft.MOD_ID, BloomcraftClient::new);
        DataProviderHelper.registerDataProviders(Bloomcraft.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}

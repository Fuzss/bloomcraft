package fuzs.bloomcraft.neoforge.client;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.bloomcraft.common.client.BloomcraftClient;
import fuzs.bloomcraft.common.data.client.ModLanguageProvider;
import fuzs.bloomcraft.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Bloomcraft.MOD_ID, dist = Dist.CLIENT)
public class BloomcraftNeoForgeClient {

    public BloomcraftNeoForgeClient() {
        ClientModConstructor.construct(Bloomcraft.MOD_ID, BloomcraftClient::new);
        DataProviderBuilder.of(Bloomcraft.MOD_ID).addProvider(ModLanguageProvider::new, ModModelProvider::new);
    }
}

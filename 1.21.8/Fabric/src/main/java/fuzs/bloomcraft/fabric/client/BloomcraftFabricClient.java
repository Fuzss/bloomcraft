package fuzs.bloomcraft.fabric.client;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.client.BloomcraftClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class BloomcraftFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(Bloomcraft.MOD_ID, BloomcraftClient::new);
    }
}

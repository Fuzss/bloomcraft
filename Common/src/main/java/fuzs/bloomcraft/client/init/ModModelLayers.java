package fuzs.bloomcraft.client.init;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.puzzleslib.api.client.init.v1.ModelLayerFactory;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    static final ModelLayerFactory MODEL_LAYERS = ModelLayerFactory.from(Bloomcraft.MOD_ID);
    public static final ModelLayerLocation MOOBLOOM = MODEL_LAYERS.registerModelLayer("moobloom");
    public static final ModelLayerLocation MOOBLOOM_BABY = MODEL_LAYERS.registerModelLayer("moobloom_baby");
    public static final ModelLayerLocation CLUCKBLOOM = MODEL_LAYERS.registerModelLayer("cluckbloom");
    public static final ModelLayerLocation CLUCKBLOOM_BABY = MODEL_LAYERS.registerModelLayer("cluckbloom_baby");
}

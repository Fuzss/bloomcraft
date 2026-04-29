package fuzs.bloomcraft.common.client;

import fuzs.bloomcraft.common.client.init.ModModelLayers;
import fuzs.bloomcraft.common.client.renderer.entity.CluckbloomRenderer;
import fuzs.bloomcraft.common.client.renderer.entity.MoobloomRenderer;
import fuzs.bloomcraft.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.LayerDefinitionsContext;
import net.minecraft.client.model.animal.cow.CowModel;

public class BloomcraftClient implements ClientModConstructor {

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), MoobloomRenderer::new);
        context.registerEntityRenderer(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), CluckbloomRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.MOOBLOOM, MoobloomRenderer::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.MOOBLOOM_BABY,
                () -> MoobloomRenderer.createBodyLayer().apply(CowModel.BABY_TRANSFORMER));
        context.registerLayerDefinition(ModModelLayers.CLUCKBLOOM, CluckbloomRenderer::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKBLOOM_BABY,
                () -> CluckbloomRenderer.createBodyLayer().apply(CluckbloomRenderer.BABY_TRANSFORMER));
    }
}

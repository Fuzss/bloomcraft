package fuzs.bloomcraft.client;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.model.CluckbloomModel;
import fuzs.bloomcraft.client.model.MoobloomModel;
import fuzs.bloomcraft.client.renderer.entity.CluckbloomRenderer;
import fuzs.bloomcraft.client.renderer.entity.MoobloomRenderer;
import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class BloomcraftClient implements ClientModConstructor {

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), MoobloomRenderer::new);
        context.registerEntityRenderer(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), CluckbloomRenderer::new);
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        context.registerRenderType(RenderType.cutout(), ModBlocks.BUTTERCUP.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.PINK_DAISY.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_BUTTERCUP.value());
        context.registerRenderType(RenderType.cutout(), ModBlocks.POTTED_PINK_DAISY.value());
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.MOOBLOOM, MoobloomModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKBLOOM, CluckbloomModel::createBodyLayer);
    }
}

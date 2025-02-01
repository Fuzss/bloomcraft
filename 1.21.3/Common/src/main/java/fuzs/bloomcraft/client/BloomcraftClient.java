package fuzs.bloomcraft.client;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.renderer.entity.CluckbloomRenderer;
import fuzs.bloomcraft.client.renderer.entity.MoobloomRenderer;
import fuzs.bloomcraft.init.BlockFamilyRegistrar;
import fuzs.bloomcraft.init.ModBlockFamilies;
import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientAbstractions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class BloomcraftClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        ModBlockFamilies.getAllFamilyRegistrars()
                .map(BlockFamilyRegistrar::getWoodType)
                .forEach(ClientAbstractions.INSTANCE::registerWoodType);
    }

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModRegistry.MOOBLOOM_ENTITY_TYPE.value(), MoobloomRenderer::new);
        context.registerEntityRenderer(ModRegistry.CLUCKBLOOM_ENTITY_TYPE.value(), CluckbloomRenderer::new);
        context.registerEntityRenderer(ModBlockFamilies.STEMWOOD_FAMILY.boatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX, ModModelLayers.STEMWOOD_BOAT));
        context.registerEntityRenderer(ModBlockFamilies.STEMWOOD_FAMILY.chestBoatEntityType().value(),
                (EntityRendererProvider.Context contextX) -> new BoatRenderer(contextX,
                        ModModelLayers.STEMWOOD_CHEST_BOAT));
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        context.registerRenderType(RenderType.cutout(),
                ModBlocks.BUTTERCUP.value(),
                ModBlocks.PINK_DAISY.value(),
                ModBlocks.ROSE.value(),
                ModBlocks.POTTED_BUTTERCUP.value(),
                ModBlocks.POTTED_PINK_DAISY.value(),
                ModBlocks.POTTED_ROSE.value());
        ModBlockFamilies.getAllFamilyRegistrars()
                .mapMulti((BlockFamilyRegistrar registrar, Consumer<Holder.Reference<Block>> consumer) -> {
                    consumer.accept(registrar.getBlock(BlockFamily.Variant.DOOR));
                    consumer.accept(registrar.getBlock(BlockFamily.Variant.TRAPDOOR));
                })
                .map(Holder.Reference::value)
                .forEach((Block block) -> {
                    context.registerRenderType(RenderType.cutout(), block);
                });
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.MOOBLOOM, CowModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.MOOBLOOM_BABY,
                () -> CowModel.createBodyLayer().apply(CowModel.BABY_TRANSFORMER));
        context.registerLayerDefinition(ModModelLayers.CLUCKBLOOM, CluckbloomRenderer::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.CLUCKBLOOM_BABY,
                () -> CluckbloomRenderer.createBodyLayer().apply(ChickenModel.BABY_TRANSFORMER));
    }
}

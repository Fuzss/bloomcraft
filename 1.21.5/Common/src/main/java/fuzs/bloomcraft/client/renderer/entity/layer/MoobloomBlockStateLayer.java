package fuzs.bloomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.bloomcraft.client.renderer.entity.state.BlockStateCarrierRenderState;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Copied from {@link net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer}, replacing the render state
 * with one that supports our custom variants.
 */
public class MoobloomBlockStateLayer<T extends LivingEntityRenderState & BlockStateCarrierRenderState> extends RenderLayer<T, CowModel> {
    private final BlockRenderDispatcher blockRenderer;

    public MoobloomBlockStateLayer(RenderLayerParent<T, CowModel> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T renderState, float yRot, float xRot) {
        if (!renderState.isBaby) {
            boolean outlineOnly = renderState.appearsGlowing && renderState.isInvisible;
            if (!renderState.isInvisible || outlineOnly) {
                BlockState blockState = renderState.blockState();
                int overlayCoords = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);

                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        overlayCoords,
                        bakedModel);
                poseStack.popPose();

                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        overlayCoords,
                        bakedModel);
                poseStack.popPose();

                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderMushroomBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        overlayCoords,
                        bakedModel);
                poseStack.popPose();
            }
        }
    }

    private void renderMushroomBlock(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState state, int packedOverlay, BakedModel model) {
        if (outlineOnly) {
            this.blockRenderer.getModelRenderer()
                    .renderModel(poseStack.last(),
                            buffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)),
                            state,
                            model,
                            0.0F,
                            0.0F,
                            0.0F,
                            packedLight,
                            packedOverlay);
        } else {
            this.blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}

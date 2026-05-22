package fuzs.bloomcraft.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.bloomcraft.client.model.CluckbloomModel;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer
 */
public class CluckbloomBlockStateLayer extends RenderLayer<Cluckbloom, CluckbloomModel<Cluckbloom>> {
    private final BlockRenderDispatcher blockRenderer;

    public CluckbloomBlockStateLayer(RenderLayerParent<Cluckbloom, CluckbloomModel<Cluckbloom>> renderer, BlockRenderDispatcher blockRenderer) {
        super(renderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Cluckbloom cluckbloom, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!cluckbloom.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean outlineOnly = minecraft.shouldEntityAppearGlowing(cluckbloom) && cluckbloom.isInvisible();
            if (!cluckbloom.isInvisible() || outlineOnly) {
                BlockState blockState = cluckbloom.getFlowerVariant().value().blockState();
                int packedOverlay = LivingEntityRenderer.getOverlayCoords(cluckbloom, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(-0.03F, 0.58F, 0.09F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-6.0F));
                poseStack.scale(-0.5F, -0.5F, 0.5F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        packedOverlay,
                        bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().head.translateAndRotate(poseStack);
                poseStack.translate(0.03F, -0.6F, -0.03F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.5F, -0.5F, 0.5F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderBlock(poseStack,
                        bufferSource,
                        packedLight,
                        outlineOnly,
                        blockState,
                        packedOverlay,
                        bakedModel);
                poseStack.popPose();
            }
        }
    }

    /**
     * @see net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer#renderMushroomBlock(PoseStack,
     *         MultiBufferSource, int, boolean, BlockState, int, BakedModel)
     */
    private void renderBlock(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean outlineOnly, BlockState state, int packedOverlay, BakedModel model) {
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

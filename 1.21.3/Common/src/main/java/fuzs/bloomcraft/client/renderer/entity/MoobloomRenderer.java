package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.renderer.entity.layer.MoobloomBlockStateLayer;
import fuzs.bloomcraft.client.renderer.entity.state.MoobloomRenderState;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class MoobloomRenderer extends AgeableMobRenderer<Moobloom, MoobloomRenderState, CowModel> {

    public MoobloomRenderer(Context context) {
        super(context,
                new CowModel(context.bakeLayer(ModModelLayers.MOOBLOOM)),
                new CowModel(context.bakeLayer(ModModelLayers.MOOBLOOM_BABY)),
                0.7F);
        this.addLayer(new MoobloomBlockStateLayer<>(this, context.getBlockRenderDispatcher()));
    }

    /**
     * Copied from Minecraft 1.21.5+ to support changed texture dimensions.
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = createBaseCowModel();
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    /**
     * Copied from Minecraft 1.21.5+ to support changed texture dimensions.
     */
    static MeshDefinition createBaseCowModel() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F)
                        .texOffs(1, 33)
                        .addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F)
                        .texOffs(22, 0)
                        .addBox("right_horn", -5.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(22, 0)
                        .addBox("left_horn", 4.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, -8.0F));
        partDefinition.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(18, 4)
                        .addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F)
                        .texOffs(52, 0)
                        .addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create()
                .mirror()
                .texOffs(0, 16)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        CubeListBuilder cubeListBuilder2 = CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, -5.0F));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, -5.0F));
        return meshDefinition;
    }

    @Override
    public ResourceLocation getTextureLocation(MoobloomRenderState renderState) {
        return renderState.textureLocation;
    }

    @Override
    public MoobloomRenderState createRenderState() {
        return new MoobloomRenderState();
    }

    @Override
    public void extractRenderState(Moobloom moobloom, MoobloomRenderState reusedState, float partialTick) {
        super.extractRenderState(moobloom, reusedState, partialTick);
        reusedState.textureLocation = FlowerMobVariant.transformTextureLocation(moobloom.getVariant()
                .value()
                .textureLocation());
        reusedState.blockState = moobloom.getVariant().value().blockState();
    }
}

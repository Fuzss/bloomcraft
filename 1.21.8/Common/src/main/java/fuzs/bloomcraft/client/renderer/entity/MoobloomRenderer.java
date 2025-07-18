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

    public static LayerDefinition createBodyLayer() {
        return CowModel.createBodyLayer().apply((MeshDefinition meshDefinition) -> {
            PartDefinition partDefinition = meshDefinition.getRoot();
            // remove udder
            partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F),
                    PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
            return meshDefinition;
        });
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
        reusedState.textureLocation = FlowerMobVariant.transformTextureLocation(moobloom.getFlowerVariant()
                .value()
                .textureLocation());
        reusedState.blockState = moobloom.getFlowerVariant().value().blockState();
    }
}

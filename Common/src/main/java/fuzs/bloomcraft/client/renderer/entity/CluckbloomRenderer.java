package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.renderer.entity.layer.CluckbloomBlockStateLayer;
import fuzs.bloomcraft.client.renderer.entity.state.CluckbloomRenderState;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CluckbloomRenderer extends AgeableMobRenderer<Cluckbloom, CluckbloomRenderState, ChickenModel> {

    public CluckbloomRenderer(Context context) {
        super(context,
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKBLOOM)),
                new ChickenModel(context.bakeLayer(ModModelLayers.CLUCKBLOOM_BABY)),
                0.3F);
        this.addLayer(new CluckbloomBlockStateLayer<>(this, context.getBlockRenderDispatcher()));
    }

    public static LayerDefinition createBodyLayer() {
        // fix rotation point to be at body and not in air
        return ChickenModel.createBodyLayer().apply(meshDefinition -> {
            PartDefinition partDefinition = meshDefinition.getRoot();
            partDefinition.addOrReplaceChild("left_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(3.0F, 13.0F, 0.0F));
            partDefinition.addOrReplaceChild("right_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(-3.0F, 13.0F, 0.0F));
            return meshDefinition;
        });
    }

    @Override
    public ResourceLocation getTextureLocation(CluckbloomRenderState renderState) {
        return renderState.textureLocation;
    }

    @Override
    public CluckbloomRenderState createRenderState() {
        return new CluckbloomRenderState();
    }

    @Override
    public void extractRenderState(Cluckbloom cluckbloom, CluckbloomRenderState reusedState, float partialTick) {
        super.extractRenderState(cluckbloom, reusedState, partialTick);
        reusedState.flap = Mth.lerp(partialTick, cluckbloom.oFlap, cluckbloom.flap);
        reusedState.flapSpeed = Mth.lerp(partialTick, cluckbloom.oFlapSpeed, cluckbloom.flapSpeed);
        reusedState.textureLocation = FlowerMobVariant.transformTextureLocation(cluckbloom.getVariant()
                .value()
                .textureLocation());
        reusedState.blockState = cluckbloom.getVariant().value().blockState();
    }
}

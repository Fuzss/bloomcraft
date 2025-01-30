package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.renderer.entity.layer.MoobloomBlockStateLayer;
import fuzs.bloomcraft.client.renderer.entity.state.MoobloomRenderState;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import net.minecraft.client.model.CowModel;
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

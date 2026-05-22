package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.model.MoobloomModel;
import fuzs.bloomcraft.client.renderer.entity.layer.MoobloomBlockStateLayer;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MoobloomRenderer extends MobRenderer<Moobloom, MoobloomModel<Moobloom>> {

    public MoobloomRenderer(Context context) {
        super(context, new MoobloomModel<>(context.bakeLayer(ModModelLayers.MOOBLOOM)), 0.7F);
        this.addLayer(new MoobloomBlockStateLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(Moobloom moobloom) {
        return FlowerMobVariant.transformTextureLocation(moobloom.getFlowerVariant().value().textureLocation());
    }
}

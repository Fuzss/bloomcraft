package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.model.CluckbloomModel;
import fuzs.bloomcraft.client.renderer.entity.layer.CluckbloomBlockStateLayer;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CluckbloomRenderer extends MobRenderer<Cluckbloom, CluckbloomModel<Cluckbloom>> {

    public CluckbloomRenderer(Context context) {
        super(context, new CluckbloomModel<>(context.bakeLayer(ModModelLayers.CLUCKBLOOM)), 0.3F);
        this.addLayer(new CluckbloomBlockStateLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(Cluckbloom cluckbloom) {
        return FlowerMobVariant.transformTextureLocation(cluckbloom.getFlowerVariant().value().textureLocation());
    }

    @Override
    protected float getBob(Cluckbloom cluckbloom, float partialTicks) {
        float f = Mth.lerp(partialTicks, cluckbloom.oFlap, cluckbloom.flap);
        float g = Mth.lerp(partialTicks, cluckbloom.oFlapSpeed, cluckbloom.flapSpeed);
        return (Mth.sin(f) + 1.0F) * g;
    }
}

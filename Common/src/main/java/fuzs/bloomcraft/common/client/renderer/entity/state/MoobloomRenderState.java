package fuzs.bloomcraft.common.client.renderer.entity.state;

import fuzs.bloomcraft.common.init.CluckbloomVariants;
import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class MoobloomRenderState extends LivingEntityRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModEntityTypes.MOOBLOOM_ENTITY_TYPE,
            CluckbloomVariants.BUTTERCUP));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}

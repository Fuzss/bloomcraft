package fuzs.bloomcraft.common.client.renderer.entity.state;

import fuzs.bloomcraft.common.init.ModCluckbloomVariants;
import fuzs.bloomcraft.common.init.ModRegistry;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class MoobloomRenderState extends LivingEntityRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.MOOBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.BUTTERCUP));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}

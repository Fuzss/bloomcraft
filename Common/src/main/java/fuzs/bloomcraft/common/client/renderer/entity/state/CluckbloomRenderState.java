package fuzs.bloomcraft.common.client.renderer.entity.state;

import fuzs.bloomcraft.common.init.ModCluckbloomVariants;
import fuzs.bloomcraft.common.init.ModRegistry;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class CluckbloomRenderState extends ChickenRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.CLUCKBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.PINK_DAISY));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}

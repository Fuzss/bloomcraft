package fuzs.bloomcraft.common.client.renderer.entity.state;

import fuzs.bloomcraft.common.init.CluckbloomVariants;
import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class CluckbloomRenderState extends ChickenRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE,
            CluckbloomVariants.PINK_DAISY));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
}

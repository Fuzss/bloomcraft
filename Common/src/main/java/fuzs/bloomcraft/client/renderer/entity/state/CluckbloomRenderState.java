package fuzs.bloomcraft.client.renderer.entity.state;

import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class CluckbloomRenderState extends ChickenRenderState implements BlockStateCarrierRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.CLUCKBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.PINK_DAISY));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();

    @Override
    public BlockModelRenderState blockModel() {
        return this.blockModel;
    }
}

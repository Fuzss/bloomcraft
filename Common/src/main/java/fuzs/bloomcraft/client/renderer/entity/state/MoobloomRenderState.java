package fuzs.bloomcraft.client.renderer.entity.state;

import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class MoobloomRenderState extends LivingEntityRenderState implements BlockStateCarrierRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.MOOBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.BUTTERCUP));
    public final BlockModelRenderState blockModel = new BlockModelRenderState();

    @Override
    public BlockModelRenderState blockModel() {
        return this.blockModel;
    }
}

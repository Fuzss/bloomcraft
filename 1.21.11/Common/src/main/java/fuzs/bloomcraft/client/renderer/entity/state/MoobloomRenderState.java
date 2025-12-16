package fuzs.bloomcraft.client.renderer.entity.state;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

public class MoobloomRenderState extends LivingEntityRenderState implements BlockStateCarrierRenderState {
    public Identifier textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.MOOBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.BUTTERCUP));
    public BlockState blockState = ModBlocks.BUTTERCUP.value().defaultBlockState();

    @Override
    public BlockState blockState() {
        return this.blockState;
    }
}

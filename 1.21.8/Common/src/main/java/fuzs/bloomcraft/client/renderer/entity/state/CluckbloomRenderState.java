package fuzs.bloomcraft.client.renderer.entity.state;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class CluckbloomRenderState extends ChickenRenderState implements BlockStateCarrierRenderState {
    public ResourceLocation textureLocation = FlowerMobVariant.transformTextureLocation(FlowerMobVariant.getTextureLocation(
            ModRegistry.CLUCKBLOOM_ENTITY_TYPE,
            ModCluckbloomVariants.PINK_DAISY));
    public BlockState blockState = ModBlocks.PINK_DAISY.value().defaultBlockState();

    @Override
    public BlockState blockState() {
        return this.blockState;
    }
}

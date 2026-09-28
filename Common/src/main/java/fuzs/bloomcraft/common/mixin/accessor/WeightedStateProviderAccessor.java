package fuzs.bloomcraft.common.mixin.accessor;

import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WeightedStateProvider.class)
public interface WeightedStateProviderAccessor {
    @Accessor("weightedList")
    @Mutable
    void bloomcraft$setWeightedList(WeightedList<BlockState> weightedList);
}

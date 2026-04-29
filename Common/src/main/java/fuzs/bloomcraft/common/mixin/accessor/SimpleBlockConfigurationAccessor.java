package fuzs.bloomcraft.common.mixin.accessor;

import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SimpleBlockConfiguration.class)
public interface SimpleBlockConfigurationAccessor {
    @Accessor("toPlace")
    @Mutable
    void bloomcraft$setToPlace(BlockStateProvider toPlace);
}

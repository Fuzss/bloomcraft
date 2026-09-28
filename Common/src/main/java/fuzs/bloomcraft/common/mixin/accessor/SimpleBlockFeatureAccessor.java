package fuzs.bloomcraft.common.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SimpleBlockFeature.class)
public interface SimpleBlockFeatureAccessor {
    @Accessor("toPlace")
    @Mutable
    void bloomcraft$setToPlace(Holder<BlockStateProvider> toPlace);
}

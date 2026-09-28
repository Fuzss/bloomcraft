package fuzs.bloomcraft.common.init;

import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;

public class ModDataComponentTypes {
    public static final Holder.Reference<DataComponentType<Holder<FlowerMobVariant>>> MOOBLOOM_VARIANT_DATA_COMPONENT_TYPE = ModRegistry.REGISTRIES.registerDataComponentType(
            "moobloom/variant",
            (DataComponentType.Builder<Holder<FlowerMobVariant>> builder) -> builder.persistent(FlowerMobVariant.codec(
                            MoobloomVariants.MOOBLOOM_VARIANT_KEY))
                    .networkSynchronized(FlowerMobVariant.streamCodec(MoobloomVariants.MOOBLOOM_VARIANT_KEY)));
    public static final Holder.Reference<DataComponentType<Holder<FlowerMobVariant>>> CLUCKBLOOM_VARIANT_DATA_COMPONENT_TYPE = ModRegistry.REGISTRIES.registerDataComponentType(
            "cluckbloom/variant",
            (DataComponentType.Builder<Holder<FlowerMobVariant>> builder) -> builder.persistent(FlowerMobVariant.codec(
                            CluckbloomVariants.CLUCKBLOOM_VARIANT_KEY))
                    .networkSynchronized(FlowerMobVariant.streamCodec(CluckbloomVariants.CLUCKBLOOM_VARIANT_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}

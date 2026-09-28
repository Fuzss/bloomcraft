package fuzs.bloomcraft.common.init;

import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;

public class ModEntityDataSerializers {
    public static final Holder.Reference<EntityDataSerializer<Holder<FlowerMobVariant>>> MOOBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "moobloom_variant",
            () -> EntityDataSerializer.forValueType(FlowerMobVariant.streamCodec(MoobloomVariants.MOOBLOOM_VARIANT_KEY)));
    public static final Holder.Reference<EntityDataSerializer<Holder<FlowerMobVariant>>> CLUCKBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = ModRegistry.REGISTRIES.registerEntityDataSerializer(
            "cluckbloom_variant",
            () -> EntityDataSerializer.forValueType(FlowerMobVariant.streamCodec(CluckbloomVariants.CLUCKBLOOM_VARIANT_KEY)));

    public static void bootstrap() {
        // NO-OP
    }
}

package fuzs.bloomcraft.common.init;

import fuzs.bloomcraft.common.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.common.world.entity.animal.Moobloom;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

public class ModEntityTypes {
    public static final Holder.Reference<EntityType<Moobloom>> MOOBLOOM_ENTITY_TYPE = ModRegistry.REGISTRIES.registerEntityType(
            "moobloom",
            () -> EntityType.Builder.of(Moobloom::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10));
    public static final Holder.Reference<EntityType<Cluckbloom>> CLUCKBLOOM_ENTITY_TYPE = ModRegistry.REGISTRIES.registerEntityType(
            "cluckbloom",
            () -> EntityType.Builder.of(Cluckbloom::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10));

    public static void bootstrap() {
        // NO-OP
    }
}

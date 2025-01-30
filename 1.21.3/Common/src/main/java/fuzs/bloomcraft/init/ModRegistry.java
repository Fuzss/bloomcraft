package fuzs.bloomcraft.init;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariantUtil;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SyncedDataHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public class ModRegistry {
    public static final ResourceKey<Registry<FlowerMobVariant>> MOOBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Bloomcraft.id("moobloom_variant"));
    public static final ResourceKey<Registry<FlowerMobVariant>> CLUCKBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Bloomcraft.id("cluckbloom_variant"));

    static final RegistryManager REGISTRIES = RegistryManager.from(Bloomcraft.MOD_ID);
    public static final Holder.Reference<EntityType<Moobloom>> MOOBLOOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "moobloom",
            () -> EntityType.Builder.of(FlowerMobVariantUtil::moobloom, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10));
    public static final Holder.Reference<EntityType<Cluckbloom>> CLUCKBLOOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "cluckbloom",
            () -> EntityType.Builder.of(FlowerMobVariantUtil::cluckbloom, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.BUTTERCUP);
    public static final Holder.Reference<EntityDataSerializer<Holder<FlowerMobVariant>>> MOOBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "moobloom_variant",
            () -> EntityDataSerializer.forValueType(FlowerMobVariant.streamCodec(MOOBLOOM_VARIANT_REGISTRY_KEY)));
    public static final Holder.Reference<EntityDataSerializer<Holder<FlowerMobVariant>>> CLUCKBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "cluckbloom_variant",
            () -> EntityDataSerializer.forValueType(FlowerMobVariant.streamCodec(CLUCKBLOOM_VARIANT_REGISTRY_KEY)));
    public static final Function<Class<? extends SyncedDataHolder>, EntityDataAccessor<Holder<FlowerMobVariant>>> MOOBLOOM_VARIANT_DATA_TYPE = Util.memoize(
            (Class<? extends SyncedDataHolder> clazz) -> {
                return SynchedEntityData.defineId(clazz, ModRegistry.MOOBLOOM_VARIANT_ENTITY_DATA_SERIALIZER.value());
            });
    public static final Function<Class<? extends SyncedDataHolder>, EntityDataAccessor<Holder<FlowerMobVariant>>> CLUCKBLOOM_VARIANT_DATA_TYPE = Util.memoize(
            (Class<? extends SyncedDataHolder> clazz) -> {
                return SynchedEntityData.defineId(clazz, ModRegistry.CLUCKBLOOM_VARIANT_ENTITY_DATA_SERIALIZER.value());
            });

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModCluckbloomVariants.bootstrap();
        ModMoobloomVariants.bootstrap();
    }
}

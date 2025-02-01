package fuzs.bloomcraft.init;

import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

public class ModRegistry {
    public static final ResourceKey<Registry<MoobloomVariant>> MOOBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Bloomcraft.id("moobloom_variant"));
    public static final ResourceKey<Registry<CluckbloomVariant>> CLUCKBLOOM_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(
            Bloomcraft.id("cluckbloom_variant"));

    static final RegistryManager REGISTRIES = RegistryManager.from(Bloomcraft.MOD_ID);
    public static final Holder.Reference<EntityType<Moobloom>> MOOBLOOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "moobloom",
            () -> EntityType.Builder.of(Moobloom::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .eyeHeight(1.3F)
                    .passengerAttachments(1.36875F)
                    .clientTrackingRange(10));
    public static final Holder.Reference<EntityType<Cluckbloom>> CLUCKBLOOM_ENTITY_TYPE = REGISTRIES.registerEntityType(
            "cluckbloom",
            () -> EntityType.Builder.of(Cluckbloom::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.BUTTERCUP);
    public static final Holder.Reference<EntityDataSerializer<Holder<MoobloomVariant>>> MOOBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "moobloom_variant",
            () -> EntityDataSerializer.forValueType(MoobloomVariant.STREAM_CODEC));
    public static final Holder.Reference<EntityDataSerializer<Holder<CluckbloomVariant>>> CLUCKBLOOM_VARIANT_ENTITY_DATA_SERIALIZER = REGISTRIES.registerEntityDataSerializer(
            "cluckbloom_variant",
            () -> EntityDataSerializer.forValueType(CluckbloomVariant.STREAM_CODEC));

    static final TagFactory TAGS = TagFactory.make(Bloomcraft.MOD_ID);
    public static final TagKey<Biome> HAS_BUTTERCUP_BIOME_TAG = TAGS.registerBiomeTag("has_buttercup");
    public static final TagKey<Biome> HAS_PINK_DAISY_BIOME_TAG = TAGS.registerBiomeTag("has_pink_daisy");
    public static final TagKey<Biome> HAS_ROSE_BIOME_TAG = TAGS.registerBiomeTag("has_rose");

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModCluckbloomVariants.bootstrap();
        ModMoobloomVariants.bootstrap();
    }
}

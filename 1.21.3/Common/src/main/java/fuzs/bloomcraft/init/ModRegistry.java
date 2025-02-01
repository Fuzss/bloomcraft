package fuzs.bloomcraft.init;

import com.mojang.datafixers.util.Pair;
import fuzs.bloomcraft.Bloomcraft;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.bloomcraft.world.entity.animal.Moobloom;
import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.phys.Vec3;
import terrablender.api.*;

import java.util.function.Consumer;

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

    public static final ResourceKey<Biome> FLOWERING_GARDEN_BIOME = REGISTRIES.makeResourceKey(Registries.BIOME,
            "flowering_garden");

    static final TagFactory TAGS = TagFactory.make(Bloomcraft.MOD_ID);
    public static final TagKey<Block> STEMWOOD_LOGS_BLOCK_TAG = TAGS.registerBlockTag("stemwood_logs");
    public static final TagKey<Item> STEMWOOD_LOGS_ITEM_TAG = TAGS.registerItemTag("stemwood_logs");
    public static final TagKey<Biome> HAS_BUTTERCUP_BIOME_TAG = TAGS.registerBiomeTag("has_buttercup");
    public static final TagKey<Biome> HAS_PINK_DAISY_BIOME_TAG = TAGS.registerBiomeTag("has_pink_daisy");
    public static final TagKey<Biome> HAS_ROSE_BIOME_TAG = TAGS.registerBiomeTag("has_rose");

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModBlockFamilies.bootstrap();
        ModFeatures.bootstrap();
        ModCluckbloomVariants.bootstrap();
        ModMoobloomVariants.bootstrap();
    }

    public static void registerTerrablenderRegions() {
        SurfaceRules.RuleSource ruleSource = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(
                                FLOWERING_GARDEN_BIOME),
                        SurfaceRules.state(ModBlocks.FLOWERING_GRASS_BLOCK.value().defaultBlockState())),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(0, 0),
                                SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())),
                        SurfaceRules.state(Blocks.DIRT.defaultBlockState())));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Bloomcraft.MOD_ID, ruleSource);
        // vanilla is 10, lower values make the region more rare
        Regions.register(new Region(Bloomcraft.id("overworld"), RegionType.OVERWORLD, 5) {
            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
//                this.addBiomeSimilar(mapper, Biomes.MUSHROOM_FIELDS, FLOWERING_GARDEN_BIOME);
                this.addModifiedVanillaOverworldBiomes(mapper, ((ModifiedVanillaOverworldBuilder builder) -> {
                    builder.replaceBiome(Biomes.MUSHROOM_FIELDS, FLOWERING_GARDEN_BIOME);
                }));
            }
        });
    }
}

package fuzs.bloomcraft.init;

import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModCluckbloomVariants {
    public static final ResourceKey<FlowerMobVariant> DANDELION = register("dandelion");
    public static final ResourceKey<FlowerMobVariant> POPPY = register("poppy");
    public static final ResourceKey<FlowerMobVariant> BLUE_ORCHID = register("blue_orchid");
    public static final ResourceKey<FlowerMobVariant> ALLIUM = register("allium");
    public static final ResourceKey<FlowerMobVariant> AZURE_BLUET = register("azure_bluet");
    public static final ResourceKey<FlowerMobVariant> RED_TULIP = register("red_tulip");
    public static final ResourceKey<FlowerMobVariant> ORANGE_TULIP = register("orange_tulip");
    public static final ResourceKey<FlowerMobVariant> WHITE_TULIP = register("white_tulip");
    public static final ResourceKey<FlowerMobVariant> PINK_TULIP = register("pink_tulip");
    public static final ResourceKey<FlowerMobVariant> OXEYE_DAISY = register("oxeye_daisy");
    public static final ResourceKey<FlowerMobVariant> CORNFLOWER = register("cornflower");
    public static final ResourceKey<FlowerMobVariant> LILY_OF_THE_VALLEY = register("lily_of_the_valley");
    public static final ResourceKey<FlowerMobVariant> WITHER_ROSE = register("wither_rose");
    public static final ResourceKey<FlowerMobVariant> TORCHFLOWER = register("torchflower");
    public static final ResourceKey<FlowerMobVariant> BUTTERCUP = register("buttercup");
    public static final ResourceKey<FlowerMobVariant> PINK_DAISY = register("pink_daisy");

    private static ResourceKey<FlowerMobVariant> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, path);
    }

    public static void bootstrap(BootstrapContext<FlowerMobVariant> context) {
        register(context, DANDELION, Blocks.DANDELION);
        register(context, POPPY, Blocks.POPPY);
        register(context, BLUE_ORCHID, Blocks.BLUE_ORCHID);
        register(context, ALLIUM, Blocks.ALLIUM);
        register(context, AZURE_BLUET, Blocks.AZURE_BLUET);
        register(context, RED_TULIP, Blocks.RED_TULIP);
        register(context, ORANGE_TULIP, Blocks.ORANGE_TULIP);
        register(context, WHITE_TULIP, Blocks.WHITE_TULIP);
        register(context, PINK_TULIP, Blocks.PINK_TULIP);
        register(context, OXEYE_DAISY, Blocks.OXEYE_DAISY);
        register(context, CORNFLOWER, Blocks.CORNFLOWER);
        register(context, LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        register(context, WITHER_ROSE, Blocks.WITHER_ROSE);
        register(context, TORCHFLOWER, Blocks.TORCHFLOWER);
        register(context, BUTTERCUP, ModBlocks.BUTTERCUP.value());
        register(context, PINK_DAISY, ModBlocks.PINK_DAISY.value());
    }

    private static void register(BootstrapContext<FlowerMobVariant> context, ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        context.register(resourceKey, new FlowerMobVariant(ModRegistry.CLUCKBLOOM_ENTITY_TYPE, resourceKey, block));
    }
}

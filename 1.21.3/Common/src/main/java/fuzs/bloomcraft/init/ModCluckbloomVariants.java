package fuzs.bloomcraft.init;

import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.resources.ResourceKey;

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

    public static void bootstrap() {
        // NO-OP
    }

    private static ResourceKey<FlowerMobVariant> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, path);
    }
}

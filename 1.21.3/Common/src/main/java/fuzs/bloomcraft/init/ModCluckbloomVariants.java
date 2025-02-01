package fuzs.bloomcraft.init;

import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import net.minecraft.resources.ResourceKey;

public class ModCluckbloomVariants {
    public static final ResourceKey<CluckbloomVariant> DANDELION = register("dandelion");
    public static final ResourceKey<CluckbloomVariant> POPPY = register("poppy");
    public static final ResourceKey<CluckbloomVariant> BLUE_ORCHID = register("blue_orchid");
    public static final ResourceKey<CluckbloomVariant> ALLIUM = register("allium");
    public static final ResourceKey<CluckbloomVariant> AZURE_BLUET = register("azure_bluet");
    public static final ResourceKey<CluckbloomVariant> RED_TULIP = register("red_tulip");
    public static final ResourceKey<CluckbloomVariant> ORANGE_TULIP = register("orange_tulip");
    public static final ResourceKey<CluckbloomVariant> WHITE_TULIP = register("white_tulip");
    public static final ResourceKey<CluckbloomVariant> PINK_TULIP = register("pink_tulip");
    public static final ResourceKey<CluckbloomVariant> OXEYE_DAISY = register("oxeye_daisy");
    public static final ResourceKey<CluckbloomVariant> CORNFLOWER = register("cornflower");
    public static final ResourceKey<CluckbloomVariant> LILY_OF_THE_VALLEY = register("lily_of_the_valley");
    public static final ResourceKey<CluckbloomVariant> WITHER_ROSE = register("wither_rose");
    public static final ResourceKey<CluckbloomVariant> TORCHFLOWER = register("torchflower");
    public static final ResourceKey<CluckbloomVariant> BUTTERCUP = register("buttercup");
    public static final ResourceKey<CluckbloomVariant> PINK_DAISY = register("pink_daisy");
    public static final ResourceKey<CluckbloomVariant> ROSE = register("rose");

    public static void bootstrap() {
        // NO-OP
    }

    private static ResourceKey<CluckbloomVariant> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY, path);
    }
}

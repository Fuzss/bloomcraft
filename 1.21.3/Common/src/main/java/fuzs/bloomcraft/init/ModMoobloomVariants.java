package fuzs.bloomcraft.init;

import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import net.minecraft.resources.ResourceKey;

public class ModMoobloomVariants {
    public static final ResourceKey<MoobloomVariant> DANDELION = register("dandelion");
    public static final ResourceKey<MoobloomVariant> POPPY = register("poppy");
    public static final ResourceKey<MoobloomVariant> BLUE_ORCHID = register("blue_orchid");
    public static final ResourceKey<MoobloomVariant> ALLIUM = register("allium");
    public static final ResourceKey<MoobloomVariant> AZURE_BLUET = register("azure_bluet");
    public static final ResourceKey<MoobloomVariant> RED_TULIP = register("red_tulip");
    public static final ResourceKey<MoobloomVariant> ORANGE_TULIP = register("orange_tulip");
    public static final ResourceKey<MoobloomVariant> WHITE_TULIP = register("white_tulip");
    public static final ResourceKey<MoobloomVariant> PINK_TULIP = register("pink_tulip");
    public static final ResourceKey<MoobloomVariant> OXEYE_DAISY = register("oxeye_daisy");
    public static final ResourceKey<MoobloomVariant> CORNFLOWER = register("cornflower");
    public static final ResourceKey<MoobloomVariant> LILY_OF_THE_VALLEY = register("lily_of_the_valley");
    public static final ResourceKey<MoobloomVariant> WITHER_ROSE = register("wither_rose");
    public static final ResourceKey<MoobloomVariant> TORCHFLOWER = register("torchflower");
    public static final ResourceKey<MoobloomVariant> BUTTERCUP = register("buttercup");
    public static final ResourceKey<MoobloomVariant> PINK_DAISY = register("pink_daisy");
    public static final ResourceKey<MoobloomVariant> ROSE = register("rose");

    public static void bootstrap() {
        // NO-OP
    }

    private static ResourceKey<MoobloomVariant> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY, path);
    }
}

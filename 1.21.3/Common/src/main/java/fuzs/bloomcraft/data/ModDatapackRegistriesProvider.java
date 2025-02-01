package fuzs.bloomcraft.data;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModMoobloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.bloomcraft.world.entity.animal.MoobloomVariant;
import fuzs.puzzleslib.api.data.v2.AbstractDatapackRegistriesProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;

public class ModDatapackRegistriesProvider extends AbstractDatapackRegistriesProvider {

    public ModDatapackRegistriesProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBootstrap(RegistryBoostrapConsumer consumer) {
        consumer.add(ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
                ModDatapackRegistriesProvider::bootstrapMoobloomVariants);
        consumer.add(ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
                ModDatapackRegistriesProvider::bootstrapCluckbloomVariants);
    }

    static void bootstrapMoobloomVariants(BootstrapContext<MoobloomVariant> context) {
        registerMoobloomVariant(context, ModMoobloomVariants.DANDELION, Blocks.DANDELION);
        registerMoobloomVariant(context, ModMoobloomVariants.POPPY, Blocks.POPPY);
        registerMoobloomVariant(context, ModMoobloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        registerMoobloomVariant(context, ModMoobloomVariants.ALLIUM, Blocks.ALLIUM);
        registerMoobloomVariant(context, ModMoobloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        registerMoobloomVariant(context, ModMoobloomVariants.RED_TULIP, Blocks.RED_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        registerMoobloomVariant(context, ModMoobloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        registerMoobloomVariant(context, ModMoobloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        registerMoobloomVariant(context, ModMoobloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        registerMoobloomVariant(context, ModMoobloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        registerMoobloomVariant(context, ModMoobloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        registerMoobloomVariant(context, ModMoobloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        registerMoobloomVariant(context, ModMoobloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
        registerMoobloomVariant(context, ModMoobloomVariants.ROSE, ModBlocks.ROSE.value());
    }

    static void registerMoobloomVariant(BootstrapContext<MoobloomVariant> context, ResourceKey<MoobloomVariant> resourceKey, Block block) {
        context.register(resourceKey,
                new MoobloomVariant(ModRegistry.MOOBLOOM_ENTITY_TYPE, resourceKey, (FlowerBlock) block));
    }

    static void bootstrapCluckbloomVariants(BootstrapContext<CluckbloomVariant> context) {
        registerCluckbloomVariant(context, ModCluckbloomVariants.DANDELION, Blocks.DANDELION);
        registerCluckbloomVariant(context, ModCluckbloomVariants.POPPY, Blocks.POPPY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        registerCluckbloomVariant(context, ModCluckbloomVariants.ALLIUM, Blocks.ALLIUM);
        registerCluckbloomVariant(context, ModCluckbloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        registerCluckbloomVariant(context, ModCluckbloomVariants.RED_TULIP, Blocks.RED_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        registerCluckbloomVariant(context, ModCluckbloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        registerCluckbloomVariant(context, ModCluckbloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        registerCluckbloomVariant(context, ModCluckbloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        registerCluckbloomVariant(context, ModCluckbloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        registerCluckbloomVariant(context, ModCluckbloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        registerCluckbloomVariant(context, ModCluckbloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
        registerCluckbloomVariant(context, ModCluckbloomVariants.ROSE, ModBlocks.ROSE.value());
    }

    static void registerCluckbloomVariant(BootstrapContext<CluckbloomVariant> context, ResourceKey<CluckbloomVariant> resourceKey, Block block) {
        context.register(resourceKey, new CluckbloomVariant(ModRegistry.CLUCKBLOOM_ENTITY_TYPE, resourceKey, block));
    }
}

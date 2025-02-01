package fuzs.bloomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Holder.Reference<Item> BUTTERCUP = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BUTTERCUP);
    public static final Holder.Reference<Item> PINK_DAISY = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PINK_DAISY);
    public static final Holder.Reference<Item> ROSE = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.ROSE);
    public static final Holder.Reference<Item> FLOWERING_GRASS_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.FLOWERING_GRASS_BLOCK);
    public static final Holder.Reference<Item> YELLOW_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.YELLOW_PETAL_BLOCK);
    public static final Holder.Reference<Item> RED_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.RED_PETAL_BLOCK);
    public static final Holder.Reference<Item> PINK_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PINK_PETAL_BLOCK);
    public static final Holder.Reference<Item> ORANGE_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.ORANGE_PETAL_BLOCK);
    public static final Holder.Reference<Item> STEMWOOD_LOG = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.STEMWOOD_LOG);
    public static final Holder.Reference<Item> STEMWOOD_WOOD = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.STEMWOOD_WOOD);
    public static final Holder.Reference<Item> STRIPPED_STEMWOOD_LOG = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_STEMWOOD_LOG);
    public static final Holder.Reference<Item> STRIPPED_STEMWOOD_WOOD = ModRegistry.REGISTRIES.registerBlockItem(
            ModBlocks.STRIPPED_STEMWOOD_WOOD);
    public static final Holder.Reference<Item> STEMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.STEMWOOD_PLANKS);
    public static final Holder.Reference<Item> MOOBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModRegistry.MOOBLOOM_ENTITY_TYPE,
            -1,
            -1);
    public static final Holder.Reference<Item> CLUCKBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModRegistry.CLUCKBLOOM_ENTITY_TYPE,
            -1,
            -1);

    public static void bootstrap() {
        // NO-OP
    }
}

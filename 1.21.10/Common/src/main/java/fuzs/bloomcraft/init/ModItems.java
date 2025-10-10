package fuzs.bloomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Holder.Reference<Item> BUTTERCUP = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BUTTERCUP);
    public static final Holder.Reference<Item> PINK_DAISY = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PINK_DAISY);
    public static final Holder.Reference<Item> MOOBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModRegistry.MOOBLOOM_ENTITY_TYPE);
    public static final Holder.Reference<Item> CLUCKBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModRegistry.CLUCKBLOOM_ENTITY_TYPE);

    public static void bootstrap() {
        // NO-OP
    }
}

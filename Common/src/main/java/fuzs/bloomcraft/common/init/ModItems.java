package fuzs.bloomcraft.common.init;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModItems {
    public static final Holder.Reference<Item> BUTTERCUP = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.BUTTERCUP,
            () -> new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
    public static final Holder.Reference<Item> PINK_DAISY = ModRegistry.REGISTRIES.registerBlockItem(ModBlocks.PINK_DAISY,
            () -> new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM));
    public static final Holder.Reference<Item> MOOBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModEntityTypes.MOOBLOOM_ENTITY_TYPE);
    public static final Holder.Reference<Item> CLUCKBLOOM_SPAWN_EGG = ModRegistry.REGISTRIES.registerSpawnEggItem(
            ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE);

    public static void bootstrap() {
        // NO-OP
    }
}

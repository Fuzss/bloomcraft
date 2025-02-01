package fuzs.bloomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.bloomcraft.init.BlockFamilyRegistrar;
import fuzs.bloomcraft.init.ModBlockFamilies;
import fuzs.bloomcraft.init.ModItems;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModItemTagProvider extends AbstractTagProvider<Item> {
    static final Map<BlockFamily.Variant, TagKey<Item>> VARIANT_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Item>>builder()
            .put(BlockFamily.Variant.BUTTON, ItemTags.BUTTONS)
            .put(BlockFamily.Variant.DOOR, ItemTags.DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, ItemTags.FENCES)
            .put(BlockFamily.Variant.FENCE, ItemTags.FENCES)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, ItemTags.FENCE_GATES)
            .put(BlockFamily.Variant.FENCE_GATE, ItemTags.FENCE_GATES)
            .put(BlockFamily.Variant.SLAB, ItemTags.SLABS)
            .put(BlockFamily.Variant.STAIRS, ItemTags.STAIRS)
            .put(BlockFamily.Variant.TRAPDOOR, ItemTags.TRAPDOORS)
            .put(BlockFamily.Variant.WALL, ItemTags.WALLS)
            .build();
    static final Map<BlockFamily.Variant, TagKey<Item>> VARIANT_STONE_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Item>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, ItemTags.STONE_BUTTONS)
            .buildKeepingLast();
    static final Map<BlockFamily.Variant, TagKey<Item>> VARIANT_WOODEN_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Item>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, ItemTags.WOODEN_BUTTONS)
            .put(BlockFamily.Variant.DOOR, ItemTags.WOODEN_DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, ItemTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.FENCE, ItemTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.SLAB, ItemTags.WOODEN_SLABS)
            .put(BlockFamily.Variant.STAIRS, ItemTags.WOODEN_STAIRS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, ItemTags.WOODEN_PRESSURE_PLATES)
            .put(BlockFamily.Variant.TRAPDOOR, ItemTags.WOODEN_TRAPDOORS)
            .buildKeepingLast();

    public ModItemTagProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(ItemTags.PLANKS).add(ModItems.STEMWOOD_PLANKS.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            for (Map.Entry<BlockFamily.Variant, TagKey<Item>> entry : VARIANT_WOODEN_TAGS.entrySet()) {
                Holder.Reference<Item> item = registrar.getItem(entry.getKey());
                if (item != null) {
                    this.add(entry.getValue()).add(item);
                }
            }
        });
        this.add(ModRegistry.STEMWOOD_LOGS_ITEM_TAG)
                .add(ModItems.STEMWOOD_LOG.value(),
                        ModItems.STEMWOOD_WOOD.value(),
                        ModItems.STRIPPED_STEMWOOD_LOG.value(),
                        ModItems.STRIPPED_STEMWOOD_WOOD.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            this.add(ItemTags.BOATS).add(registrar.boatItem());
            this.add(ItemTags.CHEST_BOATS).add(registrar.chestBoatItem());
        });
    }
}

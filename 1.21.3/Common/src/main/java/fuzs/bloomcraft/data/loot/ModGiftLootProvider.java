package fuzs.bloomcraft.data.loot;

import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.CluckbloomVariant;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModGiftLootProvider extends AbstractLootProvider.Simple {

    public ModGiftLootProvider(DataProviderContext context) {
        super(LootContextParamSets.GIFT, context);
    }

    @Override
    public void addLootTables() {
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.DANDELION, Items.YELLOW_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.POPPY, Items.RED_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.BLUE_ORCHID, Items.LIGHT_BLUE_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.ALLIUM, Items.MAGENTA_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.AZURE_BLUET, Items.LIGHT_GRAY_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.RED_TULIP, Items.RED_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.ORANGE_TULIP, Items.ORANGE_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.WHITE_TULIP, Items.LIGHT_GRAY_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.PINK_TULIP, Items.PINK_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.OXEYE_DAISY, Items.LIGHT_GRAY_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.CORNFLOWER, Items.BLUE_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.LILY_OF_THE_VALLEY, Items.WHITE_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.WITHER_ROSE, Items.BLACK_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.TORCHFLOWER, Items.ORANGE_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.BUTTERCUP, Items.YELLOW_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.PINK_DAISY, Items.PINK_DYE);
        this.registerCluckbloomLayingLootTable(ModCluckbloomVariants.ROSE, Items.RED_DYE);
    }

    void registerCluckbloomLayingLootTable(ResourceKey<CluckbloomVariant> resourceKey, Item item) {
        this.add(CluckbloomVariant.getLayingLootTable(ModRegistry.CLUCKBLOOM_ENTITY_TYPE, resourceKey),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(item))));
    }
}

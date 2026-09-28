package fuzs.bloomcraft.common.data.loot;

import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractEntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModEntityLootProvider extends AbstractEntityLootSubProvider {

    public ModEntityLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(ModEntityTypes.MOOBLOOM_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.CHICKEN)
                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
    }
}

package fuzs.bloomcraft.data.loot;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModCluckbloomVariants;
import fuzs.bloomcraft.init.ModMoobloomVariants;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModShearingLootProvider extends AbstractLootProvider.Simple {

    public ModShearingLootProvider(DataProviderContext context) {
        super(LootContextParamSets.SHEARING, context);
    }

    @Override
    public void addLootTables() {
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.DANDELION, Blocks.DANDELION);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.POPPY, Blocks.POPPY);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.ALLIUM, Blocks.ALLIUM);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.RED_TULIP, Blocks.RED_TULIP);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM);
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        this.registerMoobloomShearingLootTable(ModMoobloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());

        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.DANDELION, Blocks.DANDELION);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.POPPY, Blocks.POPPY);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.ALLIUM, Blocks.ALLIUM);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.RED_TULIP, Blocks.RED_TULIP);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM);
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        this.registerCluckbloomShearingLootTable(ModCluckbloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
    }

    void registerMoobloomShearingLootTable(ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        this.add(FlowerMobVariant.getShearingLootTable(ModRegistry.MOOBLOOM_ENTITY_TYPE, resourceKey),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5.0F))
                                .add(LootItem.lootTableItem(block))));
    }

    void registerCluckbloomShearingLootTable(ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        this.add(FlowerMobVariant.getShearingLootTable(ModRegistry.CLUCKBLOOM_ENTITY_TYPE, resourceKey),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(block))));
    }
}

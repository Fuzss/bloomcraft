package fuzs.bloomcraft.common.data.loot;

import fuzs.bloomcraft.common.init.CluckbloomVariants;
import fuzs.bloomcraft.common.init.ModBlocks;
import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.bloomcraft.common.init.MoobloomVariants;
import fuzs.bloomcraft.common.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModShearingLootProvider extends AbstractLootSubProvider {

    public ModShearingLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        // Moobloom
        this.registerMoobloomShearingLootTable(MoobloomVariants.DANDELION, Blocks.DANDELION);
        this.registerMoobloomShearingLootTable(MoobloomVariants.POPPY, Blocks.POPPY);
        this.registerMoobloomShearingLootTable(MoobloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        this.registerMoobloomShearingLootTable(MoobloomVariants.ALLIUM, Blocks.ALLIUM);
        this.registerMoobloomShearingLootTable(MoobloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        this.registerMoobloomShearingLootTable(MoobloomVariants.RED_TULIP, Blocks.RED_TULIP);
        this.registerMoobloomShearingLootTable(MoobloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        this.registerMoobloomShearingLootTable(MoobloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        this.registerMoobloomShearingLootTable(MoobloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        this.registerMoobloomShearingLootTable(MoobloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        this.registerMoobloomShearingLootTable(MoobloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        this.registerMoobloomShearingLootTable(MoobloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        this.registerMoobloomShearingLootTable(MoobloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        this.registerMoobloomShearingLootTable(MoobloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        this.registerMoobloomShearingLootTable(MoobloomVariants.EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM);
        this.registerMoobloomShearingLootTable(MoobloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        this.registerMoobloomShearingLootTable(MoobloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
        // Cluckbloom
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.DANDELION, Blocks.DANDELION);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.POPPY, Blocks.POPPY);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.BLUE_ORCHID, Blocks.BLUE_ORCHID);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.ALLIUM, Blocks.ALLIUM);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.AZURE_BLUET, Blocks.AZURE_BLUET);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.RED_TULIP, Blocks.RED_TULIP);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.ORANGE_TULIP, Blocks.ORANGE_TULIP);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.WHITE_TULIP, Blocks.WHITE_TULIP);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.PINK_TULIP, Blocks.PINK_TULIP);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.OXEYE_DAISY, Blocks.OXEYE_DAISY);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.CORNFLOWER, Blocks.CORNFLOWER);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.LILY_OF_THE_VALLEY, Blocks.LILY_OF_THE_VALLEY);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.WITHER_ROSE, Blocks.WITHER_ROSE);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.TORCHFLOWER, Blocks.TORCHFLOWER);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM);
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.BUTTERCUP, ModBlocks.BUTTERCUP.value());
        this.registerCluckbloomShearingLootTable(CluckbloomVariants.PINK_DAISY, ModBlocks.PINK_DAISY.value());
    }

    public final void registerMoobloomShearingLootTable(ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        this.output.accept(FlowerMobVariant.getShearingLootTable(ModEntityTypes.MOOBLOOM_ENTITY_TYPE, resourceKey),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(5))
                                .add(LootItem.lootTableItem(block))));
    }

    public final void registerCluckbloomShearingLootTable(ResourceKey<FlowerMobVariant> resourceKey, Block block) {
        this.output.accept(FlowerMobVariant.getShearingLootTable(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE, resourceKey),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(3))
                                .add(LootItem.lootTableItem(block))));
    }
}

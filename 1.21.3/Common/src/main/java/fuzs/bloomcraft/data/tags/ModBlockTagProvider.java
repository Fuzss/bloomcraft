package fuzs.bloomcraft.data.tags;

import com.google.common.collect.ImmutableMap;
import fuzs.bloomcraft.init.BlockFamilyRegistrar;
import fuzs.bloomcraft.init.ModBlockFamilies;
import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .put(BlockFamily.Variant.BUTTON, BlockTags.BUTTONS)
            .put(BlockFamily.Variant.DOOR, BlockTags.DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockTags.FENCES)
            .put(BlockFamily.Variant.FENCE, BlockTags.FENCES)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockTags.FENCE_GATES)
            .put(BlockFamily.Variant.FENCE_GATE, BlockTags.FENCE_GATES)
            .put(BlockFamily.Variant.SIGN, BlockTags.STANDING_SIGNS)
            .put(BlockFamily.Variant.SLAB, BlockTags.SLABS)
            .put(BlockFamily.Variant.STAIRS, BlockTags.STAIRS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.PRESSURE_PLATES)
            .put(BlockFamily.Variant.TRAPDOOR, BlockTags.TRAPDOORS)
            .put(BlockFamily.Variant.WALL, BlockTags.WALLS)
            .put(BlockFamily.Variant.WALL_SIGN, BlockTags.WALL_SIGNS)
            .build();
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_STONE_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, BlockTags.STONE_BUTTONS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.STONE_PRESSURE_PLATES)
            .buildKeepingLast();
    static final Map<BlockFamily.Variant, TagKey<Block>> VARIANT_WOODEN_TAGS = ImmutableMap.<BlockFamily.Variant, TagKey<Block>>builder()
            .putAll(VARIANT_TAGS)
            .put(BlockFamily.Variant.BUTTON, BlockTags.WOODEN_BUTTONS)
            .put(BlockFamily.Variant.DOOR, BlockTags.WOODEN_DOORS)
            .put(BlockFamily.Variant.CUSTOM_FENCE, BlockTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.FENCE, BlockTags.WOODEN_FENCES)
            .put(BlockFamily.Variant.SLAB, BlockTags.WOODEN_SLABS)
            .put(BlockFamily.Variant.STAIRS, BlockTags.WOODEN_STAIRS)
            .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTags.WOODEN_PRESSURE_PLATES)
            .put(BlockFamily.Variant.TRAPDOOR, BlockTags.WOODEN_TRAPDOORS)
            .buildKeepingLast();

    public ModBlockTagProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.add(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.BUTTERCUP.value(), ModBlocks.PINK_DAISY.value(), ModBlocks.ROSE.value());
        this.add(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_BUTTERCUP.value(),
                        ModBlocks.POTTED_PINK_DAISY.value(),
                        ModBlocks.POTTED_ROSE.value());
        this.add(BlockTags.DIRT).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.ANIMALS_SPAWNABLE_ON).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.RABBITS_SPAWNABLE_ON).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(ModBlocks.FLOWERING_GRASS_BLOCK.value());
        this.add(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.YELLOW_PETAL_BLOCK.value(),
                        ModBlocks.RED_PETAL_BLOCK.value(),
                        ModBlocks.PINK_PETAL_BLOCK.value(),
                        ModBlocks.ORANGE_PETAL_BLOCK.value());
        this.add(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.YELLOW_PETAL_BLOCK.value(),
                        ModBlocks.RED_PETAL_BLOCK.value(),
                        ModBlocks.PINK_PETAL_BLOCK.value(),
                        ModBlocks.ORANGE_PETAL_BLOCK.value());
        this.add(BlockTags.PLANKS).add(ModBlocks.STEMWOOD_PLANKS.value());
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            for (Map.Entry<BlockFamily.Variant, TagKey<Block>> entry : VARIANT_WOODEN_TAGS.entrySet()) {
                Holder.Reference<Block> block = registrar.getBlock(entry.getKey());
                if (block != null) {
                    this.add(entry.getValue()).add(block);
                }
            }
        });
        ModBlockFamilies.getAllFamilyRegistrars().forEach((BlockFamilyRegistrar registrar) -> {
            if (registrar.hangingSignBlock() != null) {
                this.add(BlockTags.CEILING_HANGING_SIGNS).add(registrar.hangingSignBlock());
            }
            if (registrar.wallHangingSignBlock() != null) {
                this.add(BlockTags.WALL_HANGING_SIGNS).add(registrar.wallHangingSignBlock());
            }
        });
        this.add(BlockTags.LOGS_THAT_BURN).addTag(ModRegistry.STEMWOOD_LOGS_BLOCK_TAG);
        this.add(ModRegistry.STEMWOOD_LOGS_BLOCK_TAG)
                .add(ModBlocks.STEMWOOD_LOG.value(),
                        ModBlocks.STEMWOOD_WOOD.value(),
                        ModBlocks.STRIPPED_STEMWOOD_LOG.value(),
                        ModBlocks.STRIPPED_STEMWOOD_WOOD.value());
    }
}

package fuzs.bloomcraft.init;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Holder.Reference<Block> BUTTERCUP = ModRegistry.REGISTRIES.registerBlock("buttercup",
            (BlockBehaviour.Properties properties) -> new FlowerBlock(MobEffects.SATURATION, 0.35F, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
    public static final Holder.Reference<Block> PINK_DAISY = ModRegistry.REGISTRIES.registerBlock("pink_daisy",
            (BlockBehaviour.Properties properties) -> new FlowerBlock(MobEffects.REGENERATION, 8.0F, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY));
    public static final Holder.Reference<Block> ROSE = ModRegistry.REGISTRIES.registerBlock("rose",
            (BlockBehaviour.Properties properties) -> new FlowerBlock(MobEffects.NIGHT_VISION, 5.0F, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));
    public static final Holder.Reference<Block> POTTED_BUTTERCUP = ModRegistry.REGISTRIES.registerBlock(
            "potted_buttercup",
            (BlockBehaviour.Properties properties) -> new FlowerPotBlock(BUTTERCUP.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> POTTED_PINK_DAISY = ModRegistry.REGISTRIES.registerBlock(
            "potted_pink_daisy",
            (BlockBehaviour.Properties properties) -> new FlowerPotBlock(PINK_DAISY.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> POTTED_ROSE = ModRegistry.REGISTRIES.registerBlock("potted_rose",
            (BlockBehaviour.Properties properties) -> new FlowerPotBlock(ROSE.value(), properties),
            Blocks::flowerPotProperties);
    public static final Holder.Reference<Block> FLOWERING_GRASS_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "flowering_grass_block",
            GrassBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK));
    public static final Holder.Reference<Block> YELLOW_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "yellow_petal_block",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(0.2F)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> RED_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlock("red_petal_block",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.2F)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> PINK_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "pink_petal_block",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.2F)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> ORANGE_PETAL_BLOCK = ModRegistry.REGISTRIES.registerBlock(
            "orange_petal_block",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(0.2F)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> STEMWOOD_LOG = ModRegistry.REGISTRIES.registerBlock("stemwood_log",
            RotatedPillarBlock::new,
            () -> Blocks.logProperties(MapColor.PLANT, MapColor.PLANT, SoundType.WOOD));
    public static final Holder.Reference<Block> STEMWOOD_WOOD = ModRegistry.REGISTRIES.registerBlock("stemwood_wood",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(MapColor.PLANT));
    public static final Holder.Reference<Block> STRIPPED_STEMWOOD_LOG = ModRegistry.REGISTRIES.registerBlock(
            "stripped_stemwood_log",
            RotatedPillarBlock::new,
            () -> Blocks.logProperties(MapColor.PLANT, MapColor.PLANT, SoundType.WOOD));
    public static final Holder.Reference<Block> STRIPPED_STEMWOOD_WOOD = ModRegistry.REGISTRIES.registerBlock(
            "stripped_stemwood_wood",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.PLANT));
    public static final Holder.Reference<Block> STEMWOOD_PLANKS = ModRegistry.REGISTRIES.registerBlock("stemwood_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.PLANT));

    public static void bootstrap() {
        // NO-OP
    }
}

package fuzs.bloomcraft.data.client;

import fuzs.bloomcraft.init.ModBlocks;
import fuzs.bloomcraft.init.ModItems;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators builder) {
        builder.createPlant(ModBlocks.BUTTERCUP.value(),
                ModBlocks.POTTED_BUTTERCUP.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.PINK_DAISY.value(),
                ModBlocks.POTTED_PINK_DAISY.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        builder.createPlant(ModBlocks.ROSE.value(),
                ModBlocks.POTTED_ROSE.value(),
                BlockModelGenerators.TintState.NOT_TINTED);
        this.createGrassLikeBlock(ModBlocks.FLOWERING_GRASS_BLOCK.value(), builder);
        builder.createTrivialCube(ModBlocks.YELLOW_PETAL_BLOCK.value());
        builder.createTrivialCube(ModBlocks.RED_PETAL_BLOCK.value());
        builder.createTrivialCube(ModBlocks.PINK_PETAL_BLOCK.value());
        builder.createTrivialCube(ModBlocks.ORANGE_PETAL_BLOCK.value());
    }

    private void createGrassLikeBlock(Block block, BlockModelGenerators builder) {
        TexturedModel texturedModel = TexturedModel.CUBE_TOP_BOTTOM.get(block)
                .updateTextures((TextureMapping textureMappingX) -> textureMappingX.put(TextureSlot.BOTTOM,
                        TextureMapping.getBlockTexture(Blocks.DIRT)));
        ResourceLocation resourceLocation = texturedModel.create(block, builder.modelOutput);
        ResourceLocation snowResourceLocation = texturedModel.updateTextures((TextureMapping textureMapping) -> textureMapping.put(
                TextureSlot.SIDE,
                TextureMapping.getBlockTexture(block, "_snow"))).createWithSuffix(block, "_snow", builder.modelOutput);
        builder.createGrassLikeBlock(block,
                resourceLocation,
                Variant.variant().with(VariantProperties.MODEL, snowResourceLocation));
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        builder.generateFlatItem(ModItems.MOOBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
        builder.generateFlatItem(ModItems.CLUCKBLOOM_SPAWN_EGG.value(), ModelTemplates.FLAT_ITEM);
    }
}

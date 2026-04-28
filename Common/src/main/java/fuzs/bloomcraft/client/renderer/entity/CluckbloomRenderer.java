package fuzs.bloomcraft.client.renderer.entity;

import fuzs.bloomcraft.client.init.ModModelLayers;
import fuzs.bloomcraft.client.renderer.entity.layer.CluckbloomBlockStateLayer;
import fuzs.bloomcraft.client.renderer.entity.state.CluckbloomRenderState;
import fuzs.bloomcraft.world.entity.animal.Cluckbloom;
import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.animal.chicken.AdultChickenModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import java.util.Set;

public class CluckbloomRenderer extends AgeableMobRenderer<Cluckbloom, CluckbloomRenderState, AdultChickenModel> {
    /**
     * Copied from {@code ChickenModel#BABY_TRANSFORMER} from Minecraft 1.21.11.
     */
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false,
            5.0F,
            2.0F,
            2.0F,
            1.99F,
            24.0F,
            Set.of("head", "beak", "red_thing"));

    private final BlockModelResolver blockModelResolver;

    public CluckbloomRenderer(Context context) {
        super(context,
                new AdultChickenModel(context.bakeLayer(ModModelLayers.CLUCKBLOOM)),
                new AdultChickenModel(context.bakeLayer(ModModelLayers.CLUCKBLOOM_BABY)),
                0.3F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new CluckbloomBlockStateLayer<>(this));
    }

    public static LayerDefinition createBodyLayer() {
        return AdultChickenModel.createBodyLayer().apply((MeshDefinition meshDefinition) -> {
            PartDefinition partDefinition = meshDefinition.getRoot();
            // fix rotation point to be at body and not in air
            partDefinition.addOrReplaceChild("left_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(3.0F, 13.0F, 0.0F));
            partDefinition.addOrReplaceChild("right_wing",
                    CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F),
                    PartPose.offset(-3.0F, 13.0F, 0.0F));
            return meshDefinition;
        });
    }

    @Override
    public Identifier getTextureLocation(CluckbloomRenderState renderState) {
        return renderState.textureLocation;
    }

    @Override
    public CluckbloomRenderState createRenderState() {
        return new CluckbloomRenderState();
    }

    @Override
    public void extractRenderState(Cluckbloom cluckbloom, CluckbloomRenderState state, float partialTick) {
        super.extractRenderState(cluckbloom, state, partialTick);
        state.flap = Mth.lerp(partialTick, cluckbloom.oFlap, cluckbloom.flap);
        state.flapSpeed = Mth.lerp(partialTick, cluckbloom.oFlapSpeed, cluckbloom.flapSpeed);
        state.textureLocation = FlowerMobVariant.transformTextureLocation(cluckbloom.getFlowerVariant()
                .value()
                .textureLocation());
        this.blockModelResolver.update(state.blockModel,
                cluckbloom.getFlowerVariant().value().blockState(),
                MushroomCowRenderer.BLOCK_DISPLAY_CONTEXT);
    }
}

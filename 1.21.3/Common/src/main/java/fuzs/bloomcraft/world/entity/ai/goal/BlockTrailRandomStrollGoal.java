package fuzs.bloomcraft.world.entity.ai.goal;

import fuzs.bloomcraft.world.entity.animal.FlowerMobVariant;
import fuzs.puzzleslib.api.core.v1.CommonAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class BlockTrailRandomStrollGoal extends WaterAvoidingRandomStrollGoal {
    private final int randomChance;

    public <T extends PathfinderMob & VariantHolder<? extends Holder<? extends FlowerMobVariant>>> BlockTrailRandomStrollGoal(T mob, double speedModifier) {
        this(mob, speedModifier, 1000);
    }

    public <T extends PathfinderMob & VariantHolder<? extends Holder<? extends FlowerMobVariant>>> BlockTrailRandomStrollGoal(T mob, double speedModifier, int randomChance) {
        super(mob, speedModifier);
        this.randomChance = randomChance;
    }

    @Override
    public void tick() {
        ServerLevel serverLevel = getServerLevel(this.mob);
        if (CommonAbstractions.INSTANCE.getMobGriefingRule(serverLevel, this.mob)) {
            if (!this.mob.isBaby() && serverLevel.random.nextInt(this.randomChance) == 0 &&
                    this.mob.getDeltaMovement().lengthSqr() > 1.0E-5F) {
                BlockPos blockPos = this.mob.blockPosition();
                BlockState blockState = ((VariantHolder<Holder<? extends FlowerMobVariant>>) this.mob).getVariant()
                        .value()
                        .blockState();
                if (serverLevel.getBlockState(blockPos).isAir() && blockState.canSurvive(serverLevel, blockPos)) {
                    serverLevel.setBlockAndUpdate(blockPos, blockState);
                    SoundType soundType = blockState.getSoundType();
                    // copied from placing block item
                    serverLevel.playSound(null,
                            blockPos,
                            soundType.getPlaceSound(),
                            SoundSource.BLOCKS,
                            (soundType.getVolume() + 1.0F) / 2.0F,
                            soundType.getPitch() * 0.8F);
                    serverLevel.gameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Context.of(this.mob, blockState));
                }
            }
        }
    }
}

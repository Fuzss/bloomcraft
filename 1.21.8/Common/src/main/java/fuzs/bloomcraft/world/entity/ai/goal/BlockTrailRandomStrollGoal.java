package fuzs.bloomcraft.world.entity.ai.goal;

import fuzs.puzzleslib.api.util.v1.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class BlockTrailRandomStrollGoal extends WaterAvoidingRandomStrollGoal {
    private final int randomChance;

    public BlockTrailRandomStrollGoal(PathfinderMob pathfinderMob, double speedModifier) {
        this(pathfinderMob, speedModifier, 1000);
    }

    public BlockTrailRandomStrollGoal(PathfinderMob pathfinderMob, double speedModifier, int randomChance) {
        super(pathfinderMob, speedModifier);
        this.randomChance = randomChance;
    }

    protected abstract BlockState getBlockState();

    @Override
    public void tick() {
        ServerLevel serverLevel = getServerLevel(this.mob);
        if (EntityHelper.isMobGriefingAllowed(serverLevel, this.mob)) {
            if (!this.mob.isBaby() && serverLevel.random.nextInt(this.randomChance) == 0
                    && this.mob.getDeltaMovement().lengthSqr() > 1.0E-5F) {
                BlockPos blockPos = this.mob.blockPosition();
                BlockState blockState = this.getBlockState();
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

package net.hubert.rupecs_emblems.goals;

import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class ModSuicidalFollowOwnerGoal extends Goal {
    private final PathfinderMob mob;
    private final double speed;
    private Player owner;
    private final double stopDistance = 4.0D;
    private final double startDistance = 7.0D;
    private final double deathDistance = 30.0D;

    public ModSuicidalFollowOwnerGoal(PathfinderMob mob, double speed) {
        this.mob = mob;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        if (!(mob instanceof OwnableEntity ownable)) return false;

        Player owner = (ownable.getOwner() instanceof Player player) ? player : null;
        if (owner == null || owner.isSpectator()) return false;
        if (mob.distanceTo(owner) < startDistance) return false;

        this.owner = owner;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return owner != null && mob.distanceTo(owner) > stopDistance;
    }

    @Override
    public void tick() {
        if (owner != null) {
            if (mob.distanceTo(owner) > deathDistance){
                mob.kill();
            }
            mob.getNavigation().moveTo(owner, speed);
        }
    }
}

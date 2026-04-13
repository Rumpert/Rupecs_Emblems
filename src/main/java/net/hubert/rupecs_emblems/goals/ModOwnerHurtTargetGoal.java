package net.hubert.rupecs_emblems.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

public class ModOwnerHurtTargetGoal extends TargetGoal {
    private final PathfinderMob mob;
    private LivingEntity target;
    private final OwnableEntity ownable;

    public ModOwnerHurtTargetGoal(PathfinderMob mob) {
        super(mob, false);
        this.mob = mob;
        if (!(mob instanceof OwnableEntity ownable)) {
            throw new IllegalArgumentException("Entity must implement OwnableEntity");
        }
        this.ownable = ownable;
    }

    @Override
    public boolean canUse() {
        Player owner = (ownable.getOwner() instanceof Player player) ? player : null;
        if (owner == null) return false;

        LivingEntity attacker = owner.getLastHurtMob();
        if (attacker == null || attacker == owner) return false;

        this.target = attacker;
        return true;
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }
}

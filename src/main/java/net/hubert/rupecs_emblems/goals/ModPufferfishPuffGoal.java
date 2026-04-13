package net.hubert.rupecs_emblems.goals;

import net.hubert.rupecs_emblems.entity.custom.TameablePufferfish;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.player.Player;

import java.lang.reflect.Field;
import java.util.List;

public class ModPufferfishPuffGoal extends Goal {
    private final Pufferfish fish;
    private final Player owner;

    public ModPufferfishPuffGoal(Pufferfish pFish, Player owner) {
        this.fish = pFish;
        this.owner = owner;
    }

    @Override
    public boolean canUse() {
        List<LivingEntity> nearbyEntities = this.fish.level().getEntitiesOfClass(LivingEntity.class, this.fish.getBoundingBox().inflate(2.0F), (entity) ->
                entity != this.owner && isValidTarget(entity)
        );

        return !nearbyEntities.isEmpty();
    }

    @Override
    public void start() {
        if (fish instanceof TameablePufferfish tameable) {
            tameable.startPuffing();
        }
    }

    @Override
    public void stop() {
        if (fish instanceof TameablePufferfish tameable) {
            tameable.stopPuffing();
        }
    }

    private boolean isValidTarget(LivingEntity entity) {
        // Example: Only target entities that are not the owner
        return entity != this.fish && entity != this.owner && !entity.isInvulnerable() && !(entity instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() == this.owner);
    }
}

package net.hubert.rupecs_emblems.entity.custom;

import net.hubert.rupecs_emblems.goals.ModSuicidalFollowOwnerGoal;

import net.hubert.rupecs_emblems.goals.ModOwnerHurtTargetGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


import java.util.UUID;

public class TameableBee extends Bee implements OwnableEntity {
    private UUID ownerUUID;
    private transient Player cachedOwner;
    private int lifetime = 300;
    public TameableBee(EntityType<? extends Bee> type, Level level) {
        super(type, level);
    }


    @Override
    public void tick() {
        if (lifetime > 0){
            lifetime--;
        } else {
            kill();
        }
        super.tick();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new ModSuicidalFollowOwnerGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new ModOwnerHurtTargetGoal(this));
    }

    public void setOwner(Player player) {
        this.ownerUUID = player.getUUID();
        this.cachedOwner = player;
    }



    @Override
    public @Nullable UUID getOwnerUUID() {
        return ownerUUID;
    }

    @Nullable
    @Override
    public Player getOwner() {
        if (ownerUUID == null || level().isClientSide()) return null;
        if (cachedOwner == null || !cachedOwner.isAlive() || !cachedOwner.getUUID().equals(ownerUUID)) {
            if (level() instanceof ServerLevel serverLevel) {
                cachedOwner = serverLevel.getPlayerByUUID(ownerUUID);
            }
        }
        return cachedOwner;
    }


    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (target != null && (target.getUUID().equals(ownerUUID) || (target instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() == getOwner()))) {
            return; // don't target owner
        }
        super.setTarget(target);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() == getOwner()) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.ownerUUID != null) {
            compound.putUUID("ownerUUID", this.ownerUUID);
        }
        compound.putInt("lifetime", lifetime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("ownerUUID")) {
            this.ownerUUID = compound.getUUID("ownerUUID");
        }
        this.lifetime = compound.getInt("lifetime");
    }

}

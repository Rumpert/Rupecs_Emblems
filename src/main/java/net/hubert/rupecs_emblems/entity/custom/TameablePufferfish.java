package net.hubert.rupecs_emblems.entity.custom;

import net.hubert.rupecs_emblems.goals.ModFishSwimGoal;
import net.hubert.rupecs_emblems.goals.ModOwnerHurtTargetGoal;
import net.hubert.rupecs_emblems.goals.ModPufferfishPuffGoal;
import net.hubert.rupecs_emblems.goals.ModSuicidalFollowOwnerGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TameablePufferfish extends Pufferfish implements OwnableEntity {
    private UUID ownerUUID;
    private transient Player cachedOwner;
    private int lifetime = 300;

    public TameablePufferfish(EntityType<? extends Pufferfish> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new ModFishSwimGoal(this));
        this.goalSelector.addGoal(1, new ModSuicidalFollowOwnerGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new ModOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, new ModPufferfishPuffGoal(this, cachedOwner));
    }



    public int customInflateCounter = 0;
    public int customDeflateTimer = 0;



    // Use this instead of reflection!
    public void startPuffing() {
        this.customInflateCounter = 1;
        this.customDeflateTimer = 0;
    }

    public void stopPuffing() {
        this.customInflateCounter = 0;
    }

    @Override
    public void tick() {
        if (lifetime > 0){
            lifetime--;
        } else {
            kill();
        }
        if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
            if (this.customInflateCounter > 0) {
                if (this.getPuffState() == 0) {

                    this.setPuffState(1);
                } else if (this.customInflateCounter > 40 && this.getPuffState() == 1) {

                    this.setPuffState(2);
                }

                ++this.customInflateCounter;
            } else if (this.getPuffState() != 0) {
                if (this.customDeflateTimer > 60 && this.getPuffState() == 2) {

                    this.setPuffState(1);
                } else if (this.customDeflateTimer > 100 && this.getPuffState() == 1) {

                    this.setPuffState(0);
                }

                ++this.customDeflateTimer;
            }
        }

        super.tick();
    }

    @Override
    protected float getSoundVolume() {
        return 0;
    }

    @Override
    public float getVoicePitch() {
        return 0;
    }

    @Override
    public @Nullable UUID getOwnerUUID() {
        return ownerUUID;
    }

    public void setOwner(Player player) {
        this.ownerUUID = player.getUUID();
        this.cachedOwner = player;
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
    public void aiStep() {
        super.aiStep();
        Player owner = getOwner();
        if (owner != null && this.distanceTo(owner) < 3.0F) {
            this.setPuffState(0); // Don’t puff near owner
        }
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() == getOwner()) {
            return false; // immune to owner damage
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

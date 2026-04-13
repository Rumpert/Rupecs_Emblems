// CometShardProjectile.java
package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class HealingNoteProjectile extends ThrowableItemProjectile implements ItemSupplier {

    private float healAmount;
    private boolean isFollowing = false;
    private boolean shouldFollow = false;
    private boolean canPickUp = false;
    private static final EntityDataAccessor<Float> DATA_HEAL_AMOUNT =
            SynchedEntityData.defineId(HealingNoteProjectile.class, EntityDataSerializers.FLOAT);

    @Override
        public void tick() {
            if (getOwner() != null && position().distanceTo(getOwner().position()) < 1.5){
                if (!level().isClientSide && canPickUp) {
                    if (getOwner() instanceof LivingEntity living) {
                        living.heal(healAmount);
                    }
                    discard();
                }
            }

            if (tickCount >= 40){
                if (isFollowing){
                    shouldFollow = true;
                    canPickUp = true;
                }
            }
            if (tickCount > 100 || getOwner() == null){
                discard();
            }
            if (shouldFollow && getOwner() != null) {
                if (position().distanceTo(getOwner().position()) > 1) {
                    LivingEntity owner = (LivingEntity) getOwner();
                    double speed = 0.2; // smaller = slower follow
                    Vec3 toOwner = owner.position().add(0, owner.getEyeHeight(), 0).subtract(position());
                    setDeltaMovement(getDeltaMovement().scale(0.8).add(toOwner.normalize().scale(speed)));
                } else {
                    setPos(getOwner().position());
                }
            }
            if (!shouldFollow && getDeltaMovement() != Vec3.ZERO){
                addDeltaMovement(getDeltaMovement().scale(-0.05));
            }

            super.tick();
        }


    public HealingNoteProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setNoGravity(true);
    }

    public HealingNoteProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel, float pHealAmount, boolean pIsFollowing) {
        super(pEntityType, pLevel);
        setNoGravity(true);
        healAmount = pHealAmount;
        isFollowing = pIsFollowing;
        this.entityData.set(DATA_HEAL_AMOUNT, pHealAmount);
    }



    @Override
    protected Item getDefaultItem() {
        return ItemStack.EMPTY.getItem();
    }
    @Override
    public boolean isInWater() {
        // Always report as NOT in water, so it won't be slowed or splash
        return false;
    }

    @Override
    public boolean isInWaterOrBubble() {
        // Prevent bubble columns and water slowing
        return false;
    }

    public float getHealAmount() {
        return this.entityData.get(DATA_HEAL_AMOUNT);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HEAL_AMOUNT, 1.0F);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.getOwner() == null) discard();


        if (!level().isClientSide && canPickUp && pResult.getEntity() == getOwner()) {
            if (getOwner() instanceof LivingEntity living) {
                living.heal(healAmount);
            }
            discard();
        }

    }
}
// CometShardProjectile.java
package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class DamagingNoteProjectile extends ThrowableItemProjectile implements ItemSupplier {

    private float damageAmount;
    private boolean isFollowing = false;
    private boolean shouldFollow = false;
    private LivingEntity target;
    private boolean attackAny = false;
    public static final EntityDataAccessor<Float> DATA_DAMAGE_AMOUNT =
            SynchedEntityData.defineId(DamagingNoteProjectile.class, EntityDataSerializers.FLOAT);

    @Override
        public void tick() {
        if (target == null && isFollowing){
            discard();
        }
            if (tickCount >= 20){
                if (isFollowing){
                    shouldFollow = true;
                }
            }
            if (tickCount > 100){
                discard();
            }
            if (shouldFollow && target != null) {
                if (position().distanceTo(target.position()) > 1) {
                    LivingEntity owner = (LivingEntity) getOwner();
                    double speed = 0.2; // smaller = slower follow
                    Vec3 toOwner = target.position().add(0, target.getEyeHeight(), 0).subtract(position());
                    setDeltaMovement(getDeltaMovement().scale(0.8).add(toOwner.normalize().scale(speed)));
                } else {
                    setPos(target.position());
                }
            }
            if (!shouldFollow && getDeltaMovement() != Vec3.ZERO){
                addDeltaMovement(getDeltaMovement().scale(-0.05));
            }
        if (!level().isClientSide && attackAny) {
            for (LivingEntity nearby : level().getEntitiesOfClass(
                    LivingEntity.class,
                    getBoundingBox().inflate(0.2), // detection radius
                    e -> e != getOwner()
            )) {
                if (!nearby.hurtMarked) {
                    nearby.hurt(level().damageSources().indirectMagic(this, getOwner()), damageAmount);
                    discard();
                }
                    break;

            }
        }
            super.tick();
        }


    public DamagingNoteProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setNoGravity(true);
    }

    public DamagingNoteProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel, float pDamageAmount, boolean pIsFollowing, boolean pAttackAny) {
        super(pEntityType, pLevel);
        setNoGravity(true);
        damageAmount = pDamageAmount;
        isFollowing = pIsFollowing;
        attackAny = pAttackAny;
        this.entityData.set(DATA_DAMAGE_AMOUNT, pDamageAmount);
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
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

    public float getDamageAmount() {
        return this.entityData.get(DATA_DAMAGE_AMOUNT);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DAMAGE_AMOUNT, 1.0F);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (getOwner() == null) {
            discard();
            return;
        }

        if (!level().isClientSide) {
            if (pResult.getEntity() == target || (pResult.getEntity() instanceof LivingEntity && attackAny)) {
                pResult.getEntity().hurt(level().damageSources().indirectMagic(this, getOwner()), getDamageAmount());
                discard();
            }
        }
    }

}
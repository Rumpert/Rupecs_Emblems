// CometShardProjectile.java
package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class IcicleProjectile extends ThrowableItemProjectile implements ItemSupplier {

        @Override
        public void tick() {
            if (tickCount > 20){
                discard();
            }
            if (level().isClientSide && tickCount % 5 == 0){
                level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(),getZ(),0, 0,0);

            }
            super.tick();
        }


    public IcicleProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected float getGravity() {
        return 0;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(pResult.getEntity().damageSources().playerAttack((Player) getOwner()), 3);
        if (pResult.getEntity() instanceof LivingEntity entity){
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30,1));
        }
        pResult.getEntity().hurtMarked = false;
        pResult.getEntity().invulnerableTime = 0;
    }
}
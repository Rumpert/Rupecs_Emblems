// CometShardProjectile.java
package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
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

public class VolcanicShardProjectile extends ThrowableItemProjectile implements ItemSupplier {

        @Override
        public void tick() {
            if (!isOnFire()){
                setSecondsOnFire(20);
            }
            if (tickCount > 400){
                discard();
            }
            if (level().isClientSide && tickCount % 5 == 0){
                level().addParticle(ParticleTypes.FLAME, getX(), getY(),getZ(),0, 0,0);

            }
            super.tick();
        }


    public VolcanicShardProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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
        if (!(pResult.getEntity() instanceof Player player && getOwner() != null && player.is(getOwner())) && pResult.getEntity() instanceof LivingEntity mob && getOwner() != null && !mob.isAlliedTo(getOwner())) {
            pResult.getEntity().hurt(pResult.getEntity().damageSources().playerAttack((Player) getOwner()), 3);
            pResult.getEntity().setSecondsOnFire(2);
            discard();
        }
    }
}
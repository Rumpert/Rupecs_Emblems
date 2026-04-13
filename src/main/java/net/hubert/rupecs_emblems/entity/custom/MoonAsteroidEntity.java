package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class MoonAsteroidEntity extends ThrowableItemProjectile implements ItemSupplier {
    private UUID ownerUUID;
    private Class<? extends LivingEntity> entityToHurtType;
    private boolean exploded = false;

    public MoonAsteroidEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    /**
     * Which class of LivingEntity this asteroid should damage.
     * e.g. Zombie.class, Skeleton.class, etc.
     */
    public void setEntityToHurtType(Class<? extends LivingEntity> type) {
        this.entityToHurtType = type;
    }

    @Override
    public void tick() {
        // Keep falling smoothly
        resetFallDistance();
        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        // No default item in flight
        return ItemStack.EMPTY.getItem();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
    }

    /**
     * Called when this projectile hits anything (block or entity).
     */

    @Override
    protected void onHit(HitResult result) {
        if (exploded) {
            super.onHit(result);
            return;
        }

        exploded = true;

        // Resolve owner
        Player owner = ownerUUID != null
                ? ((ServerLevel)level()).getServer().getPlayerList().getPlayer(ownerUUID)
                : null;
        if (owner == null) {

            this.discard();
            return;
        }

        // DEBUG


        // Damage all matching mobs in a 2-block radius
        AABB area = this.getBoundingBox().inflate(2.0D);
        for (Entity e : level().getEntities(this, area)) {
            if (e.getUUID().equals(ownerUUID)) continue;
            if (!(e instanceof Mob mob)) continue;
            if (!mob.isAlive()) continue;
            if (entityToHurtType != null && !entityToHurtType.isInstance(mob)) continue;

            mob.hurt(
                    level().damageSources().magic(),
                    5.0F
            );
        }

        // Sound
        level().playSound(
                null, getX(), getY(), getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.NEUTRAL,
                1.0F,
                0.5F
        );

        // Particles
        if (level() instanceof ServerLevel server) {
            server.sendParticles(
                    ParticleTypes.EXPLOSION,
                    getX(), getY(), getZ(),
                    10,
                    1.5, 1.5, 1.5,
                    0.0
            );
        }

        this.discard();
        super.onHit(result);
    }

}

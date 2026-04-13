// CometShardProjectile.java
package net.hubert.rupecs_emblems.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class OwnableLightningBolt extends LightningBolt{
    public LivingEntity owner = null;
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) return;

        for (Entity entity : this.level().getEntities(this, this.getBoundingBox().inflate(3.0))) {

            if (entity == this.getOwner()) continue; // ✅ skip owner

            entity.thunderHit((ServerLevel)this.level(), this); // damage everyone else
        }
    }
    public OwnableLightningBolt(EntityType<? extends LightningBolt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public void setOwner(LivingEntity entity){
        owner = entity;
    }

    public LivingEntity getOwner(){
        return owner;
    }

    @Override
    public @NotNull Stream<Entity> getHitEntities() {
        return super.getHitEntities()
                .filter(entity ->  entity.getUUID() != owner.getUUID());
    }
}
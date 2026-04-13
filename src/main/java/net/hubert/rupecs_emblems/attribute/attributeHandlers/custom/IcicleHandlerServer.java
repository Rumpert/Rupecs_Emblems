package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.IcicleProjectile;
import net.hubert.rupecs_emblems.entity.custom.MoonAsteroidEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class IcicleHandlerServer {

    public static void summonIcicles(Entity entity, int count) {

        Vec3 playerPos = entity.position();

        for (int i = 0; i < count; i++) {

            IcicleProjectile projectile = new IcicleProjectile(ModEntities.ICICLE.get(), entity.level());
            projectile.setPos(playerPos.x, playerPos.y + 1.2, playerPos.z);

            // random direction
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = 0;
            double z = ThreadLocalRandom.current().nextDouble(-1, 1);

            Vec3 direction = new Vec3(x, y, z).normalize();

            double speed = ThreadLocalRandom.current().nextDouble(0.5, 1.2);

            projectile.setDeltaMovement(direction.scale(speed));

            entity.level().addFreshEntity(projectile);
        }
    }
}

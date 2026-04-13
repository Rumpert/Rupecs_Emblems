package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.MoonAsteroidEntity;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class MoonWrathHandlerServer {
    public static void summonMoonAsteroids(Entity entity, double count) {
        // Use ThreadLocalRandom for thread-safety
        Vec3 playerPos = entity.position();
        for (int i = 0; i < count; i++) {
            double angle = ThreadLocalRandom.current().nextDouble() * 2 * Math.PI;
            double distance = ThreadLocalRandom.current().nextDouble() * 10;
            double offsetX = distance * Math.cos(angle);
            double offsetZ = distance * Math.sin(angle);
            double spawnY = playerPos.y + 10.0 + 3 * ThreadLocalRandom.current().nextDouble(); // Height above player

            MoonAsteroidEntity projectile = new MoonAsteroidEntity(ModEntities.MOON_ASTEROID.get(), entity.level());
            projectile.setPos(playerPos.x + offsetX, spawnY, playerPos.z + offsetZ);
            projectile.setOwnerUUID(entity.getUUID());

            double speed = ThreadLocalRandom.current().nextDouble() * 0.5 + 0.5;
            projectile.setDeltaMovement(new Vec3(0, -speed, 0));
            projectile.setEntityToHurtType(Mob.class);

            entity.level().addFreshEntity(projectile);
        }
    }
}

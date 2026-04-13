package net.hubert.rupecs_emblems.client;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.particle.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSoulConnectionParticleHandler {
    public static final Map<LivingEntity, List<LivingEntity>> soulConnectionEntitiesWithParticlesToSpawn = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (soulConnectionEntitiesWithParticlesToSpawn.isEmpty()) return;
        if (event.getEntity().tickCount % 3 != 0) return;

        ServerLevel level = player.serverLevel();

        Iterator<Map.Entry<LivingEntity, List<LivingEntity>>> iterator = soulConnectionEntitiesWithParticlesToSpawn.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, List<LivingEntity>> entry = iterator.next();
            LivingEntity source = entry.getKey();
            List<LivingEntity> targets = entry.getValue();

            // If source is dead, remove it entirely
            if (source == null || !source.isAlive()) {
                iterator.remove();
                continue;
            }

            // Remove invalid targets
            targets.removeIf(target ->
                    target == null ||
                            !target.isAlive() ||
                            target.level() != source.level() ||   // Different dimension check
                            target.distanceToSqr(source) > (50.0 * 50.0) // Too far (use squared distance for performance)
            );

            // If no more valid targets, remove the whole source
            if (targets.isEmpty()) {
                iterator.remove();
                continue;
            }

            // Send particles
            for (LivingEntity target : targets) {
                Vec3 start = source.position();
                Vec3 end = target.position();
                int steps = 15;
                for (int i = 0; i <= steps; i++) {
                    double t = i / (double) steps;
                    double x = start.x + (end.x - start.x) * t;
                    double y = start.y + (end.y - start.y) * t + 0.5;
                    double z = start.z + (end.z - start.z) * t;

                    level.sendParticles(
                            ModParticles.SMALL_HEART_PARTICLES.get(),
                            x, y, z,
                            0, 0, 0, 0, 0
                    );
                }
            }
        }
    }
}

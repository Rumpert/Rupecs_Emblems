// File: ClientLightningParticleHandler.java
package net.hubert.rupecs_emblems.client;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Example client-only event subscriber (placed in a client-only class)
@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientLightningParticleHandler {
    public static final Map<LivingEntity, LivingEntity> lightningParticleSourceTargets = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onClientTick(LivingEvent.LivingTickEvent event) {


        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Client decides to spawn lightning particles in some manner.
        // For example, every 10 ticks, pick two nearby entities to simulate a lightning zap.
        if (lightningParticleSourceTargets.isEmpty()) return;



        Iterator<Map.Entry<LivingEntity, LivingEntity>> iterator = lightningParticleSourceTargets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, LivingEntity> entry = iterator.next();
            LivingEntity source = entry.getKey();
            LivingEntity target = entry.getValue();
            if (source != null && target != null) {
                Vec3 start = source.position();
                Vec3 end = target.position();
                int steps = 10;
                for (int i = 0; i <= steps; i++) {
                    double t = i / (double) steps;
                    double x = start.x + (end.x - start.x) * t;
                    double y = start.y + (end.y - start.y) * t + 1.0;
                    double z = start.z + (end.z - start.z) * t;
                    player.serverLevel().sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            x, y, z,
                            0, 0.1, 0, 0, 0);
                }
            }
            iterator.remove();
        }
    }
}



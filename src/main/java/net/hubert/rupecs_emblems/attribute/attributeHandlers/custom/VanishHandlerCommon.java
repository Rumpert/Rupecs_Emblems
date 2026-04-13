package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;


public class VanishHandlerCommon {



    public static void vanish(Player player) {
        System.out.println("vanish");

        var vanishAttr = player.getAttribute(ModAttributes.VANISH.get());
        if (vanishAttr == null || vanishAttr.getValue() <= 0) return;
        double power = vanishAttr.getValue();
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, (int) (60*power), (int) (power-1), false,false,false));
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, (int) (60*power),0, false,false,false));
        spawnVanishParticles(player);

    }

    private static void spawnVanishParticles(Player player) {
        if (player instanceof ServerPlayer serverPlayer){
            for (int i = 0; i<7; i++){
                serverPlayer.serverLevel().sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY()+1, player.getZ(),3,0.5,0.5, 0.5,0.04);
            }
        }
    }

    public static void tick(LivingEntity entity) {
        if (!(entity instanceof Mob mob)) return;

        if (mob.getTarget() instanceof Player player) {
            // ...and that player has the GOLDEN_FAVOR attribute
            // Instantly stop attacking the player
            mob.setTarget(null);
            mob.setLastHurtByMob(null);
            mob.setLastHurtByPlayer(null);

            // Clear piglin's memory of aggression
            mob.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
            mob.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);

        }
    }



}

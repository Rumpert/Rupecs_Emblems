package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.mehvahdjukaar.moonlight.api.events.forge.LightningStruckBlockEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ThunderclapProtectionHandler {


    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource src = event.getSource();
        Entity target = event.getEntity();
        if (target instanceof Player player && ((player.getAttribute(ModAttributes.THUNDERCLAP.get()) != null && player.getAttribute(ModAttributes.THUNDERCLAP.get()).getValue() > 0 || (player.getAttribute(ModAttributes.LIGHTNING_ASPECT.get()) != null && player.getAttribute(ModAttributes.LIGHTNING_ASPECT.get()).getValue() > 0))) && src == player.damageSources().lightningBolt()) {
            event.setCanceled(true);
        }
    }
}

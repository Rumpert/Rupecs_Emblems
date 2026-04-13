package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.FloristPacket;
import net.hubert.rupecs_emblems.network.packet.PulsePacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PulseHandler {
    private static final String cooldownName = "Pulse";
    public static final KeyMapping PULSE_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final int COOLDOWN_SECONDS = 16; // 2 seconds
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {

            var pulseAttr = player.getAttribute(ModAttributes.PULSE.get());
            if (pulseAttr == null || pulseAttr.getValue() <= 0) return;

            Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(player.getUUID());
            CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
            if (cooldownEntry != null && cooldownEntry.getTicks() > 0) {
                return;  // Ability on cooldown, so do nothing.
            }
            if (PULSE_KEY.isDown()) {
                int cooldownTicks = COOLDOWN_SECONDS * 20;
                CooldownManager.addCooldown(
                        player.getUUID(),
                        cooldownName,
                        cooldownTicks,
                        new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/pulse.png")
                );
                PacketHandler.sendToServer(new PulsePacket());

            }
        }



    }

}

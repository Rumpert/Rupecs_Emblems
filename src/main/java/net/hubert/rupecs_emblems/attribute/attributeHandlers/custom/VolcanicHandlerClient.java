package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.MoonWrathPacket;
import net.hubert.rupecs_emblems.network.packet.VolcanicPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class VolcanicHandlerClient {
    public static final KeyMapping VOLCANIC_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player != event.getEntity()) return;

        var volcanicAttr = player.getAttribute(ModAttributes.VOLCANIC.get());
        if (volcanicAttr == null || volcanicAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get("Volcanic");

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;

        if (VOLCANIC_KEY.isDown() && !hasCooldown) {
            PacketHandler.sendToServer(new VolcanicPacket());

            CooldownManager.addCooldown(player.getUUID(), "Volcanic", 200,
                    ResourceLocation.fromNamespaceAndPath(Rupecs_Emblems.MOD_ID, "textures/ability/volcanic.png"));
        }

    }

}

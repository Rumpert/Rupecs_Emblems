package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.ThunderclapPacket;
import net.hubert.rupecs_emblems.network.packet.TimeControlPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class TimeControlHandlerClient {
    public static final KeyMapping TIME_CONTROL_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player != event.getEntity()) return;

        var vanishAttr = player.getAttribute(ModAttributes.TIME_CONTROL.get());
        if (vanishAttr == null || vanishAttr.getValue() <= 0) return;

        if (TIME_CONTROL_KEY.isDown()) {
            PacketHandler.sendToServer(new TimeControlPacket());
        }

    }
}

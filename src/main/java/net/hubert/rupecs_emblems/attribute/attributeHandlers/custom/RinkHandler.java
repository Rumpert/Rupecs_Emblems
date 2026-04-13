package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.HealPacket;
import net.hubert.rupecs_emblems.network.packet.RinkPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class RinkHandler {
    private static final KeyMapping RINK_KEY = Keybinds.INSTANCE.secondaryEmblemAbility;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || !RINK_KEY.isDown()) return;


        // Check the custom attribute for "POOP"
        var rinkAttr = player.getAttribute(ModAttributes.RINK.get());
        if (rinkAttr == null || rinkAttr.getValue() <= 0) return;
        // Only proceed if the block below the player is air.
            // Multiply seconds by 20 to convert to ticks.
        PacketHandler.sendToServer(new RinkPacket(player));

    }
}

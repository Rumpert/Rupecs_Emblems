package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.DandelionFallPacket;
import net.hubert.rupecs_emblems.network.packet.MirroringPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class DandelionFallHandlerClient {
    public static final KeyMapping DANDELION_FALL_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final String COOLDOWN_NAME = "DandelionFall";

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        var dandelionFallAttr = player.getAttribute(ModAttributes.DANDELION_FALL.get());
        if (dandelionFallAttr == null || dandelionFallAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get(COOLDOWN_NAME);

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;

        // Try to activate vanish
        if (DANDELION_FALL_KEY.isDown() && !hasCooldown) {
            PacketHandler.sendToServer(new DandelionFallPacket());

        }

    }
}

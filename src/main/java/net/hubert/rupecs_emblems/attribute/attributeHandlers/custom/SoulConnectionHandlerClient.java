package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.ClientSoulConnectionParticleHandler;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.MirroringPacket;
import net.hubert.rupecs_emblems.network.packet.SoulConnectingPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class SoulConnectionHandlerClient {
    public static final KeyMapping SOUL_CONNECTION_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final String COOLDOWN_NAME = "SoulConnection";

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        var SoulConnectorAttr = player.getAttribute(ModAttributes.SOUL_CONNECTOR.get());
        if (SoulConnectorAttr == null || SoulConnectorAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get(COOLDOWN_NAME);

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;

        // Try to activate vanish
        if (SOUL_CONNECTION_KEY.isDown() && !hasCooldown) {
            PacketHandler.sendToServer(new SoulConnectingPacket(true));

        }else if (SOUL_CONNECTION_KEY.isDown() && mc.options.keyShift.isDown()) {
            PacketHandler.sendToServer(new SoulConnectingPacket(false));

        }

    }







}

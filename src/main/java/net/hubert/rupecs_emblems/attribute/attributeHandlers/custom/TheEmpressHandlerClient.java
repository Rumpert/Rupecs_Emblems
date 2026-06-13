package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.EmpressPacket;
import net.hubert.rupecs_emblems.network.packet.PriestessPacket;
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
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class TheEmpressHandlerClient {
    public static final KeyMapping KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final String COOLDOWN_NAME = "TheEmpress";
    private static final int COOLDOWN_SECONDS = 20;
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null)return;

        var empressAttr = player.getAttribute(ModAttributes.THE_EMPRESS.get());
        if (empressAttr == null || empressAttr.getValue() <= 0) return;
        if (!KEY.isDown()) return;


        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        CooldownEntry cooldownEntry = playerCooldowns.get(COOLDOWN_NAME);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        PacketHandler.sendToServer(new EmpressPacket());

        int cooldownTicks = COOLDOWN_SECONDS * 20;
        CooldownManager.addCooldown(
                playerUUID,
                COOLDOWN_NAME,
                cooldownTicks,
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/the_empress.png")
        );


    }

}

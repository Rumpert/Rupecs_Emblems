package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.FoolPacket;
import net.hubert.rupecs_emblems.network.packet.MagicianPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class TheMagicianHandlerClient {
    public static final KeyMapping KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final String COOLDOWN_NAME = "TheMagician";
    private static final int COOLDOWN_SECONDS = 20;
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        var magicianAttr = player.getAttribute(ModAttributes.THE_MAGICIAN.get());
        if (magicianAttr == null || magicianAttr.getValue() <= 0) return;
        if (!KEY.isDown()) return;
        if (getTotalXp(player)<200)return;

        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        CooldownEntry cooldownEntry = playerCooldowns.get(COOLDOWN_NAME);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        PacketHandler.sendToServer(new MagicianPacket());

        int cooldownTicks = COOLDOWN_SECONDS * 20;
        CooldownManager.addCooldown(
                playerUUID,
                COOLDOWN_NAME,
                cooldownTicks,
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/the_magician.png")
        );


    }
    public static int getTotalXp(Player player) {
        int level = player.experienceLevel;
        int total;

        if (level <= 16) {
            total = level * level + 6 * level;
        } else if (level <= 31) {
            total = (int)(2.5 * level * level - 40.5 * level + 360);
        } else {
            total = (int)(4.5 * level * level - 162.5 * level + 2220);
        }

        total += Math.round(player.experienceProgress * player.getXpNeededForNextLevel());

        return total;
    }
}

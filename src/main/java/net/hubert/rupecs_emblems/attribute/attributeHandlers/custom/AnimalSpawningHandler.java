package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.PoopPacket;
import net.hubert.rupecs_emblems.network.packet.PuffSummonPacket;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class AnimalSpawningHandler {
    private static final String cooldownName = "Pufferfish";
    private static final KeyMapping PUFF_KEY = Keybinds.INSTANCE.secondaryEmblemAbility;
    private static final int COOLDOWN_SECONDS = 15; // 2 seconds

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || !PUFF_KEY.consumeClick()) return;

        // Safely get the player's cooldown entries
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(player.getUUID());
        // Check if the specific cooldown is already active.
        CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) {
            return;  // Ability on cooldown, so do nothing.
        }

        // Check the custom attribute for "POOP"
        var poopAttribute = player.getAttribute(ModAttributes.PUFF.get());
        if (poopAttribute == null || poopAttribute.getValue() <= 0) return;

        // Only proceed if the block below the player is air.
        for (int i = 0; i < AttributeHelper.getValueIfNotNull(player, ModAttributes.PUFF.get()); i ++) {
            PacketHandler.sendToServer(new PuffSummonPacket());
        }
        // Multiply seconds by 20 to convert to ticks.
        int cooldownTicks = COOLDOWN_SECONDS * 20;
        CooldownManager.addCooldown(
                player.getUUID(),
                cooldownName,
                cooldownTicks,
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/puff.png")
        );
    }

}

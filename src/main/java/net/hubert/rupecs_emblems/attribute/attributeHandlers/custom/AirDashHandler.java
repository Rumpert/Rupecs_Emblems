package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.PoopPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
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
public class AirDashHandler {
    private static final String cooldownName = "AirDash";
    private static final KeyMapping POOP_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final int COOLDOWN_SECONDS = 5; // 2 seconds

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || !POOP_KEY.isDown()) return;

        // Safely get the player's cooldown entries
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(player.getUUID());
        // Check if the specific cooldown is already active.
        CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) {
            return;  // Ability on cooldown, so do nothing.
        }

        // Check the custom attribute for "POOP"
        var poopAttribute = player.getAttribute(ModAttributes.AIR_DASHER.get());
        if (poopAttribute == null || poopAttribute.getValue() <= 0) return;


        // Only proceed if the block below the player is air.
        if (player.isFallFlying()) {
            double dashScale = poopAttribute.getValue() * 0.4;
            player.addDeltaMovement(player.getLookAngle().scale(dashScale));
            // Multiply seconds by 20 to convert to ticks.
            int cooldownTicks = COOLDOWN_SECONDS * 20;
            CooldownManager.addCooldown(
                    player.getUUID(),
                    cooldownName,
                    cooldownTicks,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/air_dash.png")
            );
        }
    }
}

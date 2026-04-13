package net.hubert.rupecs_emblems.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class CooldownRenderer {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        // Choose an overlay type to render with (this example uses "hotbar" overlay for ordering purposes)
        // Check the overlay ID if you want to limit rendering to a certain overlay.
        if (!event.getOverlay().id().getPath().equals("hotbar")) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        if (playerCooldowns.isEmpty()) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Define starting position (bottom-right) and spacing between icons.
        int iconWidth = 16;
        int iconHeight = 16;
        int padding = 2;  // space between icons
        int startX = screenWidth - iconWidth - 5;  // 5 pixels from the right edge
        int startY = screenHeight - iconHeight - 5;  // 5 pixels from the bottom edge

        int index = 0;
        // Iterate over all cooldown entries.
        for (Map.Entry<String, CooldownEntry> entrySet : playerCooldowns.entrySet()) {
            CooldownEntry entry = entrySet.getValue();
            if (entry.getIcon() == null){
                return;
            }
            // Calculate icon position for this cooldown (stack upward)
            int iconX = startX;
            int iconY = startY - (index * (iconHeight + padding));
            ResourceLocation iconTexture = entry.getIcon();

            // Bind the texture and draw the icon.
            RenderSystem.setShaderTexture(0, iconTexture);
            guiGraphics.blit(iconTexture, iconX, iconY, 0, 0, iconWidth, iconHeight, iconWidth, iconHeight);

            // Optionally draw the remaining cooldown time (in seconds) as text over the icon.
            String cooldownText = String.valueOf(entry.getTicks() / 20); // approximate seconds remaining
            guiGraphics.drawCenteredString(mc.font, cooldownText, iconX - cooldownText.length() * 4, iconY + (iconHeight / 2) - 4, 0xFFFFFF);

            index++;
        }
    }
}

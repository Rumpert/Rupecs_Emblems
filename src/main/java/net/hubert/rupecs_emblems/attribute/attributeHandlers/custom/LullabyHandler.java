package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.HealPacket;
import net.hubert.rupecs_emblems.network.packet.PoopPacket;
import net.hubert.rupecs_emblems.particle.ModParticles;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class LullabyHandler {
    private static final String cooldownName = "Lullaby";
    private static final KeyMapping LULLABY_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final int COOLDOWN_SECONDS = 13; // 2 seconds

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || !LULLABY_KEY.isDown()) return;

        // Safely get the player's cooldown entries
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(player.getUUID());
        // Check if the specific cooldown is already active.
        CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) {
            return;  // Ability on cooldown, so do nothing.
        }

        // Check the custom attribute for "POOP"
        var lullabyAttribute = player.getAttribute(ModAttributes.LULLABY.get());
        if (lullabyAttribute == null || lullabyAttribute.getValue() <= 0) return;
        // Only proceed if the block below the player is air.
            // Multiply seconds by 20 to convert to ticks.
        PacketHandler.sendToServer(new HealPacket((float) player.getAttributeValue(ModAttributes.LULLABY.get()), player));

        int cooldownTicks = COOLDOWN_SECONDS * 20;
        CooldownManager.addCooldown(
                player.getUUID(),
                cooldownName,
                cooldownTicks,
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/lullaby.png")
        );

    }
}

package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class MoonJumpHandlerCommon {
    // Cooldown identifier and duration.
    private static final String cooldownName = "MoonJump";
    private static final int COOLDOWN_SECONDS = 10;
    private static boolean hasHighJumped = false;

    /**
     * Called by the client when the key is pressed.
     * In a dedicated server scenario this should be sent as a network packet.
     */
    public static void triggerLaunch(Player player) {
        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        // Get the cooldown entry if present.
        CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
        // If there is a cooldown and it's still active, do not launch.
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        // Also check if the player is already in a high jump state or not on ground.
        if (hasHighJumped || !player.onGround()) return;

        // Check if the player has the MOON_JUMP attribute active.
        if (player.getAttribute(ModAttributes.MOON_JUMP.get()) != null &&
                player.getAttribute(ModAttributes.MOON_JUMP.get()).getValue() > 0) {
            double strength = player.getAttribute(ModAttributes.MOON_JUMP.get()).getValue();
            launchPlayer(player, strength);
            hasHighJumped = true;
            // Multiply cooldown seconds by 20 to convert to ticks.
            int cooldownTicks = COOLDOWN_SECONDS * 20;
            CooldownManager.addCooldown(
                    playerUUID,
                    cooldownName,
                    cooldownTicks,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/moon_jump.png")
            );
        }
    }

    private static void launchPlayer(Player player, double strength) {
        // Apply an upward boost.
        Vec3 currentVel = player.getDeltaMovement();
        Vec3 boost = new Vec3(currentVel.x, 1.0 + 0.4 * strength, currentVel.z);
        player.setDeltaMovement(boost);
        player.move(MoverType.SELF, boost);
        player.resetFallDistance();
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.getAttribute(ModAttributes.MOON_JUMP.get()) == null ||
                player.getAttribute(ModAttributes.MOON_JUMP.get()).getValue() <= 0) {
            return;
        }

        // On the client we update the jump state.
        if (player.level().isClientSide) {
            if (player.onGround()) {
                hasHighJumped = false;
            }
        }

        // Always reset fall damage if the ability is active.
        if (hasHighJumped) {
            player.resetFallDistance();
        }
    }
}

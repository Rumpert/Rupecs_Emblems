package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.FloristPacket;
import net.hubert.rupecs_emblems.network.packet.TimeControlPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class FlowerSpawnAbilityHandlerClient {
    private static final String cooldownName = "Flower";
    public static final KeyMapping FLOWER_SPAWN_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final int COOLDOWN_SECONDS = 2; // 2 seconds
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player != event.getEntity()) return;

        var vanishAttr = player.getAttribute(ModAttributes.FLORIST.get());
        if (vanishAttr == null || vanishAttr.getValue() <= 0) return;

        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(player.getUUID());
        CooldownEntry cooldownEntry = playerCooldowns.get(cooldownName);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) {
            return;  // Ability on cooldown, so do nothing.
        }
        if (FLOWER_SPAWN_KEY.isDown()) {
            PacketHandler.sendToServer(new FloristPacket());
            int cooldownTicks = COOLDOWN_SECONDS * 20;
            CooldownManager.addCooldown(
                    player.getUUID(),
                    cooldownName,
                    cooldownTicks,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/flower.png")
            );
        }



    }
}

package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.VanishPacket;
import net.hubert.rupecs_emblems.network.packet.VanishTickPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class VanishHandlerClient {
    public static final KeyMapping VANISH_KEY = Keybinds.INSTANCE.secondaryEmblemAbility;
    private static final String COOLDOWN_NAME = "Vanish";
    private static final int COOLDOWN_SECONDS = 15;
    private static boolean hasCooldownNow = false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        var vanishAttr = player.getAttribute(ModAttributes.VANISH.get());
        if (vanishAttr == null || vanishAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get(COOLDOWN_NAME);

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;
        hasCooldownNow = hasCooldown;

        // Try to activate vanish
        if (VANISH_KEY.isDown() && Screen.hasControlDown() && !hasCooldown) {
            PacketHandler.sendToServer(new VanishPacket());
            CooldownManager.addCooldown(playerUUID, COOLDOWN_NAME, COOLDOWN_SECONDS * 20,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/vanish.png"));
        }

    }
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof Player player &&Minecraft.getInstance().player != null && player.is(Minecraft.getInstance().player)){

            UUID playerUUID = player.getUUID();
            var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get(COOLDOWN_NAME);

            boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;
            hasCooldownNow = hasCooldown;
        }
        if (hasCooldownNow) {
            PacketHandler.sendToServer(new VanishTickPacket(event.getEntity().getId()));
        }
    }
}

package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.IciclePacket;
import net.hubert.rupecs_emblems.network.packet.MoonWrathPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class IcicleHandlerClient {
    public static final KeyMapping ICICLE_KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player != event.getEntity()) return;

        var icicleAttr = player.getAttribute(ModAttributes.ICICLE.get());
        if (icicleAttr == null || icicleAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get("Icicle");

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;

        if (ICICLE_KEY.isDown() && !hasCooldown) {
            PacketHandler.sendToServer(new IciclePacket());

            CooldownManager.addCooldown(player.getUUID(), "Icicle", 200,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/icicle.png"));
        }

    }

}

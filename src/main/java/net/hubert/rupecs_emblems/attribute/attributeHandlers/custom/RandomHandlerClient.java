package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.GamblingPacket;
import net.hubert.rupecs_emblems.network.packet.RandomPacket;
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
public class RandomHandlerClient {
    public static final KeyMapping RANDOM_KEY = Keybinds.INSTANCE.thirdEmblemAbility;
    private static final String COOLDOWN_NAME = "Random";
    private static final int COOLDOWN_SECONDS = 20;
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (mc.player != null) {

            var randomAttr = player.getAttribute(ModAttributes.RANDOM.get());
            if (randomAttr == null || randomAttr.getValue() <= 0) return;
            if (!(RANDOM_KEY.isDown())) return;
            ChaosChaos(player);



        }
    }
    public static void ChaosChaos(Player player){
        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        CooldownEntry cooldownEntry = playerCooldowns.get(COOLDOWN_NAME);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        PacketHandler.sendToServer(new RandomPacket());
        int cooldownTicks = COOLDOWN_SECONDS * 20;
        CooldownManager.addCooldown(
                playerUUID,
                COOLDOWN_NAME,
                cooldownTicks,
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/random.png")
        );
    }
}

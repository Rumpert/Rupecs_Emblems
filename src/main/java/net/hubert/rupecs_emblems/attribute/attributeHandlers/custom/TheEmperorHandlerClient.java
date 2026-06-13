package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.EmperorPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class TheEmperorHandlerClient {
    public static final KeyMapping KEY = Keybinds.INSTANCE.primaryEmblemAbility;
    private static final String COOLDOWN_NAME = "TheEmperor";
    private static final int COOLDOWN_SECONDS = 60;
    private static final String POINT_NAME = "TheEmperorPoint";
    private static final int POINT_SECONDS = 180;
    private static BlockPos pointPos;
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null)return;

        var emperorAttr = player.getAttribute(ModAttributes.THE_EMPEROR.get());
        if (emperorAttr == null || emperorAttr.getValue() <= 0) return;
        if (!KEY.isDown()) return;


        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        CooldownEntry cooldownEntry = playerCooldowns.get(COOLDOWN_NAME);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        CooldownEntry cooldownPointEntry = playerCooldowns.get(POINT_NAME);

        if (cooldownPointEntry != null && cooldownPointEntry.getTicks() > 0 && pointPos != null) {
            PacketHandler.sendToServer(new EmperorPacket(pointPos));
            CooldownManager.removeCooldown(playerUUID, POINT_NAME);
            pointPos = null;
            int cooldownTicks = COOLDOWN_SECONDS * 20;
            CooldownManager.addCooldown(
                    playerUUID,
                    COOLDOWN_NAME,
                    cooldownTicks,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/the_emperor.png")
            );
        }else{
            pointPos = player.getOnPos();
            CooldownManager.addCooldown(
                    playerUUID,
                    POINT_NAME,
                    (int) (POINT_SECONDS * 20 * player.getAttributeValue(ModAttributes.THE_EMPEROR.get())),
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/the_emperor_point.png")
            );
        }




    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        UUID playerUUID = player.getUUID();
        Map<String, CooldownEntry> playerCooldowns = CooldownManager.getCooldownEntries(playerUUID);
        CooldownEntry cooldownEntry = playerCooldowns.get(COOLDOWN_NAME);
        if (cooldownEntry != null && cooldownEntry.getTicks() > 0) return;
        CooldownEntry cooldownPointEntry = playerCooldowns.get(POINT_NAME);
        if (cooldownPointEntry!= null && cooldownPointEntry.getTicks() > 0 && pointPos == null){
            CooldownManager.removeCooldown(playerUUID, POINT_NAME);
        }
    }

}

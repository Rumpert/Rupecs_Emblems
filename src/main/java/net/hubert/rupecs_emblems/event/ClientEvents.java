package net.hubert.rupecs_emblems.event;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.util.CooldownManager;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    private static String lastWorldIdentifier = "";

    @SubscribeEvent
    public static void onWorldLeave(ClientPlayerNetworkEvent.LoggingOut event) {
        Player player = event.getPlayer();
        if (player == null){
            return;
        }
        String currentWorld = player.level().dimension().location().toString();

        if (!currentWorld.equals(lastWorldIdentifier)) {
            CooldownManager.clear();
            lastWorldIdentifier = currentWorld;
        }
    }




}
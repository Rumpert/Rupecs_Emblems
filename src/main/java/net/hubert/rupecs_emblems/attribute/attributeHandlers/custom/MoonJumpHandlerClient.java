// AsteroidLaunchHandlerClient.java
package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class MoonJumpHandlerClient {
    public static final KeyMapping ASTEROID_KEY = Keybinds.INSTANCE.primaryEmblemAbility;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !ASTEROID_KEY.consumeClick()) return;

        // Always send the packet to the server
        MoonJumpHandlerCommon.triggerLaunch(mc.player);
    }
}
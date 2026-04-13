package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.BonemealTogglePacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MotherEarthBlessingHandlerClient {
    @OnlyIn(Dist.CLIENT)
    public static final KeyMapping MOTHER_EARTH_BONEMEAL_TOGGLE = Keybinds.INSTANCE.thirdEmblemAbility;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !MOTHER_EARTH_BONEMEAL_TOGGLE.consumeClick()) return;
        if (mc.player.level().isClientSide) {
            boolean value = !MotherEarthBlessingHandler.isBonemealToggled();
            MotherEarthBlessingHandler.setBonemealToggled(value);
            PacketHandler.sendToServer(new BonemealTogglePacket(value));
        }
    }
}

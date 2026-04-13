package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber
public class RandomHandlerCommon {
    private static List<ModEmblemItem> fakeEmblems = new ArrayList<>();


    private static void sendMsg(Player player, String msg, ChatFormatting color) {
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal(msg).withStyle(color));
        }
    }
    @SubscribeEvent
    public static void provideEmblemEffect(TickEvent.PlayerTickEvent event){
        if (!(event.phase == TickEvent.PlayerTickEvent.Phase.END)) return;
        for (ModEmblemItem item : fakeEmblems){
            EmblemEffectProvider.provideConstantEmblemEffect(item, event.player);
        }
    }

    public static void setEmblemList(List<ModEmblemItem> itemsToGive, Player player) {
        for (ModEmblemItem item : fakeEmblems){
            EmblemEffectProvider.provideOnUnequipEmblemEffect(item, player);
        }
        fakeEmblems = itemsToGive;
        for (ModEmblemItem item : fakeEmblems){
            EmblemEffectProvider.provideOnEquipEmblemEffect(item, player);
            String hoverName = (item.getDefaultInstance().getHoverName()).getString();
            sendMsg(player, "Copied: " + hoverName, item.getEmblemTier().getColor());
            sendMsg(player, item.getToolTip(), item.getEmblemTier().getColor());
        }

    }
    public static void removeEmblemList(Player player){
        for (ModEmblemItem item : fakeEmblems){
            EmblemEffectProvider.provideOnUnequipEmblemEffect(item, player);
        }
    }
    public static void shrinkEmblemList(Player player){
        if (fakeEmblems.size() > player.getAttribute(ModAttributes.RANDOM.get()).getValue()){
            EmblemEffectProvider.provideOnUnequipEmblemEffect(fakeEmblems.get(fakeEmblems.size()-1), player);
            fakeEmblems.remove(fakeEmblems.size()-1);
        }
    }

}

package net.hubert.rupecs_emblems.client;

import com.mojang.datafixers.util.Either;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MotherEarthBlessingHandler;
import net.hubert.rupecs_emblems.item.EmblemTiers;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.EmblemSlotAdder;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.logging.Level;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemNameRenderHandler {

    @SubscribeEvent
    public static void onTooltip(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof ModEmblemItem emblem && (emblem.getEmblemTier() == EmblemTiers.SS || emblem.getEmblemTier() == EmblemTiers.XSS || emblem.getEmblemTier() == EmblemTiers.U)) {
            if (emblem.getEmblemTier() == EmblemTiers.SS || emblem.getEmblemTier() == EmblemTiers.XSS) {
                ClientLevel level = Minecraft.getInstance().level;
                if (level == null) return;
                int tick = (int) level.getGameTime();

                // Replace the item name line with rainbow
                List<Either<FormattedText, TooltipComponent>> elements = event.getTooltipElements();
                if (!elements.isEmpty()) {
                    elements.set(0, Either.left(FancyText.rainbowWave(stack.getHoverName().getString(), tick)));
                }
            } else if (emblem.getEmblemTier() == EmblemTiers.U) {
                ClientLevel level = Minecraft.getInstance().level;
                if (level == null) return;
                int tick = (int) level.getGameTime();

                // Replace the item name line with rainbow
                List<Either<FormattedText, TooltipComponent>> elements = event.getTooltipElements();
                if (!elements.isEmpty()) {
                    elements.set(0, Either.left(FancyText.colorSwitchAll(stack.getHoverName().getString(), tick, new int[]{
                            0xFFFFFF,
                            0xAAAAFF,
                            0xBBBBFF
                    })));
                }
            }
        }
        if (stack.getItem() instanceof EmblemSlotAdder emblem) {
            ClientLevel level = Minecraft.getInstance().level;
            if (level == null)return;
            int tick = (int) level.getGameTime();

            // Replace the item name line with rainbow
            List<Either<FormattedText, TooltipComponent>> elements = event.getTooltipElements();
            if (!elements.isEmpty()) {
                elements.set(0, Either.left(FancyText.fallingStar(stack.getHoverName().getString(), tick, level.random, 0,0,255,60,60,60)));
            }
        }
        if (stack.getItem() instanceof ModEmblemItem emblemItem) {
            if (stack.is(ModItems.MOTHER_EARTH_EMBLEM.get())) {
                List<Either<FormattedText, TooltipComponent>> elements = event.getTooltipElements();

                // Example: add a line showing passive bonemeal status
                String status = MotherEarthBlessingHandler.isBonemealToggled() ? "Passive Bonemeal: ON" : "Passive Bonemeal: OFF";
                elements.add(Either.left(Component.literal(status).withStyle(MotherEarthBlessingHandler.isBonemealToggled() ? ChatFormatting.GREEN : ChatFormatting.RED)));
            }
        }
    }
}

package net.hubert.rupecs_emblems.mixin;

import net.hubert.rupecs_emblems.item.EmblemTiers;
import net.hubert.rupecs_emblems.item.custom.EmblemSlotAdder;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow
    protected int toolHighlightTimer;
    @Shadow
    protected ItemStack lastToolHighlight;
    @Shadow
    protected int screenWidth;
    @Shadow
    protected int screenHeight;
    @Shadow public abstract Font getFont();

    @Shadow @Final protected RandomSource random;

    @Inject(method = "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRenderSelectedItemName(GuiGraphics pGuiGraphics, int yShift, CallbackInfo ci) {
        Gui gui = (Gui)(Object)this;
        var minecraft = Minecraft.getInstance();

        if (this.toolHighlightTimer > 0 && !this.lastToolHighlight.isEmpty()) {
            if (this.lastToolHighlight.getItem() instanceof ModEmblemItem emblemItem &&
                (emblemItem.getEmblemTier() == EmblemTiers.SS || emblemItem.getEmblemTier() == EmblemTiers.XSS || emblemItem.getEmblemTier() == EmblemTiers.U)) {

                int tickCount = minecraft.gui.getGuiTicks();
                MutableComponent mutablecomponent = Component.empty();
                if (emblemItem.getEmblemTier() == EmblemTiers.SS || emblemItem.getEmblemTier() == EmblemTiers.XSS) {
                    mutablecomponent = Component.empty()
                            .append(FancyText.rainbowWave(this.lastToolHighlight.getHoverName(), tickCount));
                } else if (emblemItem.getEmblemTier() == EmblemTiers.U) {
                    mutablecomponent = Component.empty()
                            .append(FancyText.colorSwitchAll(this.lastToolHighlight.getHoverName().getString(), tickCount, new int[]{
                                    0xFFFFFF,
                                    0xAAAAFF,
                                    0xBBBBFF
                            }));
                }
                if (this.lastToolHighlight.hasCustomHoverName()) {
                    mutablecomponent = mutablecomponent.copy().withStyle(ChatFormatting.ITALIC);
                }

                Component highlightTip = this.lastToolHighlight.getHighlightTip(mutablecomponent);
                int i = this.getFont().width(highlightTip);
                int j = (this.screenWidth - i) / 2;
                int k = this.screenHeight - Math.max(yShift, 59);

                if (!minecraft.gameMode.canHurtPlayer()) {
                    k += 14;
                }

                int l = (int)((float)this.toolHighlightTimer * 256.0F / 10.0F);
                if (l > 255) {
                    l = 255;
                }

                if (l > 0) {
                    pGuiGraphics.fill(j - 2, k - 2, j + i + 2, k + 9 + 2, minecraft.options.getBackgroundColor(0));

                    Font font = net.minecraftforge.client.extensions.common.IClientItemExtensions.of(this.lastToolHighlight)
                        .getFont(this.lastToolHighlight, net.minecraftforge.client.extensions.common.IClientItemExtensions.FontContext.SELECTED_ITEM_NAME);

                    if (font == null) {
                        pGuiGraphics.drawString(this.getFont(), highlightTip, j, k, 16777215 + (l << 24));
                    } else {
                        j = (this.screenWidth - font.width(highlightTip)) / 2;
                        pGuiGraphics.drawString(font, highlightTip, j, k, 16777215 + (l << 24));
                    }
                }

                ci.cancel(); // Cancel the original vanilla rendering
            }
            if (this.lastToolHighlight.getItem() instanceof EmblemSlotAdder emblemItem) {

                int tickCount = minecraft.gui.getGuiTicks();
                MutableComponent mutablecomponent = Component.empty()
                    .append(FancyText.fallingStar(this.lastToolHighlight.getHoverName(), tickCount, random, 64,128,255,90,90,90));

                if (this.lastToolHighlight.hasCustomHoverName()) {
                    mutablecomponent = mutablecomponent.copy().withStyle(ChatFormatting.ITALIC);
                }

                Component highlightTip = this.lastToolHighlight.getHighlightTip(mutablecomponent);
                int i = this.getFont().width(highlightTip);
                int j = (this.screenWidth - i) / 2;
                int k = this.screenHeight - Math.max(yShift, 59);

                if (!minecraft.gameMode.canHurtPlayer()) {
                    k += 14;
                }

                int l = (int)((float)this.toolHighlightTimer * 256.0F / 10.0F);
                if (l > 255) {
                    l = 255;
                }

                if (l > 0) {
                    pGuiGraphics.fill(j - 2, k - 2, j + i + 2, k + 9 + 2, minecraft.options.getBackgroundColor(0));

                    Font font = net.minecraftforge.client.extensions.common.IClientItemExtensions.of(this.lastToolHighlight)
                        .getFont(this.lastToolHighlight, net.minecraftforge.client.extensions.common.IClientItemExtensions.FontContext.SELECTED_ITEM_NAME);

                    if (font == null) {
                        pGuiGraphics.drawString(this.getFont(), highlightTip, j, k, 16777215 + (l << 24));
                    } else {
                        j = (this.screenWidth - font.width(highlightTip)) / 2;
                        pGuiGraphics.drawString(font, highlightTip, j, k, 16777215 + (l << 24));
                    }
                }

                ci.cancel(); // Cancel the original vanilla rendering
            }
        }
    }

}
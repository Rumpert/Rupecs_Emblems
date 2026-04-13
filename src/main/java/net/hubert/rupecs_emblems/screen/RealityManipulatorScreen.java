package net.hubert.rupecs_emblems.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.awt.*;

public class RealityManipulatorScreen extends AbstractContainerScreen<RealityManipulatorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/gui/reality_manipulator_gui.png");

    public RealityManipulatorScreen(RealityManipulatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth)/ 2;
        int y = (height - imageHeight)/ 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, x, y);
        renderChanceLabel(pGuiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int x, int y) {
        if (menu.isCrafting()){
            pGuiGraphics.blit(TEXTURE, x + 80, y+33, 176, 0, 18, menu.getScaledProgress());
        }
    }

    private void renderChanceLabel(GuiGraphics pGuiGraphics, int x, int y) {
        float chance = menu.getCraftChance();
        String text;
        text = String.format("%.1f", chance) + "%";


        // Get the font renderer - you need to access it properly
        Font font = Minecraft.getInstance().font;

        // Render the text with proper positioning
        pGuiGraphics.drawString(font, text, x + 103, y+36, 0xFFFFFF, false);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

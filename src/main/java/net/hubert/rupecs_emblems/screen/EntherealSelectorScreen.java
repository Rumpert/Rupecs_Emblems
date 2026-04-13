package net.hubert.rupecs_emblems.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EntherealSelectorScreen extends AbstractContainerScreen<EntherealSelectorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/gui/enthereal_selector_gui.png");

    public EntherealSelectorScreen(EntherealSelectorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int x, int y) {
        if (menu.isCrafting()){
            pGuiGraphics.blit(TEXTURE, x + 57, y+31, 176, 0, menu.getScaledProgress(), 22);

            pGuiGraphics.blit(TEXTURE, x + 76, y + 34, 176 + ((menu.getProgress()/10)%3)*17, 23, 16, 22);

        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

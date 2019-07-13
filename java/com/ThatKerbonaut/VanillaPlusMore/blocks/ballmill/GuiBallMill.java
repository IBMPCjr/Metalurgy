package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill;

import com.ThatKerbonaut.VanillaPlusMore.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBallMill extends GuiContainer{
	private static final ResourceLocation BALL_MILL_TEXTURE = new ResourceLocation("assets/vpmm/textures/gui/ball_mill.png");
	private final InventoryPlayer playerInventory;
	private final IInventory tileMill;
	public GuiBallMill(InventoryPlayer playerInv, IInventory millInv) {
		super(new ContainerBallMill(playerInv, millInv));
		this.playerInventory = playerInv;
		this.tileMill = millInv;
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tileMill.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BALL_MILL_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        if (TileEntityBallMill.isMilling(this.tileMill)){
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }
	private int getCookProgressScaled(int pixels){
		int i = this.tileMill.getField(2);
		int j = this.tileMill.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
	private int getBurnLeftScaled(int pixels){
		int i = this.tileMill.getField(1);
		if(i == 0){
			i = 200;
		}
		return this.tileMill.getField(0) * pixels / i;
	}
}

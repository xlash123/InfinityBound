package Noah.InfinityBound.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import Noah.InfinityBound.inventory.ContainerFuser;
import Noah.InfinityBound.tileentity.TileEntityFuser;

public class GuiFuser extends GuiContainer
{
    private static final ResourceLocation fuserGuiTextures = new ResourceLocation("InfinityBound:" + "textures/gui/container/fuser.png");
    private TileEntityFuser fuserInventory;

    public GuiFuser(InventoryPlayer par1InventoryPlayer, TileEntityFuser par2TileEntityFuser)
    {
        super(new ContainerFuser(par1InventoryPlayer, par2TileEntityFuser));
        this.fuserInventory = par2TileEntityFuser;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.fuserInventory.isInvNameLocalized() ? this.fuserInventory.getInvName() : I18n.getString(this.fuserInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(fuserGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.fuserInventory.isBurning())
        {
            i1 = this.fuserInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 11, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.fuserInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 89, l + 34, 176, 14, i1 + 1, 16);
    }
}

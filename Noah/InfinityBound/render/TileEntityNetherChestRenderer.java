package Noah.InfinityBound.render;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Noah.InfinityBound.tileentity.TileEntityNetherChest;
import Noah.InfinityBound.blockdata.BlockNetherChest;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityNetherChestRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation netherChestTexture = new ResourceLocation("InfinityBound:" + "textures/entity/chest/nether_chest.png");

    /** The normal small chest model. */
    private ModelChest chestModel = new ModelChest();

    /** The large double chest model. */
    private ModelChest largeChestModel = new ModelLargeChest();

    /** If true, chests will be rendered with the Christmas present textures. */
    private boolean isChristmas;

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void renderTileEntityChestAt(TileEntityNetherChest par1TileEntityChest, double par2, double par4, double par6, float par8)
    {
        int i;

        if (!par1TileEntityChest.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = par1TileEntityChest.getBlockType();
            i = par1TileEntityChest.getBlockMetadata();

            if (block instanceof BlockNetherChest && i == 0)
            {
                i = par1TileEntityChest.getBlockMetadata();
            }
        }

        if (par1TileEntityChest.adjacentChestZNeg == null && par1TileEntityChest.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (par1TileEntityChest.adjacentChestXPos == null && par1TileEntityChest.adjacentChestZPosition == null)
            {
                modelchest = this.chestModel;

                if (par1TileEntityChest.func_98041_l() == 1)
                {
                    this.bindTexture(netherChestTexture);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(netherChestTexture);
                }
                else
                {
                    this.bindTexture(netherChestTexture);
                }
            }
            else
            {
                modelchest = this.largeChestModel;

                if (par1TileEntityChest.func_98041_l() == 1)
                {
                    this.bindTexture(netherChestTexture);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(netherChestTexture);
                }
                else
                {
                    this.bindTexture(netherChestTexture);
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2)
            {
                short1 = 180;
            }

            if (i == 3)
            {
                short1 = 0;
            }

            if (i == 4)
            {
                short1 = 90;
            }

            if (i == 5)
            {
                short1 = -90;
            }

            if (i == 2 && par1TileEntityChest.adjacentChestXPos != null)
            {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && par1TileEntityChest.adjacentChestZPosition != null)
            {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
            float f2;

            if (par1TileEntityChest.adjacentChestZNeg != null)
            {
                f2 = par1TileEntityChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityChest.adjacentChestZNeg.lidAngle - par1TileEntityChest.adjacentChestZNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (par1TileEntityChest.adjacentChestXNeg != null)
            {
                f2 = par1TileEntityChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityChest.adjacentChestXNeg.lidAngle - par1TileEntityChest.adjacentChestXNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityChestAt((TileEntityNetherChest)par1TileEntity, par2, par4, par6, par8);
    }
}

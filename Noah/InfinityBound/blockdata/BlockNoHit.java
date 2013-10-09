package Noah.InfinityBound.blockdata;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockNoHit extends Block
{
    public String textureName;
    public String modName;

    public BlockNoHit(int par1, Material par2Material, String par3Str, String par4Str)
    {
        super(par1, par2Material);
        this.textureName = par3Str;
        this.modName = par4Str;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
        return false;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        if (blockID == Blocks.stoneNoHit.blockID)
        {
            return Blocks.cobblestoneNoHit.blockID;
        }
        else
        {
            return this.blockID;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.modName + ":" + this.textureName);
    }
}

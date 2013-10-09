package Noah.InfinityBound.blockdata;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGlassNoHit extends BlockGlass
{
    public String textureName;
    public String modName;

    protected BlockGlassNoHit(int par1, Material par2Material, String par3Str, String par4Str, boolean par5Bool)
    {
        super(par1, par2Material, par5Bool);
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

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.modName + ":" + this.textureName);
    }
}

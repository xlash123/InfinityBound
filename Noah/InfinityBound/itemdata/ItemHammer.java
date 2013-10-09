package Noah.InfinityBound.itemdata;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;

public class ItemHammer extends net.minecraft.item.ItemTool
{
    /** an array of the blocks this axe is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.melon, Block.glass, Block.leaves, Block.pumpkin, Block.pumpkinLantern, Block.web};

    public ItemHammer(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, 3.0F, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }
}

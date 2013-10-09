package Noah.InfinityBound.itemdata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemBasic extends Item
{
    public ItemBasic(int par1)
    {
        super(par1);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }
}

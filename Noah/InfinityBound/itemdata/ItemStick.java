package Noah.InfinityBound.itemdata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemStick extends Item
{
    public static int stickType;

    public ItemStick(int par1, int par2)
    {
        super(par1);
        this.stickType = par2;
    }

    public static Item getStickType()
    {
        if (stickType == 2)
        {
            return Items.stickStone;
        }

        if (stickType == 3)
        {
            return Items.stickIron;
        }

        if (stickType == 4)
        {
            return Items.stickGold;
        }

        if (stickType == 5)
        {
            return Items.stickDiamond;
        }

        if (stickType == 6)
        {
            return Items.stickBedrock;
        }

        return null;
    }

    public static String getStickName()
    {
        if (stickType == 2)
        {
            return "Stone";
        }

        if (stickType == 3)
        {
            return "Iron";
        }

        if (stickType == 4)
        {
            return "Gold";
        }

        if (stickType == 5)
        {
            return "Diamond";
        }

        if (stickType == 6)
        {
            return "Gold";
        }

        return null;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }
}

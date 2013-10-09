package Noah.InfinityBound.itemdata;

import Noah.InfinityBound.blockdata.Blocks;
import net.minecraft.item.ItemStack;

public class ItemBlock extends net.minecraft.item.ItemBlock
{
    public ItemBlock(int par1)
    {
        super(par1);
        setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack itemstack)
    {
        String name = "";

        if (itemID == Blocks.planksNoHit.blockID)
        {
            switch (itemstack.getItemDamage())
            {
                case 0:
                {
                    name = "oak";
                    break;
                }

                case 1:
                {
                    name = "spruce";
                    break;
                }

                case 2:
                {
                    name = "birch";
                    break;
                }

                case 3:
                {
                    name = "jungle";
                    break;
                }

                default:
                    name = "oak";
            }
        }

        return getUnlocalizedName() + "." + name;
    }

    public int getMetadata(int par1)
    {
        return par1;
    }
}

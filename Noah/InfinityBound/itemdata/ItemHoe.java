package Noah.InfinityBound.itemdata;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHoe extends net.minecraft.item.ItemHoe
{
    protected EnumToolMaterial theToolMaterial;
    /**The type of stick used - 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock. */
    protected int stickType;
    /** The material the tool is - 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock. */
    protected int toolType;

    public ItemHoe(int par1, EnumToolMaterial par2EnumToolMaterial, int par3, int par4)
    {
        super(par1, par2EnumToolMaterial);
        this.theToolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabTools);
        this.stickType = par3;
        this.toolType = par4;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);

            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Result.ALLOW)
            {
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }

            int i1 = par3World.getBlockId(par4, par5, par6);
            int j1 = par3World.getBlockId(par4, par5 + 1, par6);

            if ((par7 == 0 || j1 != 0 || i1 != Block.grass.blockID) && i1 != Block.dirt.blockID)
            {
                return false;
            }
            else
            {
                Block block = Block.tilledField;
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                if (par3World.isRemote)
                {
                    return true;
                }
                else
                {
                    par3World.setBlock(par4, par5, par6, block.blockID);
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                    return true;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Returns the name of the material this tool is made from as it is declared in EnumToolMaterial (meaning diamond
     * would return "EMERALD")
     */
    public String getMaterialName()
    {
        return this.theToolMaterial.toString();
    }
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.getToolType() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    public int getStickType()
    {
        if (stickType == 1)
        {
            return Block.planks.blockID;
        }

        if (stickType == 2)
        {
            return Block.cobblestone.blockID;
        }

        if (stickType == 3)
        {
            return Item.ingotIron.itemID;
        }

        if (stickType == 4)
        {
            return Item.ingotGold.itemID;
        }

        if (stickType == 5)
        {
            return Item.diamond.itemID;
        }

        if (stickType == 6)
        {
            return Items.piecesBedrock.itemID;
        }
        else
        {
            return 0;
        }
    } public int getToolType()
    {
        if (toolType == 1)
        {
            return Block.planks.blockID;
        }

        if (toolType == 2)
        {
            return Block.cobblestone.blockID;
        }

        if (toolType == 3)
        {
            return Item.ingotIron.itemID;
        }

        if (toolType == 4)
        {
            return Item.ingotGold.itemID;
        }

        if (toolType == 5)
        {
            return Item.diamond.itemID;
        }

        if (toolType == 6)
        {
            return Items.piecesBedrock.itemID;
        }
        else
        {
            return 0;
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }
}

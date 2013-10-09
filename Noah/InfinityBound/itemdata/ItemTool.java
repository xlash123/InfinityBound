package Noah.InfinityBound.itemdata;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumToolMaterial;

public class ItemTool extends net.minecraft.item.ItemTool
{
    private Block[] blocksEffectiveAgainst;

    /**The type of stick used - 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock. */
    protected int stickType;

    /** The material the tool is - 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock. */
    protected int toolType;

    protected ItemTool(int par1, float par2, EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock, int par5, int par6)
    {
        super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
        this.toolMaterial = par3EnumToolMaterial;
        this.blocksEffectiveAgainst = par4ArrayOfBlock;
        this.maxStackSize = 1;
        this.setMaxDamage(par3EnumToolMaterial.getMaxUses());
        this.efficiencyOnProperMaterial = par3EnumToolMaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = par2 + par3EnumToolMaterial.getDamageVsEntity();
        this.setCreativeTab(CreativeTabs.tabTools);
        this.stickType = par5;
        this.toolType = par6;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.getToolType() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    public int getToolTypeMaterial()
    {
        return toolType;
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
}

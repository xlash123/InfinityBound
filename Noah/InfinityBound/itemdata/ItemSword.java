package Noah.InfinityBound.itemdata;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.sound.PlaySoundEffectEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class ItemSword extends net.minecraft.item.ItemSword
{
    private float weaponDamage;
    private final EnumToolMaterial toolMaterial;
    /** Type of stick used - 0=null 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock. */
    protected int stickType;
    /** The material the tool is - 0=null 1=Wood 2=Stone 3=Iron 4=Gold 5=Diamond 6=Bedrock 7=instakill */
    protected int toolType;

    public ItemSword(int par1, EnumToolMaterial par2EnumToolMaterial, int par3, int par4)
    {
        super(par1, par2EnumToolMaterial);
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.weaponDamage = 4.0F + par2EnumToolMaterial.getDamageVsEntity();
        this.stickType = par3;
        this.toolType = par4;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        if (j >= 64)
        {
            par3EntityPlayer.addPotionEffect(new PotionEffect(5, 67));
        }
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
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

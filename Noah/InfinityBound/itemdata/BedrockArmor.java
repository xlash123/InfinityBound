package Noah.InfinityBound.itemdata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import Noah.InfinityBound.itemdata.*;

public class BedrockArmor extends ItemArmor
{
    public BedrockArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return Items.piecesBedrock.itemID == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        if (itemID == Items.helmetBedrock.itemID || itemID == Items.plateBedrock.itemID || itemID == Items.bootsBedrock.itemID)
        {
            return "InfinityBound:textures/models/armor/bedrock_layer_1.png";
        }

        if (itemID == Items.legsBedrock.itemID)
        {
            return "InfinityBound:textures/models/armor/bedrock_layer_2.png";
        }
        else
        {
            return null;
        }
    }
}

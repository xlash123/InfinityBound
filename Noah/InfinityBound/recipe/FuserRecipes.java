package Noah.InfinityBound.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import Noah.InfinityBound.blockdata.Blocks;
import Noah.InfinityBound.itemdata.Items;
import Noah.InfinityBound.tileentity.TileEntityFuser;

public class FuserRecipes
{
    private static final FuserRecipes fuseBase = new FuserRecipes();

    /** The list of smelting results. */
    private Map fusingList = new HashMap();
    private ItemStack returnedItem;
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaFusingList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final FuserRecipes fusing()
    {
        return fuseBase;
    }

    private FuserRecipes()
    {
        this.addFuse(Items.crystalNormal.itemID, new ItemStack(Items.crystalNormalFused), 1.0F);
        this.addFuse(Items.crystalSuper.itemID, new ItemStack(Items.crystalSuperFused), 1.0F);
        this.addFuse(Items.crystalUltra.itemID, new ItemStack(Items.crystalUltraFused), 1.0F);
        this.addFuse(Items.crystalInfinity.itemID, new ItemStack(Items.crystalInfinityFused), 1.0F);
    }

    /**
     * Adds a chopping recipe.
     * @return
     */
    public void addFuse(int par1, ItemStack par2ItemStack, float par3)
    {
        this.fusingList.put(Integer.valueOf(par1), par2ItemStack);
        this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
    }

    /**
     * Returns the cut result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getChoppingResult(int par1)
    {
        return (ItemStack)this.fusingList.get(Integer.valueOf(par1));
    }

    public Map getFusingList()
    {
        return this.fusingList;
    }

    /**
     * A metadata sensitive version of adding a chopping recipe.
     */
    public void addChopping(int itemID, int metadata, ItemStack itemstack, float experience)
    {
        metaFusingList.put(Arrays.asList(itemID, metadata), itemstack);
        metaExperience.put(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()), experience);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getFusedResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }

        ItemStack ret = (ItemStack)metaFusingList.get(Arrays.asList(item.itemID, item.getItemDamage()));

        if (ret != null)
        {
            return ret;
        }

        return (ItemStack)fusingList.get(Integer.valueOf(item.itemID));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0;
        }

        float ret = item.getItem().getSmeltingExperience(item);

        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
        {
            ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
        }

        if (ret < 0 && experienceList.containsKey(item.itemID))
        {
            ret = ((Float)experienceList.get(item.itemID)).floatValue();
        }

        return (ret < 0 ? 0 : ret);
    }

    public Map<List<Integer>, ItemStack> getMetaSmeltingList()
    {
        return metaFusingList;
    }
}

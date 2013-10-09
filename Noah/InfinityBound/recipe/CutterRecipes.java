package Noah.InfinityBound.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Noah.InfinityBound.blockdata.Blocks;
import Noah.InfinityBound.itemdata.Items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CutterRecipes
{
    private static final CutterRecipes choppingBase = new CutterRecipes();

    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map smeltingListRare = new HashMap();
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaChoppingList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final CutterRecipes chopping()
    {
        return choppingBase;
    }

    private CutterRecipes()
    {
        this.addChopping(Blocks.oreBedrock.blockID, new ItemStack(Items.piecesBedrock), 1.1F, new ItemStack(Items.piecesBedrock));
        this.addChopping(Block.oreCoal.blockID, new ItemStack(Item.coal, 2), 0.1F, new ItemStack(Items.piecesIron));
        this.addChopping(Block.oreIron.blockID, new ItemStack(Items.piecesIron, 2), 0.7F, new ItemStack(Items.piecesGold));
        this.addChopping(Block.oreGold.blockID, new ItemStack(Items.piecesGold, 2), 1.0F, new ItemStack(Item.diamond));
        this.addChopping(Block.oreEmerald.blockID, new ItemStack(Item.emerald, 2), 1.0F, new ItemStack(Item.emerald));
        this.addChopping(Block.oreRedstone.blockID, new ItemStack(Item.redstone, 6), 0.7F, new ItemStack(Item.redstone, 4));
        this.addChopping(Block.oreLapis.blockID, new ItemStack(Item.dyePowder, 6, 4), 0.2F, new ItemStack(Item.dyePowder, 4, 4));
        this.addChopping(Block.oreDiamond.blockID, new ItemStack(Item.diamond, 2), 1.0F, new ItemStack(Item.diamond));
        this.addChopping(Block.oreNetherQuartz.blockID, new ItemStack(Item.netherQuartz, 2), 0.2F, new ItemStack(Item.netherQuartz));
        this.addChopping(Blocks.oreTfarcenim.blockID, new ItemStack(Items.piecesTfarcenim, 2), 0.8F, new ItemStack(Items.piecesIron));
        this.addChopping(Item.diamond.itemID, new ItemStack(Items.dustDiamond), 0.1F);
        this.addChopping(Blocks.oreIntangible.blockID, new ItemStack(Items.essenceIntangible), 0.8F, new ItemStack(Blocks.stoneNoHit));
        this.addChopping(Item.ingotGold.itemID, new ItemStack(Items.piecesGold), 0.1F);
        this.addChopping(Item.ingotIron.itemID, new ItemStack(Items.piecesIron), 0.1F);
        this.addChopping(Items.ingotTfarcenim.itemID, new ItemStack(Items.piecesTfarcenim), 0.1F);
    }

    /**
     * Adds a chopping recipe.
     */
    public void addChopping(int par1, ItemStack par2ItemStack, float par3, ItemStack rareItem)
    {
        this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
        this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
        this.smeltingListRare.put(Integer.valueOf(par1), rareItem);
    }

    /**
     * Adds a chopping recipe.
     */
    public void addChopping(int par1, ItemStack par2ItemStack, float par3)
    {
        this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
        this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
        this.smeltingListRare.put(Integer.valueOf(par1), null);
    }

    /**
     * Returns the cut result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getChoppingResult(int par1)
    {
        return (ItemStack)this.smeltingList.get(Integer.valueOf(par1));
    }

    public Map getChoppingList()
    {
        return this.smeltingList;
    }

    /**
     * A metadata sensitive version of adding a chopping recipe.
     */
    public void addChopping(int itemID, int metadata, ItemStack itemstack, float experience)
    {
        metaChoppingList.put(Arrays.asList(itemID, metadata), itemstack);
        metaExperience.put(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()), experience);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getCutterResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }

        ItemStack ret = (ItemStack)metaChoppingList.get(Arrays.asList(item.itemID, item.getItemDamage()));

        if (ret != null)
        {
            return ret;
        }

        return (ItemStack)smeltingList.get(Integer.valueOf(item.itemID));
    }

    public ItemStack getCutterResultRare(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }

        return (ItemStack)smeltingListRare.get(Integer.valueOf(item.itemID));
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
        return metaChoppingList;
    }
}

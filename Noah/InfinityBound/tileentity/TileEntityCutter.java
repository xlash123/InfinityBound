package Noah.InfinityBound.tileentity;

import java.util.Random;

import Noah.InfinityBound.recipe.CutterRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCutter extends TileEntity implements ISidedInventory
{
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};

    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] cutterItemStacks = new ItemStack[5];

    /** The number of ticks that the cutter will keep chopping */
    public int cutterWorkTime;
    public int multFour;
    public int workTimeSaveState;
    private ItemStack previousCutter;
    private boolean tick;

    /**
     * The number of ticks that a fresh copy of the currently-chopping item would keep the cutter chopping for
     */
    public int currentItemWorkTime;

    /** The number of ticks that the current item has been chopping for */
    public int cutterChopTime;
    private String field_94130_e;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.cutterItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.cutterItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.cutterItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.cutterItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.cutterItemStacks[par1];
                this.cutterItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.cutterItemStacks[par1].splitStack(par2);

                if (this.cutterItemStacks[par1].stackSize == 0)
                {
                    this.cutterItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.cutterItemStacks[par1] != null)
        {
            ItemStack itemstack = this.cutterItemStacks[par1];
            this.cutterItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.cutterItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : "container.cutter";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    /**
     * Sets the custom display name to use when opening a GUI linked to this tile entity.
     */
    public void setGuiDisplayName(String par1Str)
    {
        this.field_94130_e = par1Str;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.cutterItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.cutterItemStacks.length)
            {
                this.cutterItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.cutterWorkTime = par1NBTTagCompound.getShort("BurnTime");
        this.cutterChopTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemWorkTime = getItemBurnTime(this.cutterItemStacks[1]);

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.cutterWorkTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.cutterChopTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.cutterItemStacks.length; ++i)
        {
            if (this.cutterItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.cutterItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94130_e);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.cutterChopTime * par1 / numberSpeed();
    }

    public int numberSpeed()
    {
        return this.getItemBurnTime(previousCutterSlot());
    }

    public ItemStack previousCutterSlot()
    {
        if (this.cutterItemStacks[1] != null)
        {
            previousCutter = this.cutterItemStacks[1];
        }

        if (previousCutter == null)
        {
            previousCutter = new ItemStack(Item.pickaxeWood);
        }

        if (this.getItemBurnTime(cutterItemStacks[1]) == 0 && this.cutterItemStacks[1] != null)
        {
            previousCutter = new ItemStack(Item.pickaxeWood);
        }

        return previousCutter;
    }

    /**
     * Returns true if the cutter is currently chopping
     */
    public boolean isChopping()
    {
        return this.cutterWorkTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean flag = this.cutterWorkTime > 0;
        boolean flag1 = false;

        if (this.cutterWorkTime > 0)
        {
            --this.cutterWorkTime;
        }

        if (cutterItemStacks[0] == null)
        {
            cutterChopTime = 0;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.cutterWorkTime == 0 && this.canChop())
            {
                if (workTimeSaveState > 0)
                {
                    this.cutterWorkTime = workTimeSaveState;
                }

                this.currentItemWorkTime = this.cutterWorkTime = getItemBurnTime(this.cutterItemStacks[1]);

                if (this.cutterWorkTime > 0)
                {
                    flag1 = true;

                    if (this.cutterItemStacks[1] != null)
                    {
                        this.previousCutter = this.cutterItemStacks[1];
                        Random rand = new Random();
                        this.cutterItemStacks[1].attemptDamageItem(1, rand);
                        int maxHit = this.cutterItemStacks[1].getMaxDamage();

                        if (cutterItemStacks[1].getItemDamage() > maxHit)
                        {
                            --cutterItemStacks[1].stackSize;
                        }

                        if (this.cutterItemStacks[1].stackSize == 0)
                        {
                            this.cutterItemStacks[1] = this.cutterItemStacks[1].getItem().getContainerItemStack(cutterItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isChopping() && this.canChop())
            {
                ++this.cutterChopTime;
                Random rand = new Random();

                if (cutterItemStacks[1] != null)
                {
                    ++this.multFour;

                    if (multFour == 4)
                    {
                        this.cutterItemStacks[1].attemptDamageItem(1, rand);
                        this.multFour = 0;
                        int maxHit = this.cutterItemStacks[1].getMaxDamage();

                        if (cutterItemStacks[1].getItemDamage() > maxHit)
                        {
                            cutterItemStacks[1].stackSize--;
                        }

                        if (this.cutterItemStacks[1].stackSize == 0)
                        {
                            this.cutterItemStacks[1] = this.cutterItemStacks[1].getItem().getContainerItemStack(cutterItemStacks[1]);
                        }
                    }
                }

                if (this.cutterChopTime == this.getItemBurnTime(cutterItemStacks[1]))
                {
                    this.cutterChopTime = 0;
                    this.cutItem();
                    flag1 = true;
                }

                if (cutterItemStacks[1] == null && cutterChopTime > 0)
                {
                    this.workTimeSaveState = cutterWorkTime;
                    cutterWorkTime = 0;
                }

                if (this.cutterChopTime > this.getItemBurnTime(previousCutter))
                {
                    this.cutterChopTime = 0;
                    this.cutItem();
                    flag1 = true;
                }
            }
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the cutter can chop an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canChop()
    {
        if (this.cutterItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = CutterRecipes.chopping().getCutterResult(this.cutterItemStacks[0]);

            if (itemstack == null)
            {
                return false;
            }

            if (this.cutterItemStacks[2] == null)
            {
                return true;
            }

            if (!this.cutterItemStacks[2].isItemEqual(itemstack) && this.cutterItemStacks[4] != null && !this.cutterItemStacks[4].isItemEqual(itemstack))
            {
                return false;
            }

            if (this.cutterItemStacks[4] == null)
            {
                return true;
            }

            if (!this.cutterItemStacks[4].isItemEqual(itemstack) && this.cutterItemStacks[2] != null && !this.cutterItemStacks[2].isItemEqual(itemstack))
            {
                return false;
            }

            int result = cutterItemStacks[2].stackSize + itemstack.stackSize;
            int result4 = cutterItemStacks[4].stackSize + itemstack.stackSize;

            if (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize())
            {
                return true;
            }

            if (result >= getInventoryStackLimit() && result >= itemstack.getMaxStackSize())
            {
                if (result4 <= getInventoryStackLimit() && result4 <= itemstack.getMaxStackSize())
                {
                    return true;
                }
            }

            if (result >= getInventoryStackLimit() && result >= itemstack.getMaxStackSize())
            {
                return false;
            }

            if (result >= getInventoryStackLimit() && result >= itemstack.getMaxStackSize())
            {
                if (result4 >= getInventoryStackLimit() && result4 >= itemstack.getMaxStackSize())
                {
                    return false;
                }
            }

            return false;
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void cutItem()
    {
        if (this.canChop())
        {
            double rand = Math.random();
            ItemStack itemstack = CutterRecipes.chopping().getCutterResult(this.cutterItemStacks[0]);
            ItemStack rarestack = CutterRecipes.chopping().getCutterResultRare(this.cutterItemStacks[0]);

            if (this.cutterItemStacks[2] == null)
            {
                this.cutterItemStacks[2] = itemstack.copy();
            }
            else if (this.cutterItemStacks[2].isItemEqual(itemstack) && this.cutterItemStacks[2].stackSize < itemstack.getMaxStackSize())
            {
                cutterItemStacks[2].stackSize += itemstack.stackSize;
            }

            if (this.cutterItemStacks[4] == null && this.cutterItemStacks[2] != null)
            {
                if (!this.cutterItemStacks[2].isItemEqual(itemstack))
                {
                    this.cutterItemStacks[4] = itemstack.copy();
                }

                if (tick == true)
                {
                    this.cutterItemStacks[4] = itemstack.copy();
                }

                if (this.cutterItemStacks[2].stackSize >= itemstack.getMaxStackSize() && tick == false)
                {
                    tick = true;
                }
            }
            else if (this.cutterItemStacks[4].isItemEqual(itemstack) && this.cutterItemStacks[2].stackSize >= itemstack.getMaxStackSize() || this.cutterItemStacks[4].isItemEqual(itemstack) && this.cutterItemStacks[2].getItem() != this.cutterItemStacks[4].getItem())
            {
                cutterItemStacks[4].stackSize += itemstack.stackSize;
                tick = false;
            }

            if (this.cutterItemStacks[2].stackSize > itemstack.getMaxStackSize() && this.cutterItemStacks[4] == null || this.cutterItemStacks[2].stackSize > itemstack.getMaxStackSize() && this.cutterItemStacks[4].isItemEqual(cutterItemStacks[1]))
            {
                int sub = this.cutterItemStacks[2].stackSize - itemstack.getMaxStackSize();
                this.cutterItemStacks[2].stackSize = this.cutterItemStacks[2].stackSize - sub;

                if (this.cutterItemStacks[4] != null && this.cutterItemStacks[4].isItemEqual(cutterItemStacks[1]))
                {
                    this.cutterItemStacks[4].stackSize = sub;
                }

                if (this.cutterItemStacks[4] == null)
                {
                    this.cutterItemStacks[4] = new ItemStack(this.cutterItemStacks[2].getItem(), sub);
                }
            }

            if (this.cutterItemStacks[3] == null)
            {
                if (rarestack != null)
                {
                    if (rand < 0.1D)
                    {
                        this.cutterItemStacks[3] = rarestack.copy();
                    }
                }
            }
            else if (this.cutterItemStacks[3] != null)
            {
                if (this.cutterItemStacks[3].isItemEqual(rarestack))
                {
                    if (rarestack != null)
                    {
                        if (rand < 0.1D)
                        {
                            cutterItemStacks[3].stackSize += rarestack.stackSize;
                        }
                    }
                }
            }

            --this.cutterItemStacks[0].stackSize;

            if (this.cutterItemStacks[0].stackSize <= 0)
            {
                this.cutterItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied pickaxe will chop the block.
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];
            }

            if (item instanceof ItemPickaxe && ((ItemPickaxe) item).getToolMaterialName().equals("WOOD"))
            {
                return 100;
            }

            if (item instanceof ItemPickaxe && ((ItemPickaxe) item).getToolMaterialName().equals("STONE"))
            {
                return 90;
            }

            if (item instanceof ItemPickaxe && ((ItemPickaxe) item).getToolMaterialName().equals("IRON"))
            {
                return 75;
            }

            if (item instanceof ItemPickaxe && ((ItemPickaxe) item).getToolMaterialName().equals("GOLD"))
            {
                return 50;
            }

            if (item instanceof ItemPickaxe && ((ItemPickaxe) item).getToolMaterialName().equals("EMERALD"))
            {
                return 65;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolMaterialName().equals("INFINITY"))
            {
                return 30;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 1)
            {
                return 100;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 2)
            {
                return 90;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 3)
            {
                return 75;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 4)
            {
                return 50;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 5)
            {
                return 65;
            }

            if (item instanceof Noah.InfinityBound.itemdata.ItemPickaxe && ((Noah.InfinityBound.itemdata.ItemPickaxe) item).getToolTypeMaterial() == 6)
            {
                return 45;
            }

            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }
}
package Noah.InfinityBound.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import Noah.InfinityBound.recipe.FuserRecipes;
import Noah.InfinityBound.tileentity.TileEntityFuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFuser extends Container
{
    private TileEntityFuser fuser;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerFuser(InventoryPlayer par1InventoryPlayer, TileEntityFuser par2TileEntityFuser)
    {
        this.fuser = par2TileEntityFuser;
        this.addSlotToContainer(new Slot(par2TileEntityFuser, 0, 10, 16));
        this.addSlotToContainer(new Slot(par2TileEntityFuser, 1, 10, 52));
        this.addSlotToContainer(new Slot(par2TileEntityFuser, 2, 51, 34));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityFuser, 3, 124, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.fuser.fuserFuseTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.fuser.fuserBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.fuser.currentItemWorkTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.fuser.fuserFuseTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.fuser.fuserFuseTime);
            }

            if (this.lastBurnTime != this.fuser.fuserBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.fuser.fuserBurnTime);
            }

            if (this.lastItemBurnTime != this.fuser.currentItemWorkTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.fuser.currentItemWorkTime);
            }
        }

        this.lastCookTime = this.fuser.fuserFuseTime;
        this.lastBurnTime = this.fuser.fuserBurnTime;
        this.lastItemBurnTime = this.fuser.currentItemWorkTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.fuser.fuserFuseTime = par2;
        }

        if (par1 == 1)
        {
            this.fuser.fuserBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.fuser.currentItemWorkTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.fuser.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (TileEntityFuser.isItemTool(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFuser.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 30, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 30, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}

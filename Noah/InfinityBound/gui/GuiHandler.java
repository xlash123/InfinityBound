package Noah.InfinityBound.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import Noah.InfinityBound.inventory.ContainerCutter;
import Noah.InfinityBound.inventory.ContainerFuser;
import Noah.InfinityBound.tileentity.*;

public class GuiHandler implements IGuiHandler
{
    //returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
                                      int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        TileEntity tileEntity2 = world.getBlockTileEntity(x, y, z);
        TileEntity tileEntity3 = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityCutter)
        {
            return new ContainerCutter(player.inventory, (TileEntityCutter) tileEntity);
        }

        if (tileEntity2 instanceof TileEntityNetherChest)
        {
            return new ContainerChest(player.inventory, (TileEntityNetherChest) tileEntity2);
        }

        if (tileEntity3 instanceof TileEntityFuser)
        {
            return new ContainerFuser(player.inventory, (TileEntityFuser) tileEntity3);
        }

        return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
                                      int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        TileEntity tileEntity2 = world.getBlockTileEntity(x, y, z);
        TileEntity tileEntity3 = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityCutter)
        {
            return new GuiCutter(player.inventory, (TileEntityCutter) tileEntity);
        }

        if (tileEntity2 instanceof TileEntityNetherChest)
        {
            return new GuiNetherChest(player.inventory, (TileEntityNetherChest) tileEntity2);
        }

        if (tileEntity3 instanceof TileEntityFuser)
        {
            return new GuiFuser(player.inventory, (TileEntityFuser) tileEntity3);
        }

        return null;
    }
}

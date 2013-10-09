package Noah.InfinityBound.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Noah.InfinityBound.events.SoundsEvent;
import Noah.InfinityBound.render.NetherChestRenderHelper;
import Noah.InfinityBound.render.TileEntityNetherChestRenderer;
import Noah.InfinityBound.tileentity.TileEntityNetherChest;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderInformation()
    {
        ChestItemRenderHelper.instance = new NetherChestRenderHelper();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetherChest.class, new TileEntityNetherChestRenderer());
    }
    @Override
    public void registerSounds()
    {
        MinecraftForge.EVENT_BUS.register(new SoundsEvent());
    }
}

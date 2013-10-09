package Noah.InfinityBound.render;

import java.util.Map;

import Noah.InfinityBound.blockdata.Blocks;
import Noah.InfinityBound.tileentity.TileEntityNetherChest;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntityChest;

public class NetherChestRenderHelper extends ChestItemRenderHelper
{
    private TileEntityNetherChest theNetherChest = new TileEntityNetherChest(0);

    @Override
    public void renderChest(Block block, int i, float f)
    {
        if (block == Blocks.chestNether)
        {
            TileEntityRenderer.instance.renderTileEntityAt(this.theNetherChest, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else
        {
            super.renderChest(block, i, f);
        }
    }
}

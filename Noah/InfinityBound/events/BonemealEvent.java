package Noah.InfinityBound.events;

import Noah.InfinityBound.blockdata.BlockCrops;
import Noah.InfinityBound.blockdata.BlockSapling;
import Noah.InfinityBound.blockdata.Blocks;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;

public class BonemealEvent
{
    @ForgeSubscribe
    public void onUseBonemeal(net.minecraftforge.event.entity.player.BonemealEvent event)
    {
        int l = event.world.getBlockId(event.X, event.Y, event.Z);

        if (l == Blocks.sapling.blockID)
        {
            if (!event.world.isRemote)
            {
                event.world.playAuxSFX(2005, event.X, event.Y, event.Z, 0);

                if ((double)event.world.rand.nextFloat() < 0.45D)
                {
                    ((BlockSapling)Blocks.sapling).markOrGrowMarked(event.world, event.X, event.Y, event.Z, event.world.rand);
                }

                event.setResult(Result.ALLOW);
            }
        }

        if (l == Blocks.appleCrop.blockID)
        {
            if (event.world.getBlockMetadata(event.X, event.Y, event.Z) == 7)
            {
            }
            else
            {
                if (!event.world.isRemote)
                {
                    ((BlockCrops)Block.blocksList[l]).fertilize(event.world, event.X, event.Y, event.Z);
                    event.setResult(Result.ALLOW);
                }
            }
        }
    }
}

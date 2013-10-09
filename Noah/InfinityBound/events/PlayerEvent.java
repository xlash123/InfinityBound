package Noah.InfinityBound.events;

import Noah.InfinityBound.itemdata.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;

public class PlayerEvent
{
    @ForgeSubscribe
    public void onInventoryChange(net.minecraftforge.event.entity.player.PlayerEvent event)
    {
        if (event.entityPlayer.inventory.armorInventory[2] != null)
        {
            if (event.entityPlayer.inventory.armorInventory[2].getItem() == Items.plateBedrock)
            {
                event.entityPlayer.fireResistance = 10000;
            }
        }
    }
}

package Noah.InfinityBound.events;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.Entity;

import java.util.Random;

import Noah.InfinityBound.itemdata.Items;

public class MobDrops
{
    public static double rand;
    @ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event)
    {
        rand = Math.random();

        if (event.entityLiving instanceof EntitySheep)
        {
            if (event.entityLiving.isBurning() == false)
            {
                if (rand < 0.95d)
                {
                    event.entityLiving.dropItem(Items.lambRaw.itemID, 1);
                }
            }
        }

        if (event.entityLiving instanceof EntitySheep)
        {
            if (event.source.getDamageType().equals("inFire"))
            {
                event.entityLiving.dropItem(Items.lambCooked.itemID, 1);
            }
        }

        if (event.entityLiving instanceof EntitySheep)
        {
            if (event.entityLiving.isBurning())
            {
                event.entityLiving.dropItem(Items.lambCooked.itemID, 1);
            }
        }

        if (event.entityLiving instanceof EntityCreeper)
        {
            if (event.source.getDamageType().equals("cactus"))
            {
                event.entityLiving.dropItem(Items.recordSideburns.itemID, 1);
            }
        }

        if (event.entityLiving instanceof EntityCreeper)
        {
            if (event.entityLiving.isBurning())
            {
                if (event.source.getDamageType().equals("arrow"))
                {
                    event.entityLiving.dropItem(Items.recordDramatic.itemID, 1);
                }
            }
        }
    }
}

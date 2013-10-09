package Noah.InfinityBound.events;

import Noah.InfinityBound.InfinityBound;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundsEvent
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        try
        {
            event.manager.soundPoolStreaming.addSound("infinitybound:Sideburns.ogg");
            event.manager.soundPoolStreaming.addSound("infinitybound:Dramatic Song.ogg");
        }
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
        }
    }
}

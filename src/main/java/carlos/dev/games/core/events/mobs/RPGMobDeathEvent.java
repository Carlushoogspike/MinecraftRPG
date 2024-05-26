package carlos.dev.games.core.events.mobs;

import carlos.dev.games.core.meta.mob.util.MobUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class RPGMobDeathEvent implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event){
        Entity entity = event.getEntity();

        if (!(entity instanceof Monster monster)) return;

        if (!MobUtils.isMarkedMob(monster)) return;
    }
}

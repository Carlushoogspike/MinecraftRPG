package carlos.dev.games.core.events.mobs;

import carlos.dev.games.core.meta.mob.util.MobUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class RPGSpawnEntityEvent implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();

        if (MobUtils.isMarkedMob(entity)){
        } else {
           // Component name = StringUtils.generateNameNatural(entity);
           // entity.setCustomNameVisible(true);
           // entity.customName(name);
        }
    }

}

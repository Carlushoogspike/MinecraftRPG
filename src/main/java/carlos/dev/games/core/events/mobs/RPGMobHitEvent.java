package carlos.dev.games.core.events.mobs;

import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.core.meta.mob.methods.MobMethods;
import carlos.dev.games.core.meta.mob.util.MobUtils;
import carlos.dev.games.core.utils.string.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class RPGMobHitEvent implements Listener {

    @EventHandler
    public void onKill(EntityDamageByEntityEvent event){
        LivingEntity entity = (LivingEntity) event.getEntity();

        if (MobUtils.isMarkedMob(entity)) {

            RPGMob rpgMob = MobMethods.getMob(MobUtils.getId(entity));

            Component component = StringUtils.generateNameBaseRPG(rpgMob, entity);
            entity.customName(component);
        } else {
            entity.setCustomNameVisible(true);
            entity.customName(Component.empty());
            Component name = StringUtils.generateNameNatural(entity);
            entity.customName(name);
        }


    }
}

package carlos.dev.games.core.meta.mechanic;

import carlos.dev.games.api.mechanic.MechanicProvider;
import carlos.dev.games.core.meta.mechanic.impl.ActionDoubleJump;
import carlos.dev.games.core.meta.mechanic.impl.ActionDashClicker;
import carlos.dev.games.core.meta.mechanic.impl.ActionPowerDown;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MechanicManager {

    private final Map<UUID, List<MechanicProvider>> map;

    public MechanicManager() {
        this.map = new ConcurrentHashMap<>();
    }

    public void registerMechanics(Plugin plugin){
        new ActionDoubleJump(plugin);
        new ActionDashClicker(plugin);
        new ActionPowerDown(plugin);
    }

    public boolean addMechanic(Player player, MechanicProvider mechanicProvider){
        UUID uuid = player.getUniqueId();

        List<MechanicProvider> list;
        if (map.containsKey(uuid)) {
            list = map.get(uuid);

            if (list.contains(mechanicProvider)){
                return false;
            }

            list.add(mechanicProvider);
            mechanicProvider.runAbility(player, this.map);
        }
        else {
            list = new ArrayList<>();
            list.add(mechanicProvider);
            map.put(uuid, list);
            mechanicProvider.runAbility(player, this.map);
        }
        return true;
    }


}

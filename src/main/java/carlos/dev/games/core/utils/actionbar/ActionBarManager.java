package carlos.dev.games.core.utils.actionbar;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionBarManager {

    private final Map<UUID, RPGActionBar> actionBarMap;

    public ActionBarManager(){
        this.actionBarMap = Collections.synchronizedMap(new HashMap<>());
    }

    public void sendTo(Player player){
        UUID uuid = player.getUniqueId();
        RPGActionBar actionBar = actionBarMap.computeIfAbsent(uuid, id -> new RPGActionBar(player));

        actionBar.run();
    }

    public void stop(Player player){
        UUID uuid = player.getUniqueId();
        if (!actionBarMap.containsKey(uuid)) return;

        RPGActionBar rpgActionBar = getData(player);
        System.out.println("stopping actionbar scheduler");
        rpgActionBar.stop();
        actionBarMap.remove(uuid);
    }

    @NotNull
    public RPGActionBar getData(Player player){
        return actionBarMap.get(player.getUniqueId());
    }

}

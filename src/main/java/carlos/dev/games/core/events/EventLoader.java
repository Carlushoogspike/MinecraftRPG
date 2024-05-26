package carlos.dev.games.core.events;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.events.items.RPGItemPickupEvent;
import carlos.dev.games.core.events.items.RPGItemSpawnAndEnableEvent;
import carlos.dev.games.core.events.items.RPGPlayerDropItemEvent;
import carlos.dev.games.core.events.items.RPGPlayerHoldItemEvent;
import carlos.dev.games.core.events.misc.RPGTasksUpdatesEvent;
import carlos.dev.games.core.events.mobs.RPGMobDeathEvent;
import carlos.dev.games.core.events.mobs.RPGMobHitEvent;
import carlos.dev.games.core.events.mobs.RPGSpawnEntityEvent;
import carlos.dev.games.core.events.player.RPGPlayerDamageEvent;
import carlos.dev.games.core.events.player.RPGPlayerJoinEvent;
import carlos.dev.games.core.events.player.RPGPlayerMovementEvent;
import carlos.dev.games.core.events.player.RPGPlayerQuitEvent;
import carlos.dev.games.core.player.base.PlayerTaskManager;
import carlos.dev.games.core.player.skill.event.PlayerSkillConnectorEvent;
import carlos.dev.games.core.player.skill.listener.SkillGainXpListener;
import carlos.dev.games.core.player.skill.listener.SkillUpdateLevelListener;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class EventLoader {

    private final MinecraftRPG game;
    private final List<Listener> listeners;

    public EventLoader(MinecraftRPG game){
        this.game = game;
        this.listeners = new ArrayList<>();

        load();
    }

    private void load(){
        ActionBarManager actionBarManager = game.getActionBarManager();
        PlayerTaskManager taskManager = game.getPlayerTaskManager();

        listeners.add(new RPGItemPickupEvent());
        listeners.add(new RPGItemSpawnAndEnableEvent(game));
        listeners.add(new RPGPlayerDropItemEvent());
        listeners.add(new RPGPlayerHoldItemEvent());

        listeners.add(new RPGPlayerDamageEvent(actionBarManager));
        listeners.add(new RPGPlayerJoinEvent(actionBarManager, taskManager));
        listeners.add(new RPGPlayerQuitEvent(actionBarManager, taskManager));
        listeners.add(new RPGPlayerMovementEvent());

        listeners.add(new RPGTasksUpdatesEvent(actionBarManager));

        listeners.add(new RPGMobDeathEvent());
        listeners.add(new RPGMobHitEvent());
        listeners.add(new RPGSpawnEntityEvent());

        listeners.add(new PlayerSkillConnectorEvent());
        listeners.add(new SkillUpdateLevelListener());
        listeners.add(new SkillGainXpListener());


        listeners.forEach(l -> {
            game.getServer().getPluginManager().registerEvents(l, game);
            game.getLogger().info("Loaded event: " + l.getClass().getSimpleName());
        });
    }
}

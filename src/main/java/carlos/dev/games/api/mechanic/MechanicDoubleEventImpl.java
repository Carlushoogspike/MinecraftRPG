package carlos.dev.games.api.mechanic;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class MechanicDoubleEventImpl<T extends Event, B extends Event> implements Listener {
    private final Plugin plugin;

    public MechanicDoubleEventImpl(Plugin plugin) {
        this.plugin = plugin;
        registerEvent();
    }

    public void registerEvent() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}

package carlos.dev.games.core.utils.actionbar;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionBarUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public ActionBarUpdateEvent() {
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

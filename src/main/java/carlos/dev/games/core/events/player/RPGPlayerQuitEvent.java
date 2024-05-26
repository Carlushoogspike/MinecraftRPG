package carlos.dev.games.core.events.player;

import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.base.PlayerTaskManager;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class RPGPlayerQuitEvent implements Listener {

    private final ActionBarManager actionBarManager;
    private final PlayerTaskManager playerTaskManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

        actionBarManager.stop(player);
        playerTaskManager.stopPlayerTask(player);
    }

}

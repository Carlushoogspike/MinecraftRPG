package carlos.dev.games.core.player.skill.event;

import carlos.dev.games.core.player.skill.PlayerSkillUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerSkillConnectorEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerSkillUtility.putPlayer(player);
    }

}

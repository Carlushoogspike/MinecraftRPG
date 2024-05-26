package carlos.dev.games.core.events.player;

import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class RPGPlayerMovementEvent implements Listener {
    private long lastUpdateTime = System.currentTimeMillis();

    @EventHandler
    public void onMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

        update(player, rpgPlayer);
    }

    private void update(Player player, RPGPlayer rpgPlayer){


        if (System.currentTimeMillis() - this.lastUpdateTime >= 50L) {
            this.lastUpdateTime = System.currentTimeMillis();
            if (player.isSprinting()){

                float tireRate = 0.2F;

                float stamina = bound((float) rpgPlayer.getStamina() - tireRate, (int) rpgPlayer.getMaxStamina());
                rpgPlayer.setStamina(stamina);
            }
        }

        if (rpgPlayer.getStamina() <= 0.0){
            player.setSprinting(false);
        }

    }



    private float bound(float i, int maxStamina) {
        return Math.max(0.0F, Math.min(maxStamina, i));
    }
}

package carlos.dev.games.core.events.misc;

import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.actionbar.ActionBarUpdateEvent;
import carlos.dev.games.core.utils.actionbar.RPGActionBar;
import carlos.dev.games.core.utils.string.StringEmojis;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class RPGTasksUpdatesEvent implements Listener {

    private final ActionBarManager actionBarManager;

     @EventHandler
    public void onActionBarUpdate(ActionBarUpdateEvent event){
         for (Player player : Bukkit.getOnlinePlayers()){
             RPGActionBar actionBar = actionBarManager.getData(player);

             RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

             Component health = Component.text(
                     StringEmojis.HEALTH.getPrefix() + " " + (int) rpgPlayer.getHealth() + "/" + (int) rpgPlayer.getMaxHealth()
             ).color(TextColor.color(215, 69, 64));

             Component stamina = Component.text(
                     StringEmojis.STAMINA.getPrefix() + " " + (int) rpgPlayer.getStamina() + "/" + (int) rpgPlayer.getMaxStamina()
             ).color(TextColor.color(101, 168, 125));

             Component mana = Component.text(
                     StringEmojis.MANA.getPrefix() + " " + rpgPlayer.getMana() + "/" + rpgPlayer.getMaxMana()
             ).color(TextColor.color(67, 155, 196));

             actionBar.switchAll(health, stamina, mana);
         }
     }
}

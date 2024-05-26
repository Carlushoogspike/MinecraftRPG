package carlos.dev.games.core.events.items;

import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class RPGPlayerDropItemEvent implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

        ItemStack droppedItem = event.getItemDrop().getItemStack();

        if (ItemRPGUtils.isRPGItem(droppedItem)) {
            ItemCustomizable rpgItem = ItemRPGUtils.getRPGItem(droppedItem);
            if (rpgItem.containsAttributes()) {
                int loseHealth = rpgItem.getGainHealth();
                int loseMana = rpgItem.getGainMana();
                int loseStamina = rpgItem.getGainStamina();

                rpgPlayer.reduceMaxHealth(loseHealth);
                rpgPlayer.reduceMaxMana(loseMana);
                rpgPlayer.reduceMaxStamina(loseStamina);
                player.sendMessage("u is dropped, then subtracting attributes...");
            }
        }
    }

}

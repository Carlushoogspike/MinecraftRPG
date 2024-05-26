package carlos.dev.games.core.events.items;

import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class RPGPlayerHoldItemEvent implements Listener {

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

        int newSlot = event.getNewSlot();
        int oldSlot = event.getPreviousSlot();

        ItemStack oldItem = player.getInventory().getItem(oldSlot);
        ItemStack currentItem = player.getInventory().getItem(newSlot);

        if (oldItem != null && ItemRPGUtils.isRPGItem(oldItem)) {
            ItemCustomizable oldRPGItem = ItemRPGUtils.getRPGItem(oldItem);
            if (oldRPGItem.containsAttributes()) {
                int loseHealth = oldRPGItem.getGainHealth();
                int loseMana = oldRPGItem.getGainMana();
                int loseStamina = oldRPGItem.getGainStamina();

                rpgPlayer.reduceMaxHealth(loseHealth);
                rpgPlayer.reduceMaxMana(loseMana);
                rpgPlayer.reduceMaxStamina(loseStamina);
            }
        }

        if (currentItem != null && ItemRPGUtils.isRPGItem(currentItem)) {
            ItemCustomizable newRPGItem = ItemRPGUtils.getRPGItem(currentItem);
            if (newRPGItem.containsAttributes()) {
                int gainHealth = newRPGItem.getGainHealth();
                int gainMana = newRPGItem.getGainMana();
                int gainStamina = newRPGItem.getGainStamina();

                rpgPlayer.addMaxHealth(gainHealth);
                rpgPlayer.addMaxMana(gainMana);
                rpgPlayer.addMaxStamina(gainStamina);

                player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_STEP, 2f ,2f);
            }
        }
    }

}

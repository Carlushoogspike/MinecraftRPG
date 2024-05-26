package carlos.dev.games.core.events.items;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.items.lore.ItemLore;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.items.utils.ItemTierUtils;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class RPGItemPickupEvent implements Listener {

    private final HashMap<Player, ItemCustomizable> playerRPGItemHashMap = new HashMap<>();

    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if (!(event.getEntity() instanceof Player player)) return;
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        ItemStack stack = event.getItem().getItemStack();

        if (ItemTierUtils.isTired(stack)) {
            ItemRanked tier = ItemTierUtils.getTierByStack(stack);
            ItemLore lore = new ItemLore(stack);

            if (ItemRPGUtils.isRPGItem(stack)){
                ItemCustomizable rpgItem = ItemRPGUtils.getRPGItem(stack);

                if (playerRPGItemHashMap.containsKey(player) &&
                        playerRPGItemHashMap.get(player).equals(rpgItem)){
                    event.setCancelled(true);
                    return;
                }

                lore.addLore(tier);

                int level = rpgItem.getRequiredLevel();
                int playerLevel = rpgPlayer.getLevel();

                if (level > playerLevel){
                    event.setCancelled(true);
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO , 1f , 1f);
                    player.sendMessage("§cVocê precisa estar no Lvl. " + level + " para pegar o item §n" + rpgItem.getName());

                    playerRPGItemHashMap.put(player, rpgItem);

                    Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
                        playerRPGItemHashMap.remove(player);
                    }, 10 * 20L);
                }


                if (rpgItem.containsAttributes()) {
                    int gainHealth = rpgItem.getGainHealth();
                    int gainMana = rpgItem.getGainMana();
                    int gainStamina = rpgItem.getGainStamina();

                    rpgPlayer.addMaxHealth(gainHealth);
                    rpgPlayer.addMaxMana(gainMana);
                    rpgPlayer.addMaxStamina(gainStamina);

                    player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_STEP, 2f ,2f);
                }

            } else {
                lore.addLoreNotRpgItem(tier);
            }
        }
    }
}

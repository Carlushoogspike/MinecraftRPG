package carlos.dev.games.core.events.player;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.base.PlayerTaskManager;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class RPGPlayerJoinEvent implements Listener {

    private final ActionBarManager actionBarManager;
    private final PlayerTaskManager playerTaskManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RPGPlayerUtility.putPlayer(player);

        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        rpgPlayer.setMaxHealth(100);
        rpgPlayer.setStrength(2020);
        rpgPlayer.setStamina(20);
        rpgPlayer.setLevel(10);

        actionBarManager.sendTo(player);
        playerTaskManager.putPlayerTask(player);


        RPGMob rpgMob = MinecraftRPG.getInstance().getMobManager().get("master_zombie");
        if (rpgMob == null){
            player.sendMessage("§cNão foi possivel spawnar pois o mob é null...");
        } else {
            //MobMethods.spawnMob(player.getLocation(), rpgMob, 0);
        }

        ItemStack itemOnHand = player.getInventory().getItem(EquipmentSlot.HAND);
        if (ItemRPGUtils.isRPGItem(itemOnHand)){
            ItemCustomizable rpgItem = ItemRPGUtils.getRPGItem(itemOnHand);
            if (rpgItem.containsAttributes()) {
                int gainHealth = rpgItem.getGainHealth();
                int gainMana = rpgItem.getGainMana();
                int gainStamina = rpgItem.getGainStamina();

                rpgPlayer.addMaxHealth(gainHealth);
                rpgPlayer.addMaxMana(gainMana);
                rpgPlayer.addMaxStamina(gainStamina);

                player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_STEP, 2f ,2f);
            }
        }
    }

}

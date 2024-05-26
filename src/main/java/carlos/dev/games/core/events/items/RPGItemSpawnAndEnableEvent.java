package carlos.dev.games.core.events.items;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.items.lore.ItemLore;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.items.utils.ItemTierUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class RPGItemSpawnAndEnableEvent implements Listener {

    private final MinecraftRPG game;

    @EventHandler
    public void onSpawn(ItemSpawnEvent event){
        ItemStack stack = event.getEntity().getItemStack();
        ItemTierUtils.loadItem(stack);
    }

    @EventHandler
    public void pluginEnable(PluginEnableEvent event){
        if(event.getPlugin() == game){
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.getInventory().forEach(itemStack -> {
                            if (itemStack != null && !itemStack.getType().isAir()) {
                                ItemTierUtils.loadItem(itemStack);
                                loadItem(itemStack);
                            }
                        });
                    });
                }
            }.runTaskTimerAsynchronously(game, 0L , 1L);
        }

    }

    private void loadItem(ItemStack stack){
        if (ItemRPGUtils.notLoad(stack))return;

        if (ItemTierUtils.isTired(stack)) {
            ItemRanked tier = ItemTierUtils.getTierByStack(stack);
            ItemLore lore = new ItemLore(stack);
            if (ItemRPGUtils.isRPGItem(stack)) {
                lore.addLore(tier);
            } else {
                lore.addLoreNotRpgItem(tier);
            }
        }
    }


}

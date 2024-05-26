package carlos.dev.games.core.player.inventory;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.core.items.utils.ItemParserUtils;
import carlos.dev.games.api.inventory.RPGSlotLeveler;
import carlos.dev.games.core.player.inventory.slots.LockedSlot;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class RPGInventoryEvents implements Listener {

    private RPGInventory rpgInventory;
    private PlayerInventory playerInventory;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        rpgInventory = new RPGInventory(player);

        playerInventory = player.getInventory();

        setDefaultItems();

        ItemCustomizable ITEM = MinecraftRPG.getInstance().getLoaderManager().getItem(ItemType.WEAPON, "FULL_SWORD");
        ItemStack i = ItemParserUtils.readRPGItem(ITEM);

        playerInventory.addItem(i);

    }

    public void onQuit() {
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        Inventory eventInventory = event.getClickedInventory();
        if (eventInventory != playerInventory) return;

        ItemStack handItem = event.getCursor();
        ItemStack clickedItem = event.getCurrentItem();
        int clickedSlot = event.getSlot();

        assert clickedItem != null;

        for (RPGSlotLeveler leveler : rpgInventory.getLevelers()) {
            int levelerSlot = leveler.getSlot();

            if (levelerSlot == clickedSlot && clickedItem.getType() == leveler.getReferenceItem().getType()) {
                event.setCancelled(true);
                break;
            }
        }

        for (Integer lockedSlots : rpgInventory.getLockedSlot().getSlots()) {
            if (lockedSlots == clickedSlot && clickedItem.getType() == rpgInventory.getLockedSlot().getReferenceItem().getType()) {
                event.setCancelled(true);
                break;
            }
        }

    }

    private void setDefaultItems() {
        LockedSlot lockedSlot = rpgInventory.getLockedSlot();
        List<RPGSlotLeveler> levelers = rpgInventory.getLevelers();

        for (Integer slot : lockedSlot.getSlots()) {
            ItemStack searchedItem = playerInventory.getItem(slot);

            if (searchedItem == null || searchedItem.getType() == Material.AIR) {
                playerInventory.setItem(slot, lockedSlot.getReferenceItem());
            }
        }

        for (RPGSlotLeveler leveler : levelers) {
            if (leveler.isEmpty()) {
                playerInventory.setItem(leveler.getSlot(), leveler.getReferenceItem());
            }
        }

    }

}

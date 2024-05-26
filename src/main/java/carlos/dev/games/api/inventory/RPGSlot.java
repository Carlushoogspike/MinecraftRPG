package carlos.dev.games.api.inventory;

import org.bukkit.inventory.ItemStack;

public interface RPGSlot {

    int getSlot();

    boolean isEmpty();

    void clearItemOnSlot();

    ItemStack getItemOnSlot();

    void setItemOnSlot(ItemStack itemStack);

}

package carlos.dev.games.core.player.inventory.slots;

import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.api.inventory.RPGSlotSwitchable;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LockedSlot implements RPGSlotSwitchable {

    private final PlayerInventory inventory;
    private final Map<Integer, Boolean> lockedSlots;

    public LockedSlot(@NotNull Player player) {
        this.inventory = player.getInventory();
        this.lockedSlots = new HashMap<>();
        for (int slot : getSlots()) {
            lockedSlots.put(slot, true);
        }
    }

    public List<Integer> getSlots() {
        return List.of(13, 14, 15, 16, 17, 22, 23, 24, 25, 26, 31, 32, 33, 34, 35);
    }

    @Override
    @Deprecated
    public int getSlot() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        for (int slot : getSlots()) {
            if (inventory.getItem(slot) != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void clearItemOnSlot() {
        for (int slot : getSlots()) {
            inventory.setItem(slot, null);
        }
    }

    @Deprecated
    @Override
    public ItemStack getItemOnSlot() {
        return null;
    }

    @Override
    @Deprecated
    public void setItemOnSlot(ItemStack itemStack) {

    }

    public void clearItemOnSlot(int slot) {
        if (!getSlots().contains(slot)) return;
        inventory.setItem(slot, null);
    }

    public Optional<ItemStack> getItemOnSlot(int slot) {
        if (!getSlots().contains(slot)) return Optional.empty();
        return Optional.ofNullable(inventory.getItem(slot));
    }

    public void setItemOnSlot(int slot, ItemStack itemStack) {
        if (!getSlots().contains(slot)) return;
        inventory.setItem(slot, itemStack);
    }

    @Override
    public boolean hasLocked(int slot) {
        return lockedSlots.get(slot);
    }

    public boolean hasLockedByItem(int slot) {
        Optional<ItemStack> item = getItemOnSlot(slot);
        return item.filter(itemStack -> itemStack.getType() == getReferenceItem().getType()).isPresent();
    }

    @Override
    public void unlockSlot(int slot) {
        lockedSlots.put(slot, true);
    }

    @Override
    public void lockSlot(int slot) {
        lockedSlots.put(slot, false);
    }

    public ItemStack getReferenceItem() {
        ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();

        meta.displayName(Component.text("§8Espaço Bloqueado"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        stack.setItemMeta(meta);

        ItemRPGUtils.putNotLoad(stack);
        return stack;
    }
}

package carlos.dev.games.core.player.inventory.slots;

import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.player.inventory.RPGSlotIndex;
import carlos.dev.games.api.inventory.RPGSlotLeveler;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class NecklaceSlot implements RPGSlotLeveler {

    private final PlayerInventory inventory;
    private double level;
    private double xp;

    public NecklaceSlot(@NotNull Player player) {
        this.inventory = player.getInventory();
        this.level = 0;
        this.xp = 0;
    }

    @Override
    public int getSlot() {
        return RPGSlotIndex.NECKLACE.getSlot();
    }

    @Override
    public boolean isEmpty() {
        return inventory.getItem(getSlot()) == null;
    }

    @Override
    public void clearItemOnSlot() {
        inventory.setItem(getSlot(), null);
    }

    @Override
    public ItemStack getItemOnSlot() {
        return inventory.getItem(getSlot());
    }

    @Override
    public void setItemOnSlot(ItemStack itemStack) {
        inventory.setItem(getSlot(), itemStack);
    }

    @Override
    public double getSlotXp() {
        return 0;
    }

    @Override
    public double getSlotLevel() {
        return 0;
    }

    @Override
    public void setSlotXp(double xp) {
        this.xp = xp;
    }

    @Override
    public void setSlotLevel(double level) {
        this.level = level;
    }

    @Override
    public ItemStack getReferenceItem() {
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();

        meta.displayName(Component.text("§7Espaço para Cordão"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        stack.setItemMeta(meta);

        ItemRPGUtils.putNotLoad(stack);
        return stack;
    }
}

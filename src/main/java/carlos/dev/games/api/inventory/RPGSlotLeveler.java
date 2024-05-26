package carlos.dev.games.api.inventory;

import org.bukkit.inventory.ItemStack;

public interface RPGSlotLeveler extends RPGSlot {

    double getSlotXp();

    double getSlotLevel();

    default void addXp(double xp) {
        double currentXp = getSlotXp();
        setSlotXp(currentXp + xp);
        updateLevel();
    }

    default void updateLevel() {
        double xp = getSlotXp();
        double level = xp / 100;
        setSlotLevel(level);
    }


    void setSlotXp(double xp);

    void setSlotLevel(double level);

    ItemStack getReferenceItem();

}

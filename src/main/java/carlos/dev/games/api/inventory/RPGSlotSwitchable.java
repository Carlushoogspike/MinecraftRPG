package carlos.dev.games.api.inventory;

public interface RPGSlotSwitchable extends RPGSlot {

    boolean hasLocked(int slot);

    void unlockSlot(int slot);

    void lockSlot(int slot);

}

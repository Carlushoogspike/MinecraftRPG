package carlos.dev.games.core.player.inventory;

public enum RPGSlotIndex {

    RING(9),
    AMULET(10),
    GLOVES(11),
    NECKLACE(12);

    private final int id;

    private RPGSlotIndex(int id) {
        this.id = id;
    }

    public int getSlot() {
        return id;
    }

}

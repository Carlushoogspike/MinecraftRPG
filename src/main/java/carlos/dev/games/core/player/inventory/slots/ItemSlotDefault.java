package carlos.dev.games.core.player.inventory.slots;

import lombok.Getter;

@Getter
public enum ItemSlotDefault {

    HAND("hand"),
    OFF_HAND("off-hand"),
    HELMET("helmet"),
    CHESTPLATE("chestplate"),
    LEGGINGS("leggings"),
    BOOTS("boots");

    private final String id;

    ItemSlotDefault(String id) {
        this.id = id;
    }

    public ItemSlotDefault getById(String identifier) {
        for (ItemSlotDefault v : values()) {
            String id = v.getId();
            if (id.equals(identifier)) return v;
        }

        return null;
    }
}

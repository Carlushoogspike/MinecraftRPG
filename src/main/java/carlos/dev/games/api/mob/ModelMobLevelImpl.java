package carlos.dev.games.api.mob;

import carlos.dev.games.core.player.inventory.slots.ItemSlotDefault;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ModelMobLevelImpl {

    private int level;
    private double maxHealth, spawnChance;
    private int strength, speed, defense;
    private boolean spawnedDefault;
    private Map<ItemSlotDefault, ModelMobItemImpl> items;
}

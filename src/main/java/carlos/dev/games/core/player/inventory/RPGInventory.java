package carlos.dev.games.core.player.inventory;

import carlos.dev.games.api.inventory.RPGSlotLeveler;
import carlos.dev.games.core.player.inventory.slots.*;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RPGInventory {

    private final Player player;

    private final AmuletSlot amuletSlot;
    private final GlovesSlot glovesSlot;
    private final NecklaceSlot necklaceSlot;
    private final RingSlot ringSlot;

    private final LockedSlot lockedSlot;

    private final List<RPGSlotLeveler> levelers;

    public RPGInventory(Player player) {
        this.player = player;

        this.amuletSlot = new AmuletSlot(player);
        this.glovesSlot = new GlovesSlot(player);
        this.necklaceSlot = new NecklaceSlot(player);
        this.ringSlot = new RingSlot(player);

        this.lockedSlot = new LockedSlot(player);

        this.levelers = new ArrayList<>();

        levelers.add(amuletSlot);
        levelers.add(glovesSlot);
        levelers.add(necklaceSlot);
        levelers.add(ringSlot);

    }

}

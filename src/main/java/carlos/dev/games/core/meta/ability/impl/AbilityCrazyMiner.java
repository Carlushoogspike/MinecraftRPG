package carlos.dev.games.core.meta.ability.impl;

import carlos.dev.games.api.mechanic.MechanicEventImpl;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class AbilityCrazyMiner extends MechanicEventImpl<BlockBreakEvent> {

    public AbilityCrazyMiner(Plugin plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void breakBlock(BlockBreakEvent event){
        Block block = event.getBlock();
        Player player = event.getPlayer();


    }
}

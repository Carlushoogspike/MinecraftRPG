package carlos.dev.games.core.meta.mechanic.impl;

import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.api.mechanic.MechanicConsumable;
import carlos.dev.games.api.mechanic.MechanicEventImpl;
import carlos.dev.games.api.mechanic.MechanicProvider;
import carlos.dev.games.core.meta.particle.impl.SphereParticleImpl;
import carlos.dev.games.core.utils.effects.EffectUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActionPowerDown extends MechanicEventImpl<PlayerDropItemEvent> implements MechanicProvider {
    public ActionPowerDown(Plugin plugin) {
        super(plugin);
    }

    @Override
    public ItemType getType() {
        return ItemType.RING;
    }

    @Override
    public MechanicConsumable getConsumable() {
        return new MechanicConsumable(
                "power_down",
                "Avanço Cabuloso",
                10,
                0,
                10,
                0
        );
    }

    @Override
    public void runAbility(Player player, Map<UUID, List<MechanicProvider>> map) {

    }

    @EventHandler
    public void onMovement(PlayerDropItemEvent event) {
        new SphereParticleImpl(event.getPlayer(), Particle.FALLING_OBSIDIAN_TEAR).start();
}

    public void pullPlayer(Player player, Location from, Location to, double distance) {
        Vector pullVector = EffectUtils.getPullVector(from, to, distance);
        player.setVelocity(pullVector);
    }
}

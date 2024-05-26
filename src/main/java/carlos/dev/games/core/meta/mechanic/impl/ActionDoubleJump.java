package carlos.dev.games.core.meta.mechanic.impl;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.api.mechanic.MechanicConsumable;
import carlos.dev.games.api.mechanic.MechanicEventImpl;
import carlos.dev.games.api.mechanic.MechanicProvider;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.effects.EffectUtils;
import carlos.dev.games.core.utils.effects.ParticleUtils;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActionDoubleJump extends MechanicEventImpl<PlayerJumpEvent> implements MechanicProvider  {

    public ActionDoubleJump(Plugin plugin) {
        super(plugin);
    }

    @Override
    public ItemType getType() {
        return ItemType.STUFF;
    }

    @Override
    public MechanicConsumable getConsumable() {
        return new MechanicConsumable(
                "double_jump",
                "Salto Duplo",
                10,
                0,
                0,
                0);
    }

    @Override
    public void runAbility(Player player, Map<UUID, List<MechanicProvider>> map) {
        Block bloc = player.getWorld().getBlockAt(player.getLocation().subtract(0, 2,0));
        effect(player);

        ActionBarManager barManager = MinecraftRPG.getInstance().getActionBarManager();

        if (bloc.getType() != Material.AIR){
            startMechanicAction(player, map, barManager);

            Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
                Vector vector = player.getLocation().getDirection().multiply(0.5).setY(1);
                player.setVelocity(vector);
                effect(player);

            }, 10L);
        }

    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if (nonHaveRequires(player)){
            defaultError(player);
            return;
        }
        startCooldown(player);
    }

    private void effect(Player player){
        ParticleUtils.FillHelixInBellow(Particle.CLOUD, player.getLocation().add(0, 0.5,0 ), 2, 9 * 15, 1);
        EffectUtils.sound(player, Sound.ENTITY_FIREWORK_ROCKET_BLAST);
    }


}


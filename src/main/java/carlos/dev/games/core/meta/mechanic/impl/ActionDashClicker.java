package carlos.dev.games.core.meta.mechanic.impl;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.api.mechanic.MechanicConsumable;
import carlos.dev.games.api.mechanic.MechanicEventImpl;
import carlos.dev.games.api.mechanic.MechanicProvider;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.effects.EffectUtils;
import carlos.dev.games.core.utils.effects.ParticleUtils;
import carlos.dev.games.core.utils.string.StringPosition;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActionDashClicker extends MechanicEventImpl<PlayerInteractEvent> implements MechanicProvider {

    public ActionDashClicker(Plugin plugin) {
        super(plugin);
    }

    @Override
    public ItemType getType() {
        return ItemType.STUFF;
    }

    @Override
    public MechanicConsumable getConsumable() {
        return new MechanicConsumable(
                "sinseeker",
                "Buscador de Pecados",
                0,
                1,
                0,
                0);
    }

    private final ActionBarManager barManager = MinecraftRPG.getInstance().getActionBarManager();


    @Override
    public void runAbility(Player player, Map<UUID, List<MechanicProvider>> map) {
        startMechanicAction(player, map, barManager);
    }

    private Location startLocation;
    private final List<LivingEntity> entities = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack handedItem = player.getInventory().getItemInMainHand();

        if (handedItem.getType() != Material.STICK) return;

        if (nonHaveRequires(player)){
            defaultError(player);
            if (startLocation != null) {
                player.teleportAsync(startLocation);

                Bukkit.getScheduler().runTaskLater(MinecraftRPG.getInstance(), () -> {

                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                    //ParticleUtils.InfiniteLineColor(Particle.REDSTONE, dustOptions, 100, locations);
                    ParticleUtils.InfiniteLine(Particle.COMPOSTER, 130, locations);
                    ParticleUtils.InfiniteLine(Particle.FALLING_OBSIDIAN_TEAR, 100, locations);
                    ParticleUtils.InfiniteLine(Particle.CRIMSON_SPORE, 100, locations);


                    EffectUtils.sound(player, Sound.BLOCK_WOOL_BREAK);
                    entities.forEach(l -> l.setHealth(0));
                    entities.clear();
                    locations.clear();
                    startLocation = null;
                }, 20L);
            }
            return;
        }

        Vector direction = player.getEyeLocation().getDirection();
        RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(player.getEyeLocation(), direction, 50, 0.5,
                entity -> !entity.equals(player));

        if (rayTraceResult == null) return;

        LivingEntity target = (LivingEntity) rayTraceResult.getHitEntity();

        if (target != null) {
            if (player.isSneaking()) {

                if (startLocation == null) {
                    startLocation = player.getLocation();
                }

                if (entities.contains(target)) {
                    EffectUtils.sound(player, Sound.BLOCK_NOTE_BLOCK_BASS);
                    return;
                }
                if (!locations.contains(startLocation)) {
                    locations.add(startLocation);
                }

                entities.add(target);
                locations.add(target.getLocation().add(0, 1,0));
                subtractAttributes(player);

                player.teleportAsync(target.getLocation());
                EffectUtils.sound(player, Sound.BLOCK_NOTE_BLOCK_PLING);
            }
        }
    }

    @EventHandler
    public void onToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking() && startLocation != null) {
            player.teleportAsync(startLocation);

            Bukkit.getScheduler().runTaskLater(MinecraftRPG.getInstance(), () -> {

                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                //ParticleUtils.InfiniteLineColor(Particle.REDSTONE, dustOptions, 130, locations);
                ParticleUtils.InfiniteLine(Particle.COMPOSTER, 130, locations);
                ParticleUtils.InfiniteLine(Particle.FALLING_OBSIDIAN_TEAR, 100, locations);
                ParticleUtils.InfiniteLine(Particle.CRIMSON_SPORE, 100, locations);

                Component msg = Component.text(getStreak(locations.size()));
                barManager.getData(player).switchWithTime(StringPosition.CENTER, msg, 6);

                EffectUtils.sound(player, Sound.BLOCK_WOOL_BREAK);
                entities.forEach(l -> l.setHealth(0));
                entities.clear();
                locations.clear();
                startLocation = null;
                }, 20L);
        }
    }

    private String getStreak(int length){
        if (length <= 2){
            return "§aSequencia " + length + "x";
        } else if (length <= 4) {
            return "§eSequencia " + length + "x";
        } else if (length <= 6) {
            return "§6Sequencia " + length + "x";
        } else {
            return "§dSequencia " + length + "x";
        }
    }
}

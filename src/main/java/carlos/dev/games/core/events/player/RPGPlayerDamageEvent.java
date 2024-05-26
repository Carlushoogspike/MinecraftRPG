package carlos.dev.games.core.events.player;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.actionbar.RPGActionBar;
import carlos.dev.games.core.utils.string.StringPosition;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class RPGPlayerDamageEvent implements Listener {

    private final ActionBarManager actionBarManager;

    @EventHandler
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player && event.getEntity() instanceof Mob) {

            RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);

            Entity entity = event.getEntity();

            double baseDamage = event.getDamage();
            double strength = rpgPlayer.getStrength();
            double criticalDamage = rpgPlayer.getCriticalDamage();
            double criticalChance = rpgPlayer.getCriticalChance();
            //double health = rpgPlayer.getHealth();

            ItemStack itemInHand = player.getEquipment().getItem(EquipmentSlot.HAND);
            ItemCustomizable rpgItem = ItemRPGUtils.getRPGItem(itemInHand);

            boolean isCritical = Math.random()  < (criticalChance/ 100);

            if (rpgItem != null) {
                int useStamina = rpgItem.getUseStamina();

                if (rpgPlayer.getStamina() < useStamina){
                    event.setCancelled(true);
                    RPGActionBar actionBar = actionBarManager.getData(player);
                    Component msg = Component.text("Sem stamina").color(TextColor.color(166, 18, 71));
                    actionBar.switchWithTime(StringPosition.CENTER, msg, 3);
                    return;
                }

                double totalDamage = getTotalDamage(rpgItem, strength, baseDamage);

                rpgPlayer.subtractStamina(useStamina);

                event.setDamage(totalDamage);
                showDamageHologram(entity, totalDamage, false);
                return;
            }

            double damage = baseDamage;

            if (isCritical) {
                damage *= (1 + (criticalDamage / 100));
            }

            damage *= (1 + (strength / 100));

            showDamageHologram(entity, damage, isCritical);

            event.setDamage(damage);
            rpgPlayer.subtractStamina(1.7);
        }
}

    private static double getTotalDamage(@NotNull ItemCustomizable rpgItem, double strength, double baseDamage) {
        double rpgItemCriticalDamage = rpgItem.getCriticalDamage();

        double weaponDamageBonus = rpgItem.getAttackDamage();
        double strengthDamageBonus = 0;
        double strengthBonus = strength / 100.0;

        return (baseDamage + weaponDamageBonus + strengthDamageBonus) * (1 + rpgItemCriticalDamage / 100) * (1 + strengthBonus);
    }


    private void showDamageHologram(@NotNull Entity entity, double damage, boolean isCrit) {
        Location location = entity.getLocation().clone().add(0, 1, 0);
        ArmorStand hologram = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setVisible(false);
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setCanPickupItems(false);
        hologram.setMarker(true);
        hologram.setSmall(true);
        hologram.setCustomName(isCrit ? "§6" + String.format("%.1f", damage) + " ☠" : "§c" + String.format("%.1f", damage) + " ❤");

        new BukkitRunnable() {
            private double height = 1;

            @Override
            public void run() {
                if (height >= 2.8 ) {
                    hologram.remove();
                    cancel();
                    return;
                }

                Location loc = hologram.getLocation();
                loc.setY(location.getY() + height);
                hologram.teleport(loc);

                double speed = 0.1;
                height += speed;
            }
        }.runTaskTimer(MinecraftRPG.getInstance(), 0L, 5L);

        Bukkit.getScheduler().runTaskLater(MinecraftRPG.getInstance(), hologram::remove, 60L);
    }
}

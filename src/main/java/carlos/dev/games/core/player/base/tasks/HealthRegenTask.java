package carlos.dev.games.core.player.base.tasks;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.common.scheduler.RPGTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthRegenTask implements RPGTask {

    private final RPGPlayer rpgPlayer;
    private final Player player;
    private final BukkitRunnable task;
    private boolean isRunning;

    public HealthRegenTask(RPGPlayer rpgPlayer, Player player) {
        this.rpgPlayer = rpgPlayer;
        this.player = player;
        this.isRunning = false;
        this.task = createTaskUpdate();
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public void run() {
        if (isRunning()) return;
        this.isRunning = true;
        this.task.runTaskTimerAsynchronously(MinecraftRPG.getInstance(), 0, 20L);
    }

    @Override
    public void stop() {
        if (!isRunning()) return;
        this.isRunning = false;
        this.task.cancel();
    }

    @Override
    public BukkitRunnable createTaskUpdate() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (rpgPlayer.getHealth() < rpgPlayer.getMaxHealth()) {
                    increaseHealthGradually(rpgPlayer);
                }

                if (rpgPlayer.getHealth() > rpgPlayer.getMaxHealth()) {
                    rpgPlayer.setHealth(rpgPlayer.getMaxHealth());
                }

                if (player.getHealth() < player.getMaxHealth()) {
                    if (rpgPlayer.getHealth() > 24.0) return;
                    player.setHealth(rpgPlayer.getHealth());
                }
            }
        };
    }


    private void increaseHealthGradually(RPGPlayer rpgPlayer) {
        double currentHealth = rpgPlayer.getHealth();
        double maxHealth = rpgPlayer.getMaxHealth();

        double healthIncrement = 10;

        if (currentHealth <= maxHealth / 2) {
            healthIncrement *= 1.5;
        }
        else if (currentHealth <= maxHealth * 3 / 4) {
            healthIncrement *= 1.25;
        }

        if (currentHealth + healthIncrement > maxHealth) {
            rpgPlayer.setHealth(maxHealth);
        } else {
            rpgPlayer.addHealth(healthIncrement);
        }
    }


}
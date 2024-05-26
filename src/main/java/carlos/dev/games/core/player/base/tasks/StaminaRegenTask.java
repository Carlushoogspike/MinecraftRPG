package carlos.dev.games.core.player.base.tasks;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.common.scheduler.RPGTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StaminaRegenTask implements RPGTask {

    private final RPGPlayer rpgPlayer;
    private final Player player;
    private final BukkitRunnable task;
    private boolean isRunning;

    public StaminaRegenTask(RPGPlayer rpgPlayer, Player player) {
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
        this.task.runTaskTimerAsynchronously(MinecraftRPG.getInstance(), 0, 60L);
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
                if (rpgPlayer.getStamina() < rpgPlayer.getMaxStamina()) {
                    increaseGradually(rpgPlayer);
                }

                if (rpgPlayer.getStamina() > rpgPlayer.getMaxStamina()) {
                    rpgPlayer.setStamina(rpgPlayer.getMaxStamina());
                }
            }
        };
    }

    private void increaseGradually(RPGPlayer rpgPlayer) {
        double currentStamina = rpgPlayer.getStamina();
        double maxStamina = rpgPlayer.getMaxStamina();

        double staminaIncrement = 10;

        if (currentStamina <= maxStamina / 2) {
            staminaIncrement *= 1.5;
        }
        else if (currentStamina <= maxStamina * 3 / 4) {
            staminaIncrement *= 1.25;
        }

        if (currentStamina + staminaIncrement > maxStamina) {
            rpgPlayer.setStamina(maxStamina);
        } else {
            rpgPlayer.addStamina(staminaIncrement);
        }
    }
}

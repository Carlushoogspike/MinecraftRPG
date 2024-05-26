package carlos.dev.games.core.player.base.tasks;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.common.scheduler.RPGTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ArcaneRegenTask implements RPGTask {

    private final RPGPlayer rpgPlayer;
    private final Player player;
    private final BukkitRunnable task;
    private boolean isRunning;

    public ArcaneRegenTask(RPGPlayer rpgPlayer, Player player) {
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
                if (rpgPlayer.getArcane() < rpgPlayer.getMaxArcane()) {
                    increaseArcaneGradually(rpgPlayer);
                }
            }
        };
    }


    private void increaseArcaneGradually(RPGPlayer rpgPlayer) {
        double currentArcane = rpgPlayer.getArcane();
        double maxArcane = rpgPlayer.getMaxArcane();

        double arcaneDifference = maxArcane - currentArcane;

        double arcaneIncrement = arcaneDifference * 0.1;

        rpgPlayer.addHealth(arcaneIncrement);
    }

}
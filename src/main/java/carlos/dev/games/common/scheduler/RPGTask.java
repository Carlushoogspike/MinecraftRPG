package carlos.dev.games.common.scheduler;

import org.bukkit.scheduler.BukkitRunnable;

public interface RPGTask {

    boolean isRunning();

    void run();

    void stop();

    BukkitRunnable createTaskUpdate();

}

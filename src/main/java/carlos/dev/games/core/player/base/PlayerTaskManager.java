package carlos.dev.games.core.player.base;

import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.base.tasks.ArcaneRegenTask;
import carlos.dev.games.core.player.base.tasks.HealthRegenTask;
import carlos.dev.games.core.player.base.tasks.ManaRegenTask;
import carlos.dev.games.core.player.base.tasks.StaminaRegenTask;
import carlos.dev.games.common.scheduler.RPGTask;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayerTaskManager {

    private final Map<UUID, List<RPGTask>> tasks;

    public PlayerTaskManager(){
        this.tasks = Collections.synchronizedMap(new HashMap<>());
    }

    public void putPlayerTask(@NotNull Player player){
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        HealthRegenTask healthRegenTask = new HealthRegenTask(rpgPlayer, player);
        StaminaRegenTask staminaRegenTask = new StaminaRegenTask(rpgPlayer, player);
        ManaRegenTask manaRegenTask = new ManaRegenTask(rpgPlayer, player);
        ArcaneRegenTask arcaneRegenTask = new ArcaneRegenTask(rpgPlayer, player);

        List<RPGTask> task = List.of(healthRegenTask, staminaRegenTask, manaRegenTask, arcaneRegenTask);

        this.tasks.putIfAbsent(player.getUniqueId(), task);

        task.forEach(RPGTask::run);
    }

    public void stopPlayerTask(@NotNull Player player){
        this.tasks.get(player.getUniqueId()).forEach(scheduler -> {
            System.out.println("stopping " + scheduler);
            scheduler.stop();
        });
    }

    public HealthRegenTask getHealthTask(@NotNull Player player){
        return (HealthRegenTask) this.tasks.get(player.getUniqueId());
    }

    public StaminaRegenTask getStaminaTask(@NotNull Player player){
        return (StaminaRegenTask) this.tasks.get(player.getUniqueId());
    }

    public ManaRegenTask getManaTask(@NotNull Player player){
        return (ManaRegenTask) this.tasks.get(player.getUniqueId());
    }

    public ArcaneRegenTask getArcaneTask(@NotNull Player player){
        return (ArcaneRegenTask) this.tasks.get(player.getUniqueId());
    }

}

package carlos.dev.games.core.utils.actionbar;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.core.utils.string.StringPosition;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
public class RPGActionBar {

    private final Player player;
    private final BukkitRunnable task;
    @Getter
    private Component left, center, right;

    public RPGActionBar(Player player) {
        this.player = player;
        this.task = createTaskUpdate();

        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        this.left = Component.text((int) rpgPlayer.getHealth() + "/" + (int) rpgPlayer.getMaxHealth());
        this.center = Component.text("§7" + (int) rpgPlayer.getStamina() + "/" + (int) rpgPlayer.getMaxStamina());
        this.right =  Component.text(rpgPlayer.getMana() + "/" + rpgPlayer.getMaxMana());
    }

    public void stop(){
        this.task.cancel();
    }

    public void run(){
        this.task.runTaskTimerAsynchronously(MinecraftRPG.getInstance(), 0, 20);
    }

    public void switchCenter(Component text){
        this.center = text;
    }

    public void switchRight(Component text){
        this.right = text;
    }

    public void switchLeft(Component text){
        this.left = text;
    }

    public void switchAll(Component left, Component center, Component right){
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public void sendXpActionBar(PlayerSkill playerSkill, Skill skill, double xp){

    }

    public void switchWithTime(StringPosition position, Component msg, int seconds){
        switch (position){
            case CENTER:
                Component defaultCenter = this.center;
                this.center = msg;
                Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
                    this.center = defaultCenter;
                },  seconds * 20L);
                break;

            case LEFT:
                Component defaultLeft = this.left;
                this.left = msg;
                Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
                    this.left = defaultLeft;
                },  seconds * 20L);
                break;

            case RIGHT:
                Component defaultRight = this.right;
                this.right = msg;
                Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
                    this.right = defaultRight;
                },  seconds * 20L);
                break;
        }
    }


    private BukkitRunnable createTaskUpdate(){
        return new BukkitRunnable(){
            @Override
            public void run() {
                Component dv = Component.text("     ");
                Component msg = Component.text("").append(left).append(dv).append(center).append(dv).append(right);

                Bukkit.getScheduler().runTask(MinecraftRPG.getInstance(), () -> {
                    player.sendActionBar(msg);

                    Bukkit.getPluginManager().callEvent(new ActionBarUpdateEvent());
                });
            }
        };
    }

}

package carlos.dev.games.common.ui;

import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.common.RPGPlugin;
import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.core.player.skill.PlayerSkillUtility;
import carlos.dev.games.core.utils.actionbar.RPGActionBar;
import carlos.dev.games.core.utils.number.Numerals;
import carlos.dev.games.core.utils.string.StringEmojis;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public abstract class ActionBarManager {

    protected final RPGPlugin plugin;
    private final ActionBarProvider actionBarProvider;

    private final HashSet<UUID> isPaused = new HashSet<>();
    private final HashSet<UUID> isGainingXp = new HashSet<>();
    private final HashMap<UUID, Integer> timer = new HashMap<>();
    private final HashMap<UUID, Integer> currentAction = new HashMap<>();

    public ActionBarManager(RPGPlugin plugin, ActionBarProvider actionBarProvider) {
        this.plugin = plugin;
        this.actionBarProvider = actionBarProvider;
        startTimerCountdown();
        startUpdatingIdleActionBar();
    }

    public void startTimerCountdown() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                UUID uuid = onlinePlayer.getUniqueId();
                Integer time = timer.get(uuid);
                if (time != null) {
                    if (time > 0) {
                        timer.put(uuid, time - 1);
                    }
                } else {
                    timer.put(uuid, 0);
                }
            }
        }, 2 * 50L);
    }

    public void startUpdatingIdleActionBar() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                UUID uuid = onlinePlayer.getUniqueId();
                if (!currentAction.containsKey(uuid)) {
                    currentAction.put(uuid, 0);
                }
                if (isGainingXp.contains(uuid) || isPaused.contains(uuid)) {
                    continue;
                }

                RPGPlayer rpgPlayer = RPGPlayerUtility.getData(onlinePlayer);

                Component message = getComponent(rpgPlayer);


                PlayerSkill playerSkill = PlayerSkillUtility.getData(onlinePlayer);

                actionBarProvider.sendActionBar(playerSkill, message);
            }
        }, 20);
    }

    private static @NotNull Component getComponent(RPGPlayer rpgPlayer) {
        Component health = Component.text(
                StringEmojis.HEALTH.getPrefix() + " " + (int) rpgPlayer.getHealth() + "/" + (int) rpgPlayer.getMaxHealth()
        ).color(TextColor.color(215, 69, 64));

        Component stamina = Component.text(
                StringEmojis.STAMINA.getPrefix() + " " + (int) rpgPlayer.getStamina() + "/" + (int) rpgPlayer.getMaxStamina()
        ).color(TextColor.color(101, 168, 125));

        Component mana = Component.text(
                StringEmojis.MANA.getPrefix() + " " + rpgPlayer.getMana() + "/" + rpgPlayer.getMaxMana()
        ).color(TextColor.color(67, 155, 196));

        Component dv = Component.text("     ");
        return Component.text("").append(health).append(dv).append(stamina).append(dv).append(mana);
    }

    public void sendXpActionBar(PlayerSkill user, Skill skill, double earnedXp, boolean maxed) {
        ActionBarType sendType = maxed ? ActionBarType.MAXED : ActionBarType.XP;

        UUID uuid = user.getUuid();
        if (isPaused.contains(uuid)) {
            return;
        }

        isGainingXp.add(uuid);
        timer.put(uuid, 20);

        int thisAction = currentAction.getOrDefault(uuid, 0) + 1;
        currentAction.put(uuid, thisAction);

        if (!isGainingXp.contains(uuid)) {
            return;
        }

        Integer actionNum = currentAction.get(uuid);
        if (actionNum == null) {
            return;
        }

        if (thisAction != actionNum) {
            return;
        }
        plugin.getScheduler().runTaskTimerAsynchronously(MinecraftRPG.getInstance(), () -> {
                        Component message = getXpActionBarMessage(user, skill, earnedXp);
                        actionBarProvider.sendActionBar(user, message);

        },0, 5 * 50L);

        plugin.getScheduler().scheduleSyncDelayedTask(MinecraftRPG.getInstance(), () -> {
            Integer timerNum = timer.get(uuid);
            if (timerNum != null) {
                if (timerNum.equals(0)) {
                    isGainingXp.remove(uuid);
                }
            }
        }, 41 * 50L);
    }

    public void resetActionBars() {
        isGainingXp.clear();
        timer.clear();
        currentAction.clear();
        isPaused.clear();
    }

    public void resetActionBar(PlayerSkill user) {
        UUID uuid = user.getUuid();
        isGainingXp.remove(uuid);
        timer.remove(uuid);
        currentAction.remove(uuid);
        isPaused.remove(uuid);
    }

    public void setPaused(PlayerSkill user, int time) {
        UUID uuid = user.getUuid();
        isPaused.add(uuid);
        Integer action = currentAction.get(uuid);
        if (action != null) {
            currentAction.put(uuid, action + 1);
        } else {
            currentAction.put(uuid, 0);
        }
        int thisAction = this.currentAction.get(uuid);

       plugin.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
           Integer actionBarCurrentAction = currentAction.get(uuid);
           if (actionBarCurrentAction != null) {
               if (thisAction == actionBarCurrentAction) {
                   isPaused.remove(uuid);
               }
           }
       }, time);
    }

    public void sendAbilityActionBar(PlayerSkill user, Component message) {
        plugin.getActionBarProvider().sendActionBar(user, message);
        setPaused(user, 15 * 50);
    }

    private Component getXpActionBarMessage(PlayerSkill user, Skill skill, double earnedXp) {;
        String skillName = skill.getFancyName();

        String xp = Numerals.toKMB((int) user.getExperience(skill));
        String cost = Numerals.toKMB((int) user.getXpCost(skill, user.getLevel(skill)));
        String gain = Numerals.toKMB((int) earnedXp);

         return Component.text("+" + gain + " " + skillName + " (" + xp + "/" + cost + ")").color(TextColor.color(113, 189, 95));
    }

    @NotNull
    public abstract String getHp(RPGPlayer user);

    @NotNull
    public abstract String getMaxHp(RPGPlayer user);

    @NotNull
    public abstract String getWorldName(PlayerSkill user);


}

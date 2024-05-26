package carlos.dev.games.core.player.skill.listener;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.api.skill.SkillGainXpEvent;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.actionbar.RPGActionBar;
import carlos.dev.games.core.utils.number.Numerals;
import carlos.dev.games.core.utils.string.StringPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class SkillGainXpListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onGainXp(SkillGainXpEvent event) {
        final Skill skill = event.getSkill();
        final PlayerSkill user = event.getUser();
        final double earnedXp = event.getEarnedXp();

        final double amount = user.getExperience(skill) + earnedXp;
        user.setExperience(skill, amount);

        Player player = Bukkit.getPlayer(user.getName());
        if (player == null) return;

        String skillName = skill.getFancyName();

        String xp = Numerals.toKMB((int) user.getExperience(skill));
        String cost = Numerals.toKMB((int) user.getXpCost(skill, user.getLevel(skill)));
        String gain = Numerals.toKMB((int) earnedXp);

        Component message = Component.text("+" + gain + " " + skillName + " (" + xp + "/" + cost + ")").color(TextColor.color(113, 189, 95));

        ActionBarManager manager = MinecraftRPG.getInstance().getActionBarManager();
        RPGActionBar rpg = manager.getData(player);

        rpg.switchWithTime(StringPosition.CENTER, message, 2);

        /*player.sendActionBar(
                Component.text(message).color(TextColor.color(0xBFFF))
                        .decoration(TextDecoration.ITALIC, false)
        );
         */
    }
}
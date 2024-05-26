package carlos.dev.games.core.player.skill.listener;

import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.api.skill.SkillUpdateLevelEvent;
import carlos.dev.games.core.utils.effects.EffectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class SkillUpdateLevelListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onLevel(SkillUpdateLevelEvent event) {
        final Skill skill = event.getSkill();
        final PlayerSkill user = event.getUser();
        user.handleUpdate(skill);

        final Player player = Bukkit.getPlayer(user.getName());
        if (player == null) return;

        EffectUtils.sound(player, Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT);

        /*TemporaryBossBarManager temporaryBossBarManager = InterfacePlugin.getInstance().getTemporaryBossBarManager();
        temporaryBossBarManager.sendTo(
                player,
                "§6➡ " + skill.getDisplayName() + " (" + (int) user.getLevel(skill) + ") ⬅",
                3);

         */
    }
}
package carlos.dev.games.common.ui;

import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.core.player.skill.PlayerSkill;
import net.kyori.adventure.text.Component;

public interface ActionBarProvider {

    ActionBarManager getActionBarManager();

    void sendActionBar(PlayerSkill user, Component message);

}
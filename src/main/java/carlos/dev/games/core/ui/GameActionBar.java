package carlos.dev.games.core.ui;

import carlos.dev.games.common.RPGPlugin;
import carlos.dev.games.common.ui.ActionBarManager;
import carlos.dev.games.common.ui.ActionBarProvider;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.skill.PlayerSkill;
import carlos.dev.games.core.utils.string.StringEmojis;
import org.jetbrains.annotations.NotNull;

public class GameActionBar extends ActionBarManager {
    public GameActionBar(RPGPlugin plugin, ActionBarProvider actionBarProvider) {
        super(plugin, actionBarProvider);
    }

    @Override
    public @NotNull String getHp(RPGPlayer user) {
        return StringEmojis.HEALTH.getPrefix() +  " " + user.getHealth() + "/" + user.getMaxHealth();
    }

    @Override
    public @NotNull String getMaxHp(RPGPlayer user) {
        return "" + user.getMaxHealth();
    }

    @Override
    public @NotNull String getWorldName(PlayerSkill user) {
        return "";
    }
}

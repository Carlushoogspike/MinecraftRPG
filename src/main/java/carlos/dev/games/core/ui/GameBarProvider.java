package carlos.dev.games.core.ui;

import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.common.ui.ActionBarManager;
import carlos.dev.games.common.ui.ActionBarProvider;
import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.player.skill.PlayerSkill;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameBarProvider implements ActionBarProvider {

    private final MinecraftRPG plugin;
    private final ActionBarManager actionBar;

    public GameBarProvider(MinecraftRPG plugin){
        this.plugin = plugin;
        this.actionBar = new GameActionBar(plugin, this);
    }

    @Override
    public ActionBarManager getActionBarManager() {
        return actionBar;
    }

    @Override
    public void sendActionBar(PlayerSkill user, Component message) {
        Player player = Bukkit.getPlayer(user.getUuid());
        if (player == null) return;

        player.sendActionBar(message);
    }


}

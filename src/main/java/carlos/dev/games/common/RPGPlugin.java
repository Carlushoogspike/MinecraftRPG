package carlos.dev.games.common;

import carlos.dev.games.common.ui.ActionBarProvider;
import carlos.dev.games.core.events.EventLoader;
import carlos.dev.games.core.meta.mechanic.MechanicManager;
import carlos.dev.games.core.meta.resource.SourceManager;
import carlos.dev.games.core.meta.skills.SkillRegistry;
import carlos.dev.games.core.player.base.PlayerTaskManager;
import carlos.dev.games.core.reloaders.LoaderManager;
import carlos.dev.games.core.reloaders.MobManager;
import carlos.dev.games.core.reloaders.TierManager;
import carlos.dev.games.core.reloaders.base.AbilityBaseManager;
import carlos.dev.games.core.reloaders.base.SkillBaseManager;
import carlos.dev.games.core.reloaders.base.SkillLoader;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public interface RPGPlugin {

    TierManager getTierManager();
    
    LoaderManager getLoaderManager();

    MobManager getMobManager();

    EventLoader getEventLoader();

    ActionBarManager getActionBarManager();

    PlayerTaskManager getPlayerTaskManager();

    MechanicManager getMechanicManager();

    SkillBaseManager getSkillBaseManager();

    AbilityBaseManager getAbilityBaseManager();

    SkillLoader getSkillLoader();

    SourceManager getSourceManager();

    SkillRegistry getSkillRegistry();

    ActionBarProvider getActionBarProvider();

    BukkitScheduler getScheduler();
}

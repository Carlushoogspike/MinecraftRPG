package carlos.dev.games.core;

import carlos.dev.games.common.RPGPlugin;
import carlos.dev.games.common.ui.ActionBarProvider;
import carlos.dev.games.core.events.EventLoader;
import carlos.dev.games.core.meta.mechanic.MechanicManager;
import carlos.dev.games.core.meta.resource.SourceManager;
import carlos.dev.games.core.meta.skills.SkillRegistry;
import carlos.dev.games.core.player.base.PlayerTaskManager;
import carlos.dev.games.core.player.inventory.RPGInventoryEvents;
import carlos.dev.games.core.reloaders.LoaderManager;
import carlos.dev.games.core.reloaders.MobManager;
import carlos.dev.games.core.reloaders.base.AbilityBaseManager;
import carlos.dev.games.core.reloaders.base.SkillBaseManager;
import carlos.dev.games.core.reloaders.TierManager;
import carlos.dev.games.core.reloaders.base.SkillLoader;
import carlos.dev.games.core.ui.GameBarProvider;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

@Getter
public final class MinecraftRPG extends JavaPlugin implements RPGPlugin {

    @Getter
    private static MinecraftRPG instance;

    private TierManager tierManager;
    private LoaderManager loaderManager;
    //
    private MobManager mobManager;
    private EventLoader eventLoader;
    private ActionBarManager actionBarManager;
    //
    private PlayerTaskManager playerTaskManager;
    private MechanicManager mechanicManager;
    //Ability
    private SkillBaseManager skillBaseManager;
    private AbilityBaseManager abilityBaseManager;
    private SkillLoader skillLoader;
    //Source
    private SourceManager sourceManager;
    private SkillRegistry skillRegistry;
    //
    private GameBarProvider gameProvider;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new RPGInventoryEvents(), this);

        tierManager = new TierManager();
        loaderManager = new LoaderManager(this);
        mobManager = new MobManager();

        actionBarManager = new ActionBarManager();
        playerTaskManager = new PlayerTaskManager();

        loaderManager.load();

        eventLoader = new EventLoader(this);

        mechanicManager = new MechanicManager();
        mechanicManager.registerMechanics(this);

        skillLoader = new SkillLoader(this);
        skillBaseManager = new SkillBaseManager(this);
        abilityBaseManager = new AbilityBaseManager(this);

        sourceManager = new SourceManager(this);
        sourceManager.register();

        skillRegistry = new SkillRegistry(this);
        skillRegistry.registerSkills();

        gameProvider = new GameBarProvider(this);

    }

    @Override
    public void onDisable() {
    }

    @Override
    public ActionBarProvider getActionBarProvider() {
        return null;
    }

    @Override
    public BukkitScheduler getScheduler() {
        return this.getServer().getScheduler();
    }
}

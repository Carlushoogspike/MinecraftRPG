package carlos.dev.games.core.meta.skills;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.meta.skills.mining.MiningSkill;

public class SkillRegistry {

    private final MinecraftRPG plugin;

    public SkillRegistry(MinecraftRPG plugin){
        this.plugin = plugin;
    }

    public void registerSkills(){
        new MiningSkill(plugin);
    }

}

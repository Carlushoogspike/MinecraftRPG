package carlos.dev.games.core.reloaders.base;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.Skill;

import java.util.HashMap;
import java.util.Map;

public class SkillLoader {

    private final Map<String, Skill> skillBaseMap;
    private final MinecraftRPG plugin;

    public SkillLoader(MinecraftRPG plugin) {
        this.skillBaseMap = new HashMap<>();
        this.plugin = plugin;
    }

    public void register(Skill skill){
        if (skillBaseMap.containsKey(skill.getIdentifier())) return;
        skillBaseMap.put(skill.getIdentifier(), skill);
        plugin.getLogger().info("Skills > " + skill.getIdentifier() + " registered");
    }

    public Skill getSkill(String identifier){
        if (!skillBaseMap.containsKey(identifier)) return null;
        return skillBaseMap.get(identifier);
    }

}

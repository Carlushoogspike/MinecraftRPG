package carlos.dev.games.core.player.skill;

import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.api.skill.SkillUpdateLevelEvent;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class PlayerSkill {

    private final String name;
    private final UUID uuid;

    @Builder.Default
    private final Map<String, SkillAttributes> skillsMap = Maps.newHashMap();

    @Builder.Default
    private boolean dirty = false;

    public void setExperience(@NotNull Skill skill, double amount) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());

        attributes.setExperience(amount);

        if (canUpdate(skill)) {
            Bukkit.getPluginManager().callEvent(new SkillUpdateLevelEvent(skill, this));
        }

        skillsMap.put(skill.getIdentifier(), attributes);

        dirty = true;
    }

    public void setLevel(@NotNull Skill skill, double level) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());

        attributes.setLevel(level);
        attributes.setExperienceNeeded(getXpCost(skill, level));
        skillsMap.put(skill.getIdentifier(), attributes);

        dirty = true;
    }

    public double getExperience(@NotNull Skill skill) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());
        return attributes.getExperience();
    }

    public double getLevel(@NotNull Skill skill) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());
        return attributes.getLevel();
    }

    public double getXpCost(@NotNull Skill skill, double level) {
        return 200 * Math.pow(skill.getMultiplier(), level - 1);
    }

    public boolean canUpdate(@NotNull Skill skill) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());

        final double experience = attributes.getExperience();
        final double cost = attributes.getExperienceNeeded();

        return experience >= cost;
    }

    public void handleUpdate(@NotNull Skill skill) {
        final SkillAttributes attributes =
                skillsMap.getOrDefault(skill.getIdentifier(), SkillAttributes.buildDefault());

        final double nextLevel = getLevel(skill) + 1;
        attributes.setLevel(nextLevel);
        attributes.setExperience(0);
        attributes.setExperienceNeeded(getXpCost(skill, nextLevel));

        skillsMap.put(skill.getIdentifier(), attributes);

        dirty = true;
    }

    public void addExperience(Skill skill, double amount){
        final double gainAmount = getExperience(skill) + amount;
        setExperience(skill, gainAmount);
    }

    public void addLevel(Skill skill, int amount){
        final double gainAmount = getLevel(skill) + amount;
        setLevel(skill, gainAmount);
    }

    public void subtractExperience(Skill skill, double amount){
        setExperience(skill, getExperience(skill) - amount);
    }

    public void subtractLevel(Skill skill, int amount){
        setLevel(skill, getLevel(skill) - amount);
    }
}

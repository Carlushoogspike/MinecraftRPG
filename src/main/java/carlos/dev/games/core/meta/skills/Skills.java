package carlos.dev.games.core.meta.skills;

import carlos.dev.games.api.skill.Skill;
import lombok.Getter;

import java.util.List;

@Getter
public enum Skills implements Skill {

    MINING("mining");

    private final String identifier;

    Skills(String identifier){
        this.identifier = identifier;
    }

    @Override
    public boolean isEnabled() {
        return SkillData.isEnabled(this.identifier);
    }

    @Override
    public String getFancyName() {
        return SkillData.getFancyName(identifier, false);
    }

    @Override
    public String getFancyName(boolean formatted) {
        return SkillData.getFancyName(identifier, true);
    }

    @Override
    public List<String> getDescription() {
        return SkillData.getDescription(identifier, false);
    }

    @Override
    public List<String> getDescription(boolean formatted) {
        return SkillData.getDescription(identifier, true);
    }

    @Override
    public List<String> getAbilities() {
        return List.of();
    }

    @Override
    public int getStartLevel() {
        return SkillData.getStartLevel(identifier);
    }

    @Override
    public int getMaxLevel() {
        return SkillData.getMaxLevel(identifier);
    }

    @Override
    public double getMultiplier() {
        return SkillData.getMultiplier(identifier);
    }

    @Override
    public double getBaseValue() {
        return SkillData.getBaseValue(identifier);
    }

    @Override
    public String getFormula() {
        return SkillData.getFormula(identifier);
    }
}

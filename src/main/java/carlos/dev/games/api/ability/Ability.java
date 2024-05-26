package carlos.dev.games.api.ability;

import carlos.dev.games.api.skill.Skill;

import java.util.List;

public interface Ability {

    String getFancyName();

    List<String> getDescription();

    Skill getSkill();

    int getMaxLevel();

    boolean isConsumable();

    double getHealth();

    double getMana();

    double getStamina();
}

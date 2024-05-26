package carlos.dev.games.api.skill;

import java.util.List;

public interface Skill {

    String getIdentifier();

    boolean isEnabled();

    String getFancyName();
    String getFancyName(boolean formatted);

    List<String> getDescription();
    List<String> getDescription(boolean formatted);

    List<String> getAbilities();

    int getStartLevel();
    int getMaxLevel();
    double getMultiplier();
    double getBaseValue();
    String getFormula();
}

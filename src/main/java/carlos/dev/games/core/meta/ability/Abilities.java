package carlos.dev.games.core.meta.ability;

import carlos.dev.games.api.ability.Ability;
import carlos.dev.games.api.skill.Skill;
import lombok.Getter;

import java.util.List;

@Getter
public enum Abilities implements Ability {

    CRAZY_MINER("crazy_miner)");

    private final String identifier;

    Abilities(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getFancyName() {
        return AbilityProvider.getFancyName(identifier, false);
    }

    @Override
    public List<String> getDescription() {
        return AbilityProvider.getDescription(identifier, true);
    }

    @Override
    public Skill getSkill() {
        return AbilityProvider.getSkill(identifier);
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public boolean isConsumable() {
        return AbilityProvider.isConsumable(identifier);
    }

    @Override
    public double getHealth() {
        return AbilityProvider.getHealth(identifier);
    }

    @Override
    public double getMana() {
        return AbilityProvider.getMana(identifier);
    }

    @Override
    public double getStamina() {
        return AbilityProvider.getStamina(identifier);
    }

}

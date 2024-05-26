package carlos.dev.games.core.meta.ability;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.core.reloaders.base.AbilityBaseManager;

import java.util.List;

public class AbilityProvider {

    private static final AbilityBaseManager manager = MinecraftRPG.getInstance().getAbilityBaseManager();


    public static String getFancyName(String id, boolean formatted){
        return manager.getBase(id).getFancyName();
    }

    public static List<String> getDescription(String id, boolean formatted){
        return manager.getBase(id).getDescription();
    }

    public static Skill getSkill(String id){
        return manager.getBase(id).getSkill();
    }

    public static int getMaxLevel(String id){
        return manager.getBase(id).getMaxLevel();
    }

    public static boolean isConsumable(String id){
        return manager.getBase(id).isConsumable();
    }

    public static double getHealth(String id){
        return manager.getBase(id).getHealth();
    }

    public static double getMana(String id){
        return manager.getBase(id).getMana();
    }

    public static double getStamina(String id){
        return manager.getBase(id).getStamina();
    }

}

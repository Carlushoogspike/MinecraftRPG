package carlos.dev.games.core.meta.skills;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.reloaders.base.SkillBaseManager;

import java.util.List;

public class SkillData {

    private static final SkillBaseManager manager = MinecraftRPG.getInstance().getSkillBaseManager();

    public static boolean isEnabled(String id){
        return manager.getBase(id).isEnabled();
    }

    public static String getFancyName(String id, boolean formatted){
        if (formatted){
            return manager.getBase(id).getFancyName(true);
        }
        return manager.getBase(id).getFancyName();
    }

    public static List<String> getDescription(String id, boolean formatted){
        if (formatted){
            return manager.getBase(id).getDescription(true);
        }
        return manager.getBase(id).getDescription();
    }

    public static int getStartLevel(String id){
        return manager.getBase(id).getStartLevel();
    }

    public static int getMaxLevel(String id){
        return manager.getBase(id).getMaxLevel();
    }

    public static double getMultiplier(String id){
        return manager.getBase(id).getMultiplier();
    }

    public static double getBaseValue(String id){
        return manager.getBase(id).getBaseValue();
    }
    public static String getFormula(String id){
        return manager.getBase(id).getFormula();
    }

}

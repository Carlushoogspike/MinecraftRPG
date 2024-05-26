package carlos.dev.games.core.meta.mob.util;

import carlos.dev.games.core.utils.PersistentDataContainerUtil;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MobUtils {

    private static final String LEVEL_ID = "LEVEL_DATA";
    private static final String INDEX_ID = "ID_DATA";

    public static boolean isMarkedMob(@NotNull LivingEntity entity){
        return PersistentDataContainerUtil.hasString(entity, INDEX_ID);
    }

    public static void markIndexId(LivingEntity entity, String id){
        PersistentDataContainerUtil.putString(INDEX_ID, id, entity);
    }

    public static String getId(LivingEntity entity){
        if (PersistentDataContainerUtil.hasString(entity, INDEX_ID)){
            return PersistentDataContainerUtil.getString(entity, INDEX_ID);
        }
        return null;
    }

    public static void markLevel(LivingEntity entity, int level){
        PersistentDataContainerUtil.putInteger(LEVEL_ID, level, entity);
    };

    public static int getLevel(LivingEntity entity){
        if(PersistentDataContainerUtil.hasInteger(entity, LEVEL_ID)){
            return PersistentDataContainerUtil.getInteger(entity, LEVEL_ID);
        }
        return 0;
    }

    

}

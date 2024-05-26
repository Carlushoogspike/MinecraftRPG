package carlos.dev.games.api.mob;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RPGMob {

    private ModelMobBaseImpl base;
    private Map<Integer, ModelMobLevelImpl> levels;

    public ModelMobLevelImpl getLevel(int level){
        if (!levels.containsKey(level)) return null;
        return levels.get(level);
    }

}

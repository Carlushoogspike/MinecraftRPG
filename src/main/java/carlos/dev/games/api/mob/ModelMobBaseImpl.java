package carlos.dev.games.api.mob;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.EntityType;

@Data
@AllArgsConstructor
public class ModelMobBaseImpl {

    private String name;
    private String id;
    private EntityType type;

}

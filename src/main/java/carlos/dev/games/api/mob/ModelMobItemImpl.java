package carlos.dev.games.api.mob;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModelMobItemImpl {

    private String key;
    private String type;
    private boolean colorize;
    private String color;
    private boolean enchant;
    private List<String> enchantments;

}

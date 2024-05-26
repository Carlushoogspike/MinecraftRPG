package carlos.dev.games.core.meta.resource.values;

import carlos.dev.games.api.source.ValueSource;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

@Data
public class BlockValues implements ValueSource {

    private String type;
    private double xp;
    private double price;
    private double dropPercentage;
    private int requiredLevel;

    public BlockValues(@NotNull ConfigurationSection section, @NotNull String trigger){
        this.type = section.getName().toUpperCase().replace("-","_");
        this.xp = section.getDouble(trigger + ".xp");
        this.price = section.getDouble(trigger + ".price");
        this.dropPercentage = section.getDouble(trigger + ".drop-percentage");
        this.requiredLevel = section.getInt(trigger + ".required-level");
    }
}

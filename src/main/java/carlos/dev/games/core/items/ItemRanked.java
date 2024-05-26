package carlos.dev.games.core.items;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

@Getter
public class ItemRanked {

    private final String id;
    private final String prefix;
    private final boolean glow;
    private final NamedTextColor color;

    private final boolean defaultTier;

    public ItemRanked(@NotNull ConfigurationSection section) {
        this.id = section.getName().toUpperCase().replace("-", "_");
        this.prefix = section.getString("prefix");

        if (section.contains("settings")) {
            this.defaultTier = section.getBoolean("settings.default");
        } else {
            this.defaultTier = false;
        }

        if (section.contains("item-glow")) {
            this.glow = true;
            String toColor = section.getString("item-glow.color");
            TextColor rawColor = TextColor.fromHexString(toColor);
            this.color = NamedTextColor.nearestTo(rawColor);
        } else {
            this.glow = false;
            this.color = null;
        }
    }


}

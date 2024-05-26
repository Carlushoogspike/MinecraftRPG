package carlos.dev.games.core.reloaders;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.api.reloaders.Reloadable;
import carlos.dev.games.core.utils.config.ConfigFile;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class TierManager implements Reloadable {

    private final Map<String, ItemRanked> tiers = new HashMap<>();

    public TierManager() {
        reload();
    }

    public void reload() {
        tiers.clear();
        MinecraftRPG.getInstance().getLogger().info("Loading item tiers...");

        ConfigFile config = new ConfigFile("item-tiers");
        config.setup();

        for (String tierName : config.getConfig().getKeys(false)) {

            ConfigurationSection tierSection  = getSection(config.getConfig(), tierName);

            try {
                register(new ItemRanked(tierSection));
            } catch (IllegalArgumentException exception) {
                MinecraftRPG.getInstance().getLogger().log(Level.SEVERE, tierName + ": " + exception.getMessage());
            }
        }
    }

    public void register(@NotNull ItemRanked tier) {
        if (has(tier.getId())) {
            throw new IllegalArgumentException("Duplicate item tier: " + tier.getId());
        }

        if (tier.isDefaultTier()){
            MinecraftRPG.getInstance().getLogger().info("Loaded tier DEFAULT : " + tier.getId());
        } else {
            MinecraftRPG.getInstance().getLogger().info("Loaded tier: " + tier.getId());
        }
        tiers.put(tier.getId(), tier);
    }

    public boolean has(@Nullable String id) {
        if (id == null) { return false; }
        return tiers.containsKey(id);
    }

    @Nullable public ItemRanked get(@Nullable String id) {
        if (id == null) { return null; }
        return tiers.get(id);
    }

    @NotNull public Collection<ItemRanked> getAll() {
        return tiers.values();
    }

    @NotNull
    public static ConfigurationSection getSection(@NotNull ConfigurationSection root, @NotNull String path) {
        ConfigurationSection section = root.getConfigurationSection(path);
        if (section == null) {
            section = root.createSection(path);
        }
        return section;
    }

    @NotNull
    public ItemRanked getDefaultTier(){
        for (ItemRanked tier : getAll()) {
            if (tier.isDefaultTier()) {
                return tier;
            }
        }

        return getAll().iterator().next();
    }

}

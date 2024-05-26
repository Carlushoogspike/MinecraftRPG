package carlos.dev.games.core.meta.resource.impl;

import carlos.dev.games.api.source.SourceProvider;
import carlos.dev.games.core.meta.resource.triggers.BlockTriggers;
import carlos.dev.games.core.meta.resource.values.BlockValues;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BlockSource extends SourceProvider<BlockTriggers, BlockValues> {

    private final Map<BlockTriggers, BlockValues> map;

    public BlockSource(FileConfiguration config) {
        super(config);
        this.map = new HashMap<>();
        loadValues();
    }

    @Override
    public @NotNull Map<BlockTriggers, BlockValues> getMap() {
        return map;
    }

    @Override
    public void loadValues() {
        FileConfiguration config = getConfig();
        for (String block : config.getConfigurationSection("blocks").getKeys(false)){
            ConfigurationSection sc = getSection(config, "blocks." + block);

            if (sc.contains("break")){
                BlockValues value = new BlockValues(sc, "break");
                Bukkit.getLogger().info("Source Block | Loaded Break = " + value.getType() + " " + value.toString());
                map.put(BlockTriggers.BREAK, value);
            } else {
                System.out.println("No break section found for block: " + block);
            }

            if (sc.contains("place")){
                BlockValues value = new BlockValues(sc, "place");
                Bukkit.getLogger().info("Source Block | Loaded Place = " + value.getType() + " " + value.toString());
                map.put(BlockTriggers.PLACE, value);
            } else {
                System.out.println("No place section found for block: " + block);
            }
        }
    }



    @Override
    public String getNamespace() {
        return "block_sources";
    }

    @Override
    public boolean isEnabled() {
        return getConfig().getBoolean("enabled");
    }



}

package carlos.dev.games.core.reloaders;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.core.meta.mob.util.MobParserUtil;
import carlos.dev.games.api.reloaders.Reloadable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class MobManager implements Reloadable {

    private final Map<String, RPGMob> mobs = new HashMap<>();

    public MobManager(){
        reload();
    }

    public void reload() {
        mobs.clear();
        MinecraftRPG.getInstance().getLogger().info("Loading item tiers...");

        final File folder = new File(MinecraftRPG.getInstance().getDataFolder(), "mobs");
        if (!folder.exists()) {
            MinecraftRPG.getInstance().getLogger().log(Level.WARNING, "Creating mob folder...");
            folder.mkdir();
        }

        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (!file.getName().endsWith(".yml")) continue;

            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            RPGMob rpgMob = MobParserUtil.parseMob(configuration);

            try {
                register(rpgMob);
            } catch (IllegalArgumentException exception) {
                MinecraftRPG.getInstance().getLogger().log(Level.SEVERE, rpgMob.getBase().getName() + ": " + exception.getMessage());
            }
        }
    }

    public void register(@NotNull RPGMob mob) {
        if (has(mob.getBase().getId())) {
            throw new IllegalArgumentException("Duplicate RPG Mob: " + mob.getBase().getId());
        }

        MinecraftRPG.getInstance().getLogger().info("Loaded Mob: " + mob.getBase().getId());
        mobs.put(mob.getBase().getId(), mob);

    }

    public boolean has(@Nullable String id) {
        if (id == null) { return false; }
        return mobs.containsKey(id);
    }

    @Nullable public RPGMob get(@Nullable String id) {
        if (id == null) { return null; }
        return mobs.get(id);
    }

    @NotNull public Collection<RPGMob> getAll() {
        return mobs.values();
    }

}

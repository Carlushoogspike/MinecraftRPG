package carlos.dev.games.core.meta.mob.util;

import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.api.mob.ModelMobBaseImpl;
import carlos.dev.games.api.mob.ModelMobItemImpl;
import carlos.dev.games.api.mob.ModelMobLevelImpl;
import carlos.dev.games.core.player.inventory.slots.ItemSlotDefault;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobParserUtil {

    public static RPGMob parseMob(FileConfiguration config) {
        ModelMobBaseImpl base = parseBase(config);
        Map<Integer, ModelMobLevelImpl> levels = parseLevel(config);

        return new RPGMob(base, levels);
    }

    private static ModelMobBaseImpl parseBase(FileConfiguration configuration) {
        String name = configuration.getString("base.name");
        String id = configuration.getString("base.id");
        EntityType type = EntityType.valueOf(configuration.getString("base.type"));

        return new ModelMobBaseImpl(name, id, type);
    }

    private static Map<Integer, ModelMobLevelImpl> parseLevel(FileConfiguration configuration) {
        Map<Integer, ModelMobLevelImpl> levelMap = new HashMap<>();

        for (String levels : configuration.getConfigurationSection("levels").getKeys(false)) {
            String path = "levels." + levels + ".";
            String base = path + "base.";
            String options = path + "options";
            String custom = path + "custom";

            int level = configuration.getInt("levels." + levels);
            double maxHealth = configuration.getDouble(base + "max-health");
            int strength = configuration.getInt(base + "strength");
            int speed = configuration.getInt(base + "speed");
            int defense = configuration.getInt(base + "defense");

            ModelMobLevelImpl.ModelMobLevelImplBuilder builder = ModelMobLevelImpl.builder();

            builder
                    .level(level)
                    .maxHealth(maxHealth)
                    .strength(strength)
                    .speed(speed)
                    .defense(defense);

            if (configuration.contains(options)) {
                boolean spawnDefault = configuration.getBoolean(options + ".spawn-default");
                double spawnChance = configuration.getDouble(options + ".spawn-chance");

                builder
                        .spawnedDefault(spawnDefault)
                        .spawnChance(spawnChance);
            }

            if (configuration.contains(custom)) {
                Map<ItemSlotDefault, ModelMobItemImpl> items = new HashMap<>();

                if (configuration.contains(custom + ".hand")) {
                    val hand = parseItem(configuration, custom + ".hand");
                    items.put(ItemSlotDefault.HAND, hand);
                }
                if (configuration.contains(custom + ".off-hand")) {
                    val offhand = parseItem(configuration, custom + ".off-hand");
                    items.put(ItemSlotDefault.OFF_HAND, offhand);
                }
                if (configuration.contains(custom + ".helmet")) {
                    val helmet = parseItem(configuration, custom + ".helmet");
                    items.put(ItemSlotDefault.HELMET, helmet);
                }
                if (configuration.contains(custom + ".chestplate")) {
                    val chestplate = parseItem(configuration, custom + ".chestplate");
                    items.put(ItemSlotDefault.CHESTPLATE, chestplate);
                }
                if (configuration.contains(custom + ".leggins")) {
                    val leggins = parseItem(configuration, custom + ".leggins");
                    items.put(ItemSlotDefault.LEGGINGS, leggins);
                }
                if (configuration.contains(custom + ".boots")) {
                    val boots = parseItem(configuration, custom + ".boots");
                    items.put(ItemSlotDefault.BOOTS, boots);
                }

                builder.items(items);
            }

            ModelMobLevelImpl item = builder.build();
            System.out.println("loaded level" + level);
            levelMap.put(level, item);
        }
        return levelMap;
    }

    private static ModelMobItemImpl parseItem(FileConfiguration section, String path) {
        String key = section.getString(path + ".key");
        String type = section.getString(path + ".type");

        ModelMobItemImpl.ModelMobItemImplBuilder builder = ModelMobItemImpl.builder();

        builder.key(key).type(type);

        if (type != null && type.contains("LEATHER_")){
            String color = section.getString(path + ".color");
            builder.colorize(true).color(color);
        }

        if (section.contains(path + ".enchantments")){
            List<String> enchants = section.getStringList(path + ".enchantments");
            builder.enchant(true).enchantments(enchants);
        }

        return builder.build();
    }


}

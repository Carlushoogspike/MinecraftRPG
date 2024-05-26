package carlos.dev.games.core.utils.config;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.utils.FilesType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.function.Consumer;

public class ConfigurationUtils {


    public static void contains(FileConfiguration root, String path, FilesType type, Consumer<Object> consumer) {
        Object value = root.get(path);
        switch (type) {
            case BOOLEAN:
                if (value instanceof Boolean) {
                    consumer.accept((Boolean) value);
                }
                break;
            case INT:
                if (value instanceof Integer) {
                    consumer.accept((Integer) value);
                }
                break;
            case DOUBLE:
                if (value instanceof Double) {
                    consumer.accept((Double) value);
                }
                break;
            case STRING:
                if (value instanceof String) {
                    consumer.accept((String) value);
                }
                break;
            case LIST:
                if (value instanceof List<?>) {
                    consumer.accept((List<?>) value);
                }
                break;
            case ITEM_TIER:
                if (value instanceof String){
                    ItemRanked tier = MinecraftRPG.getInstance().getTierManager().get((String) value);
                    consumer.accept(tier);
                }
        }
    }


}

package carlos.dev.games.core.reloaders;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.core.items.utils.ItemParserUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoaderManager {

    private final Map<ItemType, ItemCustomizable> items;
    private final MinecraftRPG game;

    private final File folder;
    private final List<String> folderTypes;

    public LoaderManager(MinecraftRPG game) {
        this.game = game;
        this.items = new HashMap<>();
        this.folder = new File(game.getDataFolder(), "items");
        if (!folder.exists()){
            folder.mkdir();
        }
        this.folderTypes = List.of(
                "armor","weapon","consumable",
                "tool","block","projectile","explosive",
                "enchantment","fluid","fuel","catalyst",
                "amulet","ring","necklace","glove",
                "potion","book");
    }

    public void load(){
        folders();
        loadItems();
    }

    private void loadItems(){
        folderTypes.forEach(this::load);
    }

    private void folders(){
        folderTypes.forEach(this::mkdir);
    }

    private void mkdir(String name){
        File dir = new File(folder, name);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    private void load(String name){
        File file = new File(folder, name);
        if(!file.exists()){
            return;
        }

        File[] files = file.listFiles();
        if (files == null) return;
        for (File f : files){
            if (!f.getName().endsWith(".yml")) return;
            FileConfiguration config = YamlConfiguration.loadConfiguration(f);
            ItemCustomizable rpgItem = ItemParserUtils.readYML(config);

            String fileName = file.getName().toUpperCase();
            ItemType type = ItemType.valueOf(fileName);

            System.out.println("Carregando item: " + rpgItem.getName() + " do tipo: " + type);
            items.put(type, rpgItem);
            game.getLogger().info("Loaded item: " + rpgItem.getName());
        }
    }

    private static ItemType findItemType(String input) {
        for (ItemType type : ItemType.values()) {
            if (type.name().equals(input)) {
                return type;
            }
        }
        return null;
    }


    public ItemCustomizable getItem(ItemType type, String id){
        System.out.println("Buscando item: " + id + " do tipo: " + type);
        if (type == null) {
            System.out.println("Tipo de item inválido: " + type);
            return null;
        }
        if (!items.containsKey(type)) {
            System.out.println("Tipo de item não encontrado no mapa: " + type);
            return null;
        }
        ItemCustomizable item = items.get(type);
        if (item.getId().equals(id)){
            System.out.println("Item encontrado: " + item.getName());
            return item;
        } else {
            System.out.println("Item não encontrado com o ID: " + id);
            return null;
        }
    }

    public ItemCustomizable getItem(String id){
        for (Map.Entry<ItemType, ItemCustomizable> entry : items.entrySet()) {
            if (entry.getValue().getId().equals(id)){
                return entry.getValue();
            }
        }
        return null;
    }

}

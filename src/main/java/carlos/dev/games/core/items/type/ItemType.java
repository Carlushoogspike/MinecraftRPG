package carlos.dev.games.core.items.type;

import lombok.Getter;

@Getter
public enum ItemType {

    UNDEFINED("Indefinido"),

    WEAPON("Arma"),
    ARMOR("Armadura"),
    CONSUMABLE("Consumível"),
    TOOL("Ferramenta"),
    BLOCK("Bloco"),
    PROJECTILE("Projetil"),
    EXPLOSIVE("Explosivo"),
    ENCHANTMENT("Encantamento"),
    FLUID("Fluido"),
    FUEL("Combustível"),
    CATALYST("Catalisador"),
    AMULET("Amuleto"),
    RING("Anel"),
    NECKLACE("Colar"),
    GLOVE("Luvas"),
    POTION("Poção"),
    BOOK("Livro"),
    STUFF("Varinha"),

    RUNE("Runa"),
    SCROLL("Pergaminho"),

    SWORD("Espada"),
    PICKAXE("Picareta"),
    AXE("Machado"),
    SHOVEL("Pá"),
    HOE("Enxada"),

    BOW("Arco"),
    CROSSBOW("Besta"),
    TRIDENT("Tridente"),
    FISHING_ROD("Vara de Pesca"),
    SHIELD("Escudo"),

    HELMET("Capacete"),
    CHESTPLATE("Peitoral"),
    LEGGINGS("Calças"),
    BOOTS("Botas");


    private final String prefix;

    ItemType(String prefix) {
        this.prefix = prefix;
    }


}

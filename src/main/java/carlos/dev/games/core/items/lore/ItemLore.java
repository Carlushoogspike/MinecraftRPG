package carlos.dev.games.core.items.lore;

import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.items.type.ItemSettingsCatalog;
import carlos.dev.games.core.items.type.TypeController;
import carlos.dev.games.core.items.utils.ItemRPGUtils;
import carlos.dev.games.core.items.utils.ItemTypeUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemLore {

    private static final String PREFIX_ATTACK_SPEED = "Velocidade de Ataque: ";
    private static final String PREFIX_ATTACK_DAMAGE = "Dano de Ataque: ";
    private static final String PREFIX_ATTACK_RECOVER = "Recuperação de Ataque: ";
    private static final String PREFIX_USE_HEALTH = "Uso de Vida: ";
    private static final String PREFIX_GAIN_HEALTH = "Ganho de Vida: ";
    private static final String PREFIX_USE_MANA = "Uso de Mana: ";
    private static final String PREFIX_GAIN_MANA = "Ganho de Mana: ";
    private static final String PREFIX_USE_STAMINA = "Uso de Estamina: ";
    private static final String PREFIX_GAIN_STAMINA = "Ganho de Estamina: ";
    private static final String PREFIX_RECOVER_STAMINA = "Recuperação de Estamina: ";
    private static final String PREFIX_CRITICAL_CHANCE = "Chance de Crítico: ";
    private static final String PREFIX_CRITICAL_DAMAGE = "Dano Crítico: ";
    private static final String PREFIX_FORTUNE_CHANCE = "Chance de Sorte: ";
    private static final String PREFIX_FORTUNE_PERCENT = "Percentual de Sorte: ";

    private final ItemStack stack;
    private final ItemMeta meta;

    public ItemLore(ItemStack stack) {
        this.stack = stack;
        this.meta = stack.getItemMeta();
    }

    public void addLore(ItemRanked tier) {
        List<Component> lore = new ArrayList<>();

        Component component = TypeController.generateComponent(stack).decoration(TextDecoration.ITALIC, false);
        Component tierComponent = Component.text(tier.getPrefix()).color(tier.getColor()).decoration(TextDecoration.ITALIC, false);

        ItemCustomizable rpgItem = ItemRPGUtils.getRPGItem(stack);
        List<String> indexLore = rpgItem.getLore();
        List<Component> loreComponent = new ArrayList<>();
        if (indexLore != null) {
            indexLore.forEach(s -> {
                loreComponent.add(Component.text(s.replace("&", "§")).decoration(TextDecoration.ITALIC, false));
            });
        }

        lore.add(component);
        lore.add(Component.empty());
        if (rpgItem.isLevel()) {
            lore.add(Component.text("§eRequer Nivel " + rpgItem.getRequiredLevel()).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.empty());
        }
        contains(stack, ItemSettingsCatalog.ATTACK_DAMAGE, PREFIX_ATTACK_DAMAGE, NamedTextColor.GRAY, "", "", lore::add);
        contains(stack, ItemSettingsCatalog.ATTACK_SPEED, PREFIX_ATTACK_SPEED, NamedTextColor.GRAY, "", "", lore::add);
        contains(stack, ItemSettingsCatalog.ATTACK_RECOVER, PREFIX_ATTACK_RECOVER, NamedTextColor.GRAY, "+", "%", lore::add);
        contains(stack, ItemSettingsCatalog.USE_HEALTH, PREFIX_USE_HEALTH, NamedTextColor.GRAY, "", "", lore::add);
        contains(stack, ItemSettingsCatalog.GAIN_HEALTH, PREFIX_GAIN_HEALTH, NamedTextColor.GRAY, "+", "", lore::add);
        contains(stack, ItemSettingsCatalog.USE_MANA, PREFIX_USE_MANA, NamedTextColor.GRAY, "", "", lore::add);
        contains(stack, ItemSettingsCatalog.GAIN_MANA, PREFIX_GAIN_MANA, NamedTextColor.GRAY, "+", "", lore::add);
        contains(stack, ItemSettingsCatalog.USE_STAMINA, PREFIX_USE_STAMINA, NamedTextColor.GRAY, "", "", lore::add);
        contains(stack, ItemSettingsCatalog.GAIN_STAMINA, PREFIX_GAIN_STAMINA, NamedTextColor.GRAY, "+", "", lore::add);
        contains(stack, ItemSettingsCatalog.RECOVER_STAMINA, PREFIX_RECOVER_STAMINA, NamedTextColor.GRAY, "+", "%", lore::add);
        contains(stack, ItemSettingsCatalog.CRITICAL_CHANCE, PREFIX_CRITICAL_CHANCE, NamedTextColor.RED, "+", "%", lore::add);
        contains(stack, ItemSettingsCatalog.CRITICAL_DAMAGE, PREFIX_CRITICAL_DAMAGE, NamedTextColor.RED, "+", "%", lore::add);
        contains(stack, ItemSettingsCatalog.FORTUNE_CHANCE, PREFIX_FORTUNE_CHANCE, NamedTextColor.BLUE, "+", "%", lore::add);
        contains(stack, ItemSettingsCatalog.FORTUNE_PERCENT, PREFIX_FORTUNE_PERCENT, NamedTextColor.BLUE, "+", "%", lore::add);
        lore.add(Component.empty());
        lore.addAll(loreComponent);
        if (!loreComponent.isEmpty()) {
            lore.add(Component.empty());
        }
        lore.add(tierComponent);

        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.lore(lore);
        stack.setItemMeta(meta);
    }

    public void addLoreNotRpgItem(ItemRanked tier) {
        List<Component> lore = new ArrayList<>();

        Component component = TypeController.generateComponent(stack).decoration(TextDecoration.ITALIC, false);
        Component tierComponent = Component.text(tier.getPrefix()).color(tier.getColor()).decoration(TextDecoration.ITALIC, false);

        lore.add(component);
        if (ItemTypeUtils.checkItemEqualsWeapon(stack.getType())) {
            lore.add(Component.empty());
            double damage = getDamageOfSword(stack);
            lore.add(Component.text("§7▶ " + PREFIX_ATTACK_DAMAGE + "§f" + damage).decoration(TextDecoration.ITALIC, false));

            double attackSpeed = getAttackSpeed(stack);
            lore.add(Component.text("§7▶ " + PREFIX_ATTACK_SPEED + "§f" + attackSpeed + "%").decoration(TextDecoration.ITALIC, false));
        }
        lore.add(Component.empty());
        lore.add(tierComponent);


        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.lore(lore);
        stack.setItemMeta(meta);
    }

    private void contains(ItemStack item, ItemSettingsCatalog catalog, String tag, NamedTextColor color, String prefix, String suffix, Consumer<Component> consumer) {
        switch (catalog.getType()) {
            case INT:

                if (!ItemRPGUtils.hasOptionInteger(item, catalog)) {
                    System.out.println("ItemLore: " + tag + " not found");
                    return;
                }

                int intSettings = ItemRPGUtils.getOptionInteger(item, catalog);
                if (intSettings > 0) {
                    consumer.accept(Component.text("§7▶ ").append(Component.text(tag).color(color)).append(Component.text("§f" + prefix + intSettings + suffix)).decoration(TextDecoration.ITALIC, false));
                }
                break;
            case DOUBLE:

                if (!ItemRPGUtils.hasOptionDouble(item, catalog)) {
                    System.out.println("ItemLore: " + tag + " not found");
                    return;
                }

                double doubleSettings = ItemRPGUtils.getOptionDouble(item, catalog);
                if (doubleSettings > 0) {
                    consumer.accept(Component.text("§7▶ ").append(Component.text(tag).color(color)).append(Component.text("§f" + prefix + doubleSettings + suffix)).decoration(TextDecoration.ITALIC, false));
                }
                break;
        }
    }


    private static double getDamageOfSword(ItemStack sword) {
        if (sword != null && sword.getType().name().contains("SWORD")) {
            Damageable damageable = (Damageable) sword.getItemMeta();
            return damageable.getDamage();
        }
        return 0.0;
    }

    private static double getAttackSpeed(ItemStack sword) {
        if (sword != null && sword.getType().name().contains("SWORD")) {
            ItemMeta meta = sword.getItemMeta();
            AttributeInstance attributeInstance = (AttributeInstance) meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED);
            return attributeInstance != null ? attributeInstance.getBaseValue() : 0.0;
        }
        return 0.0;
    }

}

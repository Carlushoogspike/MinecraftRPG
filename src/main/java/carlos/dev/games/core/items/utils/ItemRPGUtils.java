package carlos.dev.games.core.items.utils;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.items.type.ItemSettingsCatalog;
import carlos.dev.games.core.utils.PersistentDataContainerUtil;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class ItemRPGUtils {

    private static final String ITEM_ID = "item_id";
    private static final String TIER_ID = "item_tier";
    private static final String SETTINGS_ID = "item_settings";
    private static final String MODIFIER_ID = "item_modifier";
    private static final String NOT_LOAD_ITEM = "not_load";

    public static boolean isRPGItem(ItemStack stack) {
        return PersistentDataContainerUtil.hasString(stack, ITEM_ID);
    }

    public static ItemCustomizable getRPGItem(ItemStack stack) {
        String id = PersistentDataContainerUtil.getString(stack, ITEM_ID);
        return MinecraftRPG.getInstance().getLoaderManager().getItem(id);
    }

    public static void putNotLoad(ItemStack itemStack) {
        if (PersistentDataContainerUtil.hasString(itemStack, NOT_LOAD_ITEM)) return;
        PersistentDataContainerUtil.putString(NOT_LOAD_ITEM, "true", itemStack);
        System.out.println("putting");
    }

    public static boolean notLoad(ItemStack stack) {
        return PersistentDataContainerUtil.hasString(stack, NOT_LOAD_ITEM);
    }

    public static void putDefault(ItemCustomizable itemCustomizable, ItemStack stack) {
        if (PersistentDataContainerUtil.hasString(stack, ITEM_ID)) return;
        PersistentDataContainerUtil.putString(ITEM_ID, itemCustomizable.getId(), stack);
    }

    public static void putTier(ItemStack stack, ItemRanked tier) {
        if (PersistentDataContainerUtil.hasString(stack, TIER_ID)) return;
        PersistentDataContainerUtil.putString(TIER_ID, tier.getId(), stack);
    }

    public static void putOptionInteger(ItemStack stack, ItemSettingsCatalog reference, int value) {
        PersistentDataContainerUtil.putInteger(SETTINGS_ID + reference.getPath(), value, stack);
    }

    public static int getOptionInteger(ItemStack stack, ItemSettingsCatalog reference) {
        return PersistentDataContainerUtil.getInteger(stack, SETTINGS_ID + reference.getPath());
    }

    public static boolean hasOptionInteger(ItemStack stack, ItemSettingsCatalog reference) {
        return PersistentDataContainerUtil.hasInteger(stack, SETTINGS_ID + reference.getPath());
    }

    public static void putOptionDouble(ItemStack stack, ItemSettingsCatalog reference, double value) {
        PersistentDataContainerUtil.putDouble(SETTINGS_ID + reference.getPath(), value, stack);
    }

    public static double getOptionDouble(ItemStack stack, ItemSettingsCatalog reference) {
        return PersistentDataContainerUtil.getDouble(stack, SETTINGS_ID + reference.getPath());
    }

    public static boolean hasOptionDouble(ItemStack stack, ItemSettingsCatalog reference) {
        return PersistentDataContainerUtil.hasDouble(stack, SETTINGS_ID + reference.getPath());
    }

    public static void setDamage(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        /*AttributeModifier modifierTwo = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", amount * 0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifierTwo);*/
        itemStack.setItemMeta(itemMeta);
    }

    public static double getDamage(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null || !itemMeta.hasAttributeModifiers()) return 0;

        AtomicReference<Double> damage = new AtomicReference<>(0.0);

        itemMeta.getAttributeModifiers(EquipmentSlot.HAND).forEach((attribute, modifier) -> {
            if (attribute == Attribute.GENERIC_ATTACK_DAMAGE) {
                damage.set(modifier.getAmount());
            }
        });

        return damage.get();
    }


    public static void resetArmor(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.armor", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        AttributeModifier modifierTwo = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifierTwo);
        itemStack.setItemMeta(itemMeta);
    }

    public static void setAttackSpeed(ItemStack itemStack, float amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", amount, AttributeModifier.Operation.ADD_NUMBER);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        itemStack.setItemMeta(itemMeta);
    }

}

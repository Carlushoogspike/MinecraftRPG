package carlos.dev.games.core.items.utils;

import carlos.dev.games.core.items.ItemCustomizable;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.items.type.ItemSettingsCatalog;
import carlos.dev.games.core.utils.config.ConfigurationUtils;
import carlos.dev.games.core.utils.FilesType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ItemParserUtils {

    public static ItemCustomizable readYML(@NotNull FileConfiguration config) {
        String id = config.getString("base.id");
        String name = config.getString("base.name");
        Material material = Material.valueOf(config.getString("base.base"));

        ItemCustomizable.ItemCustomizableBuilder builder = ItemCustomizable.builder().id(id).name(name).base(material);

        ConfigurationUtils.contains(config, "base.breakble", FilesType.BOOLEAN, v -> builder.breakable((Boolean) v));
        ConfigurationUtils.contains(config, "base.max-durability", FilesType.INT, v -> builder.maxDurability((Integer) v));
        ConfigurationUtils.contains(config, "options.tired", FilesType.BOOLEAN, v -> builder.tired((Boolean) v));
        ConfigurationUtils.contains(config, "options.tier", FilesType.ITEM_TIER, v -> builder.tier((ItemRanked) v));
        ConfigurationUtils.contains(config, "options.leveled", FilesType.BOOLEAN, v -> builder.level((Boolean) v));
        ConfigurationUtils.contains(config, "options.level", FilesType.INT, v -> builder.requiredLevel((Integer) v));
        ConfigurationUtils.contains(config, "options.lore", FilesType.LIST, v -> builder.lore((List<String>) v));
        ConfigurationUtils.contains(config, "stats.use-health", FilesType.INT, v -> builder.useHealth((Integer) v));
        ConfigurationUtils.contains(config, "stats.gain-health", FilesType.INT, v -> builder.gainHealth((Integer) v));
        ConfigurationUtils.contains(config, "stats.use-mana", FilesType.INT, v -> builder.useMana((Integer) v));
        ConfigurationUtils.contains(config, "stats.gain-mana", FilesType.INT, v -> builder.gainMana((Integer) v));
        ConfigurationUtils.contains(config, "stats.use-stamina", FilesType.INT, v -> builder.useStamina((Integer) v));
        ConfigurationUtils.contains(config, "stats.gain-stamina", FilesType.INT, v -> builder.gainStamina((Integer) v));
        ConfigurationUtils.contains(config, "stats.recover-stamina", FilesType.INT, v -> builder.recoverStamina((Integer) v));
        ConfigurationUtils.contains(config, "stats.attack-damage", FilesType.INT, v -> builder.attackDamage((Integer) v));
        ConfigurationUtils.contains(config, "stats.attack-speed", FilesType.INT, v -> builder.attackSpeed((Integer) v));
        ConfigurationUtils.contains(config, "stats.attack-recover", FilesType.INT, v -> builder.attackRecover((Integer) v));
        ConfigurationUtils.contains(config, "stats.critical-chance", FilesType.DOUBLE, v -> builder.criticalChance((Double) v));
        ConfigurationUtils.contains(config, "stats.critical-damage", FilesType.DOUBLE, v -> builder.criticalDamage((Double) v));
        ConfigurationUtils.contains(config, "stats.fortune-chance", FilesType.DOUBLE, v -> builder.fortuneChance((Double) v));
        ConfigurationUtils.contains(config, "stats.fortune-percent", FilesType.DOUBLE, v -> builder.fortunePercent((Double) v));

        return builder.build();
    }


    public static ItemStack readRPGItem(ItemCustomizable itemCustomizable) {
        ItemStack stack = new ItemStack(itemCustomizable.getBase());
        ItemRPGUtils.putDefault(itemCustomizable, stack);
        ItemRPGUtils.putTier(stack, itemCustomizable.getTier());

        if (itemCustomizable.getAttackDamage() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.ATTACK_DAMAGE, itemCustomizable.getAttackDamage());
            ItemRPGUtils.setDamage(stack, itemCustomizable.getAttackDamage());
        }

        if (itemCustomizable.getAttackSpeed() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.ATTACK_SPEED, itemCustomizable.getAttackSpeed());
            ItemRPGUtils.setAttackSpeed(stack, itemCustomizable.getAttackSpeed());
        }

        if (itemCustomizable.getAttackRecover() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.ATTACK_RECOVER, itemCustomizable.getAttackRecover());
        }

        if (itemCustomizable.getUseHealth() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.USE_HEALTH, itemCustomizable.getUseHealth());
        }

        if (itemCustomizable.getGainHealth() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.GAIN_HEALTH, itemCustomizable.getGainHealth());
        }

        if (itemCustomizable.getUseMana() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.USE_MANA, itemCustomizable.getUseMana());
        }

        if (itemCustomizable.getGainMana() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.GAIN_MANA, itemCustomizable.getGainMana());
        }

        if (itemCustomizable.getUseStamina() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.USE_STAMINA, itemCustomizable.getUseStamina());
        }

        if (itemCustomizable.getGainStamina() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.GAIN_STAMINA, itemCustomizable.getGainStamina());
        }

        if (itemCustomizable.getRecoverStamina() != 0) {
            ItemRPGUtils.putOptionInteger(stack, ItemSettingsCatalog.RECOVER_STAMINA, itemCustomizable.getRecoverStamina());
        }

        if (itemCustomizable.getCriticalChance() != 0) {
            ItemRPGUtils.putOptionDouble(stack, ItemSettingsCatalog.CRITICAL_CHANCE, itemCustomizable.getCriticalChance());
        }

        if (itemCustomizable.getCriticalDamage() != 0) {
            ItemRPGUtils.putOptionDouble(stack, ItemSettingsCatalog.CRITICAL_DAMAGE, itemCustomizable.getCriticalDamage());
        }

        if (itemCustomizable.getFortuneChance() != 0) {
            ItemRPGUtils.putOptionDouble(stack, ItemSettingsCatalog.FORTUNE_CHANCE, itemCustomizable.getFortuneChance());
        }

        if (itemCustomizable.getFortunePercent() != 0) {
            ItemRPGUtils.putOptionDouble(stack, ItemSettingsCatalog.FORTUNE_PERCENT, itemCustomizable.getFortunePercent());
        }


        return stack;
    }

}

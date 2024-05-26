package carlos.dev.games.core.items.type;

import carlos.dev.games.core.items.utils.ItemTypeUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TypeController {

    public static Component generateComponent(ItemStack stack) {
        Material material = stack.getType();
        ItemType type = getItemType(material);
        return Component.text("§c" + type.getPrefix());
    }

    @NotNull
    private static ItemType getItemType(Material material) {
        if (ItemTypeUtils.checkItemEqualsArmor(material)) return ItemType.ARMOR;
        if (ItemTypeUtils.checkItemEqualsWeapon(material)) return ItemType.WEAPON;
        if (ItemTypeUtils.checkItemEqualsTool(material)) return ItemType.TOOL;
        if (material.isBlock()) return ItemType.BLOCK;
        //more...
        return ItemType.UNDEFINED;
    }

}

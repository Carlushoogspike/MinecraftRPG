package carlos.dev.games.core.items.utils;

import org.bukkit.Material;

public class ItemTypeUtils {

    public static boolean checkItemEqualsWeapon(Material material) {
        return switch (material) {
            case WOODEN_SWORD, STONE_SWORD, IRON_SWORD, DIAMOND_SWORD, GOLDEN_SWORD, NETHERITE_SWORD -> true;
            default -> false;
        };
    }


    public static boolean checkItemEqualsArmor(Material material) {
        return switch (material) {
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS, IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS, DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS, GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS, NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS, TURTLE_HELMET ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsHelmet(Material material) {
        return switch (material) {
            case LEATHER_HELMET, CHAINMAIL_HELMET, IRON_HELMET, DIAMOND_HELMET, GOLDEN_HELMET, NETHERITE_HELMET, TURTLE_HELMET ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsBoots(Material material) {
        return switch (material) {
            case LEATHER_BOOTS, CHAINMAIL_BOOTS, IRON_BOOTS, DIAMOND_BOOTS, GOLDEN_BOOTS, NETHERITE_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsLeggings(Material material) {
        return switch (material) {
            case LEATHER_LEGGINGS, CHAINMAIL_LEGGINGS, IRON_LEGGINGS, DIAMOND_LEGGINGS, GOLDEN_LEGGINGS, NETHERITE_LEGGINGS ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsChestplate(Material material) {
        return switch (material) {
            case LEATHER_CHESTPLATE, CHAINMAIL_CHESTPLATE, IRON_CHESTPLATE, DIAMOND_CHESTPLATE, GOLDEN_CHESTPLATE, NETHERITE_CHESTPLATE ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsPickaxe(Material material) {
        return switch (material) {
            case WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE, DIAMOND_PICKAXE, GOLDEN_PICKAXE, NETHERITE_PICKAXE ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsShovel(Material material) {
        return switch (material) {
            case WOODEN_SHOVEL, STONE_SHOVEL, IRON_SHOVEL, DIAMOND_SHOVEL, GOLDEN_SHOVEL, NETHERITE_SHOVEL -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsHoe(Material material) {
        return switch (material) {
            case WOODEN_HOE, STONE_HOE, IRON_HOE, DIAMOND_HOE, GOLDEN_HOE, NETHERITE_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsAxe(Material material) {
        return switch (material) {
            case WOODEN_AXE, STONE_AXE, IRON_AXE, DIAMOND_AXE, GOLDEN_AXE, NETHERITE_AXE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsNetheriteTools(Material material) {
        return switch (material) {
            case NETHERITE_AXE, NETHERITE_PICKAXE, NETHERITE_SHOVEL, NETHERITE_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsDiamondTools(Material material) {
        return switch (material) {
            case DIAMOND_AXE, DIAMOND_PICKAXE, DIAMOND_SHOVEL, DIAMOND_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsGoldTools(Material material) {
        return switch (material) {
            case GOLDEN_AXE, GOLDEN_PICKAXE, GOLDEN_SHOVEL, GOLDEN_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsIronTools(Material material) {
        return switch (material) {
            case IRON_AXE, IRON_PICKAXE, IRON_SHOVEL, IRON_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsWoodenTools(Material material) {
        return switch (material) {
            case WOODEN_AXE, WOODEN_PICKAXE, WOODEN_SHOVEL, WOODEN_HOE -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsNetheriteArmor(Material material) {
        return switch (material) {
            case NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsDiamondArmor(Material material) {
        return switch (material) {
            case DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsGoldArmor(Material material) {
        return switch (material) {
            case GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsIronArmor(Material material) {
        return switch (material) {
            case IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsLeatherArmor(Material material) {
        return switch (material) {
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS -> true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsNetheritePreSet(Material material) {
        return switch (material) {
            case NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS, NETHERITE_PICKAXE, NETHERITE_SHOVEL, NETHERITE_HOE, NETHERITE_AXE, NETHERITE_SWORD ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsDiamondPreSet(Material material) {
        return switch (material) {
            case DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS, DIAMOND_PICKAXE, DIAMOND_SHOVEL, DIAMOND_HOE, DIAMOND_AXE, DIAMOND_SWORD ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsGoldPreSet(Material material) {
        return switch (material) {
            case GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS, GOLDEN_PICKAXE, GOLDEN_SHOVEL, GOLDEN_HOE, GOLDEN_AXE, GOLDEN_SWORD ->
                    true;
            default -> false;
        };
    }


    public static boolean checkItemEqualsIronPreSet(Material material) {
        return switch (material) {
            case IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS, IRON_PICKAXE, IRON_SHOVEL, IRON_HOE, IRON_AXE, IRON_SWORD ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsChemicalArmor(Material material) {
        switch (material) {
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
            case CHAINMAIL_HELMET:
                return true;
            default:
                return false;
        }
    }

    public static boolean checkItemEqualsStoneTools(Material material) {
        return switch (material) {
            case STONE_AXE, STONE_PICKAXE, STONE_SHOVEL, STONE_HOE -> true;
            default -> false;
        };
    }

    public static boolean IsChainmailPreSet(Material material) {
        return switch (material) {
            case CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS, CHAINMAIL_HELMET, STONE_AXE, STONE_PICKAXE, STONE_SHOVEL, STONE_HOE, STONE_SWORD ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsTool(Material material) {
        return switch (material) {
            case WOODEN_AXE, WOODEN_PICKAXE, WOODEN_SHOVEL, WOODEN_HOE, STONE_AXE, STONE_PICKAXE, STONE_SHOVEL, STONE_HOE, IRON_AXE, IRON_PICKAXE, IRON_SHOVEL, IRON_HOE, DIAMOND_AXE, DIAMOND_PICKAXE, DIAMOND_SHOVEL, DIAMOND_HOE, GOLDEN_AXE, GOLDEN_PICKAXE, GOLDEN_SHOVEL, GOLDEN_HOE, NETHERITE_AXE, NETHERITE_PICKAXE, NETHERITE_SHOVEL, NETHERITE_HOE, WOODEN_SWORD, STONE_SWORD, IRON_SWORD, DIAMOND_SWORD, GOLDEN_SWORD, NETHERITE_SWORD, CROSSBOW, TRIDENT ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsBlock(Material material) {
        return material.isBlock();
    }

    public static boolean checkItemEqualsFood(Material material) {
        return switch (material) {
            case APPLE, BAKED_POTATO, BREAD, CARROT, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, MELON_SLICE, PUFFERFISH, PUMPKIN_PIE, RABBIT_STEW, BEEF, CHICKEN, COD, MUTTON, PORKCHOP, RABBIT, SALMON, TROPICAL_FISH ->
                    true;
            default -> false;
        };
    }

    public static boolean checkItemEqualsPotion(Material material) {
        return material == Material.POTION || material == Material.SPLASH_POTION || material == Material.LINGERING_POTION;
    }


}
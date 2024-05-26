package carlos.dev.games.core.items.utils;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.ItemRanked;
import carlos.dev.games.core.utils.PersistentDataContainerUtil;
import org.bukkit.inventory.ItemStack;

public class ItemTierUtils {

    private static final String TIER_ID = "item_tier";

    public static void loadItem(ItemStack stack) {
        if (PersistentDataContainerUtil.hasString(stack, TIER_ID)) return;

        ItemRanked tier = MinecraftRPG.getInstance().getTierManager().getDefaultTier();

        PersistentDataContainerUtil.putString(TIER_ID, tier.getId(), stack);
    }


    public static boolean isTired(ItemStack stack) {
        return PersistentDataContainerUtil.hasString(stack, TIER_ID);
    }

    public static ItemRanked getTierByStack(ItemStack stack) {
        String tierId = PersistentDataContainerUtil.getString(stack, TIER_ID);
        return MinecraftRPG.getInstance().getTierManager().get(tierId);
    }

}

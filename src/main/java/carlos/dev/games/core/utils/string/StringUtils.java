package carlos.dev.games.core.utils.string;

import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.core.meta.mob.util.MobUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import java.text.DecimalFormat;
import java.util.Objects;

public class StringUtils {

    public static Component generateNameBaseRPG(RPGMob rpgMob, LivingEntity monster){
        DecimalFormat formatter = new DecimalFormat("#.##");

        int level = MobUtils.getLevel(monster);
        String name = rpgMob.getBase().getName();

        double maximizedHealth = Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();

        String rawComponent = "§8[§7Lv" + level + "§8] §c" + name + " §a" + formatter.format(monster.getHealth()) + "/" + formatter.format(maximizedHealth);
        return Component.text(rawComponent).decoration(TextDecoration.ITALIC, false);
    }

    public static Component generateNameNatural(LivingEntity monster){
        DecimalFormat formatter = new DecimalFormat("#.##");

        int level = MobUtils.getLevel(monster);

        double maximizedHealth = Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
        String name = monster.getName();

        String rawComponent = "§8[§7Lv" + level + "§8] §7" + name + " §a" + formatter.format(monster.getHealth()) + "/" + formatter.format(maximizedHealth);
        return Component.text(rawComponent).decoration(TextDecoration.ITALIC, false);
    }
}

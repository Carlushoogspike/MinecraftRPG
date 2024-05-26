package carlos.dev.games.core.meta.mob.methods;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.mob.RPGMob;
import carlos.dev.games.api.mob.ModelMobLevelImpl;
import carlos.dev.games.core.meta.mob.util.MobUtils;
import carlos.dev.games.core.reloaders.MobManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.text.DecimalFormat;
import java.util.Objects;

public class MobMethods {

    public static void spawnMob(Location location, RPGMob rpgMob, int level) {
        EntityType type = rpgMob.getBase().getType();
        String name = rpgMob.getBase().getName();
        String id = rpgMob.getBase().getId();

        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, type);
        DecimalFormat formatter = new DecimalFormat("#.##");

        entity.setCustomNameVisible(true);
        entity.setInvisible(false);

        ModelMobLevelImpl leveledMob = rpgMob.getLevel(level);
        int mobLevel = leveledMob.getLevel();
        int speed = leveledMob.getSpeed();
        int strength = leveledMob.getStrength();
        double maxHealth = leveledMob.getMaxHealth();

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(strength);

        entity.setHealth(maxHealth);

        double maximizedHealth = Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();

        String rawComponent = "§8[§7Lv" + level + "§8] §c" + name + " §a" + formatter.format(entity.getHealth()) + "/" + formatter.format(maximizedHealth);
        entity.customName(Component.text(rawComponent));

        MobUtils.markIndexId(entity, id);
        MobUtils.markLevel(entity, mobLevel);
    }

    public static RPGMob getMob(String id){
        MobManager manager = MinecraftRPG.getInstance().getMobManager();
        return manager.get(id);
    }

}

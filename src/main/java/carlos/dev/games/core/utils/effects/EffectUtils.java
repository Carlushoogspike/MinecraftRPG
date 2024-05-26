package carlos.dev.games.core.utils.effects;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class EffectUtils {

    public static void sound(Player player, Sound sound){
        player.playSound(player.getLocation(), sound, 1F ,1F);
    }

    public static Vector getPullVector(Location from, Location to, double distance) {
        double dx = to.getX() - from.getX();
        double dy = to.getY() - from.getY();
        double dz = to.getZ() - from.getZ();

        double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
        dx /= length;
        dy /= length;
        dz /= length;

        dx *= distance;
        dy *= distance;
        dz *= distance;

        return new Vector(dx, dy, dz);
    }
}

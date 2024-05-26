package carlos.dev.games.core.utils.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParticleUtils {

    public static void HelixInBellow(Particle particle, Location center, int radius, int points){
        World world = center.getWorld();

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location newLoc = new Location(world, x, center.getY(), z);
            world.spawnParticle(particle, newLoc, 0, 0, 0, 0, 0);
        }
    }

    public static void FillHelixInBellow(Particle particle, Location center, double radius, int points, int particlesInside) {
        World world = center.getWorld();
        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location circlePoint = new Location(world, x, center.getY(), z);

            for (int j = 0; j < particlesInside; j++) {
                Vector randomOffset = new Vector(Math.random() * radius * 2 - radius, 0, Math.random() * radius * 2 - radius);
                Location insideLoc = circlePoint.clone().add(randomOffset);
                world.spawnParticle(particle, insideLoc, 0, 0, 0, 0, 0);
            }
        }
    }

    public static void LineToFrom(Particle particle, Location to, Location from, int points){
        World world = to.getWorld();

        double distance = to.distance(from);
        double length = distance / (points - 1);

        double xDiff = (to.getX() - from.getX()) / distance;
        double yDiff = (to.getY() - from.getY()) / distance;
        double zDiff = (to.getZ() - from.getZ()) / distance;

        for (int i = 0; i < points; i++) {
            double x = from.getX() + xDiff * length * i;
            double y = from.getY() + yDiff * length * i;
            double z = from.getZ() + zDiff * length * i;

            world.spawnParticle(particle, x, y, z, 0, 0, 0,0,1);
        }
    }

    public static void LineToFromDamage(Particle particle, Location to, Location from, int points, double damage) {
        World world = to.getWorld();

        double distance = to.distance(from);
        double length = distance / (points - 1);

        double xDiff = (to.getX() - from.getX()) / distance;
        double yDiff = (to.getY() - from.getY()) / distance;
        double zDiff = (to.getZ() - from.getZ()) / distance;

        for (int i = 0; i < points; i++) {
            double x = from.getX() + xDiff * length * i;
            double y = from.getY() + yDiff * length * i;
            double z = from.getZ() + zDiff * length * i;

            world.spawnParticle(particle, x, y, z, 0, 0, 0,0,1);

            Location currentLocation = new Location(world, x, y, z);
            for (Entity entity : world.getNearbyEntities(currentLocation, 1, 1, 1)) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(damage);
                }
            }
        }
    }

    public static void InfiniteLine(Particle particle, int points, List<Location> locations){
        if (locations.size() < 2) {
            throw new IllegalArgumentException("Pelo menos duas localizações devem ser fornecidas para criar uma linha.");
        }

        World world = locations.get(0).getWorld();

        for (int i = 0; i < locations.size() - 1; i++) {
            Location from = locations.get(i);
            Location to = locations.get(i + 1);

            double distance = from.distance(to);
            double length = distance / (points - 1);

            double xDiff = (to.getX() - from.getX()) / distance;
            double yDiff = (to.getY() - from.getY()) / distance;
            double zDiff = (to.getZ() - from.getZ()) / distance;

            for (int j = 0; j < points; j++) {
                double x = from.getX() + xDiff * length * j;
                double y = from.getY() + yDiff * length * j;
                double z = from.getZ() + zDiff * length * j;

                world.spawnParticle(particle, x, y, z, 0, 0, 0, 0, 1);
            }
        }
    }

    public static void Sphere(Particle particle, Location location){
        World world = location.getWorld();
        Location center = location.clone();

        for (double i = 0; i < Math.PI; i += Math.PI / 15){
            double radius = Math.sin(i);
            double y = Math.cos(i);

            for (double a = 0; a < Math.PI * 2;  a += Math.PI/ 7){
                    double x = Math.cos(a) * radius;
                    double z = Math.sin(a) * radius;
                    center.add(x, y, z);
                    world.spawnParticle(particle, center, 0);
                    center.subtract(x, y, z);
            }
        }
    }

    /*
    * COLORIZED PARTICLES
    * */

    public static void HelixInBellowColor(Particle particle, Particle.DustOptions dustOptions, Location center, int radius, int points){
        World world = center.getWorld();

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location newLoc = new Location(world, x, center.getY(), z);
            world.spawnParticle(particle, newLoc, 0, 0, 0, 0, 0, dustOptions);
        }
    }

    public static void FillHelixInBellowColor(Particle particle, Particle.DustOptions dustOptions, Location center, double radius, int points, int particlesInside) {
        World world = center.getWorld();
        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location circlePoint = new Location(world, x, center.getY(), z);

            for (int j = 0; j < particlesInside; j++) {
                Vector randomOffset = new Vector(Math.random() * radius * 2 - radius, 0, Math.random() * radius * 2 - radius);
                Location insideLoc = circlePoint.clone().add(randomOffset);
                world.spawnParticle(particle, insideLoc, 0, 0, 0, 0, 0, dustOptions);
            }
        }
    }

    public static void LineToFromColor(Particle particle, Particle.DustOptions dustOptions, Location to, Location from, int points){
        World world = to.getWorld();

        double distance = to.distance(from);
        double length = distance / (points - 1);

        double xDiff = (to.getX() - from.getX()) / distance;
        double yDiff = (to.getY() - from.getY()) / distance;
        double zDiff = (to.getZ() - from.getZ()) / distance;

        for (int i = 0; i < points; i++) {
            double x = from.getX() + xDiff * length * i;
            double y = from.getY() + yDiff * length * i;
            double z = from.getZ() + zDiff * length * i;

            world.spawnParticle(particle, x, y, z, 0, 0, 0,0,1, dustOptions);
        }
    }

    public static void LineToFromDamageColor(Particle particle, Particle.DustOptions dustOptions, Location to, Location from, int points, double damage) {
        World world = to.getWorld();

        double distance = to.distance(from);
        double length = distance / (points - 1);

        double xDiff = (to.getX() - from.getX()) / distance;
        double yDiff = (to.getY() - from.getY()) / distance;
        double zDiff = (to.getZ() - from.getZ()) / distance;

        for (int i = 0; i < points; i++) {
            double x = from.getX() + xDiff * length * i;
            double y = from.getY() + yDiff * length * i;
            double z = from.getZ() + zDiff * length * i;

            world.spawnParticle(particle, x, y, z, 0, 0, 0,0,1, dustOptions);

            Location currentLocation = new Location(world, x, y, z);
            for (Entity entity : world.getNearbyEntities(currentLocation, 1, 1, 1)) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(damage);
                }
            }
        }
    }

    public static void InfiniteLineColor(Particle particle, Particle.DustOptions dustOptions, int points, @NotNull List<Location> locations){
        if (locations.size() < 2) {
            throw new IllegalArgumentException("Pelo menos duas localizações devem ser fornecidas para criar uma linha.");
        }

        World world = locations.get(0).getWorld();

        for (int i = 0; i < locations.size() - 1; i++) {
            Location from = locations.get(i);
            Location to = locations.get(i + 1);

            double distance = from.distance(to);
            double length = distance / (points - 1);

            double xDiff = (to.getX() - from.getX()) / distance;
            double yDiff = (to.getY() - from.getY()) / distance;
            double zDiff = (to.getZ() - from.getZ()) / distance;

            for (int j = 0; j < points; j++) {
                double x = from.getX() + xDiff * length * j;
                double y = from.getY() + yDiff * length * j;
                double z = from.getZ() + zDiff * length * j;

                world.spawnParticle(particle, x, y, z, 0, 0, 0, 0, 1, dustOptions);
            }
        }
    }

}

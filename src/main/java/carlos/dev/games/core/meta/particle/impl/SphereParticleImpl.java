package carlos.dev.games.core.meta.particle.impl;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.meta.particle.ParticleBase;
import carlos.dev.games.api.particles.ParticleModel;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Random;

public class SphereParticleImpl extends ParticleModel {

    private final Particle base;
    private final Player player;
    private BukkitTask task;

    public SphereParticleImpl(Player player, Particle base) {
        super(player, base);
        this.player = player;
        this.base = base;
    }

    @Override
    public ParticleBase getBase() {
        return new ParticleBase("Esfera", "sphere", base);
    }
    @Override
    public void start() {

        int speed = 1;


        task = new BukkitRunnable(){

            double radius = 2;
            final double yOffset = 1;
            final int particles = 50;
            double radiusIncrease = 0;

            @Override
            public void run() {
                if (radiusIncrease != 0) {
                    radius += radiusIncrease;
                }

                final Location center = player.getLocation().clone();
                center.add(0, yOffset, 0);
                for (int i = 0; i < particles; i++) {
                    Vector vector = getRandomVector().multiply(radius);
                    center.add(vector);
                    center.getWorld().spawnParticle(base, center, 0,0,0,0);
                    center.subtract(vector);
                }
            }
        }.runTaskTimer(MinecraftRPG.getInstance(), 0, speed);

    }

    @Override
    public void stop() {
        if (task.isCancelled()) return;
        task.cancel();
    }

    public static final Random random = new Random(System.nanoTime());

    public static Vector getRandomVector() {
        double x, y, z;
        x = random.nextDouble() * 2 - 1;
        y = random.nextDouble() * 2 - 1;
        z = random.nextDouble() * 2 - 1;

        return new Vector(x, y, z).normalize();
    }
}

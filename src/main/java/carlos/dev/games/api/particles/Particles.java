package carlos.dev.games.api.particles;

import carlos.dev.games.core.meta.particle.ParticleBase;
import org.bukkit.Location;

public interface Particles {

    ParticleBase getBase();
    void start();
    void stop();

    default void display(Location location){
        location.getWorld().spawnParticle(getBase().getType(), location, 0);
    }
}

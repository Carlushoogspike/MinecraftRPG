package carlos.dev.games.api.particles;

import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@Getter
public abstract class ParticleModel implements Particles{

    private final Player player;
    private final Particle type;

    public ParticleModel(Player player, Particle type){
        this.player = player;
        this.type = type;
    }

}

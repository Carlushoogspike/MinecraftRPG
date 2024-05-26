package carlos.dev.games.core.meta.particle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Particle;

@Getter
@AllArgsConstructor
public class ParticleBase {

    private String name;
    private String id;
    private Particle type;

}

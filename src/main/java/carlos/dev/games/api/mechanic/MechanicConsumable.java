package carlos.dev.games.api.mechanic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MechanicConsumable {

    private String id, prefix;
    private int cooldown;
    private double health, mana, stamina;

}

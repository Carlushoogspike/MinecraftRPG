package carlos.dev.games.core.player.skill;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SkillAttributes {

    private double level, experience, experienceNeeded;

    public static SkillAttributes buildDefault() {
        return new SkillAttributes(1, 0, 200);
    }
}
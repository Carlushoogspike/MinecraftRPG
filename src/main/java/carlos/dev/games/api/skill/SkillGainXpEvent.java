package carlos.dev.games.api.skill;

import carlos.dev.games.core.player.skill.PlayerSkill;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class SkillGainXpEvent extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    private final Skill skill;
    private final PlayerSkill user;
    private final double earnedXp;

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}

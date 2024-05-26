package carlos.dev.games.core.player.skill;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.Skill;
import carlos.dev.games.api.skill.SkillGainXpEvent;
import carlos.dev.games.core.reloaders.base.SkillLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSkillUtility {

    private static Map<UUID, PlayerSkill> players;

    @NotNull
    public static PlayerSkill getData(@NotNull Player player){
        return players.computeIfAbsent(player.getUniqueId(), id -> PlayerSkill.builder().name(player.getName()).build());
    }

    public static void putPlayer(Player player){
        PlayerSkill rpgPlayer =  PlayerSkill.builder().name(player.getName()).build();

        if (players == null) players = new HashMap<>();
        if (players.containsKey(player.getUniqueId())) return;

        players.put(player.getUniqueId(), rpgPlayer);
    }

    private void xpSkill(Player player,String identifier, double xp) {
        SkillLoader loader = MinecraftRPG.getInstance().getSkillLoader();
        Skill skill = loader.getSkill(identifier);
        if (skill == null) return;

        PlayerSkill skillUser = getData(player);
        Bukkit.getServer().getPluginManager().callEvent(new SkillGainXpEvent(skill,skillUser, xp));
    }
}

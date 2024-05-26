package carlos.dev.games.core.player;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RPGPlayerUtility {

    private static Map<UUID, RPGPlayer> players;

    @NotNull
    public static RPGPlayer getData(@NotNull Player player){
        return players.computeIfAbsent(player.getUniqueId(), id -> new RPGPlayer(player));
    }

    public static void putPlayer(Player player){
        RPGPlayer rpgPlayer = new RPGPlayer(player);

        if (players == null) players = new HashMap<>();
        if (players.containsKey(player.getUniqueId())) return;

        players.put(player.getUniqueId(), rpgPlayer);
    }



}

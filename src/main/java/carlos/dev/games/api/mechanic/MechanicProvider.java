package carlos.dev.games.api.mechanic;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.items.type.ItemType;
import carlos.dev.games.core.player.RPGPlayer;
import carlos.dev.games.core.player.RPGPlayerUtility;
import carlos.dev.games.core.utils.actionbar.ActionBarManager;
import carlos.dev.games.core.utils.effects.EffectUtils;
import carlos.dev.games.core.utils.string.StringPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MechanicProvider {

    ItemType getType();
    MechanicConsumable getConsumable();
    void runAbility(Player player, Map<UUID, List<MechanicProvider>> map);

    default void defaultError(Player player){
        EffectUtils.sound(player, Sound.BLOCK_NOTE_BLOCK_BASS);
        player.sendMessage("§8Sem atributos suficiente para executar " + this.getConsumable().getPrefix());
    }

    default void startCooldown(Player player){
        MinecraftRPG.getInstance().getMechanicManager()
                .addMechanic(player, this);
    }

    default boolean nonHaveRequires(Player player){
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        MechanicConsumable consumable = this.getConsumable();

        return !(rpgPlayer.getHealth() >= consumable.getHealth()) || !(rpgPlayer.getStamina() >= consumable.getStamina())
                || !(rpgPlayer.getMana() >= consumable.getMana());
    };

    default void startMechanicAction(Player player, Map<UUID, List<MechanicProvider>> map, ActionBarManager barManager){
        subtractAttributes(player);
        Bukkit.getScheduler().runTaskLaterAsynchronously(MinecraftRPG.getInstance(), () -> {
            if (map.containsKey(player.getUniqueId())){
                List<MechanicProvider> megs = map.get(player.getUniqueId());
                megs.remove(this);
                Component msg = Component.text("§a✔ " + getConsumable().getPrefix()).color(TextColor.color(48, 223, 83));
                barManager.getData(player).switchWithTime(StringPosition.CENTER, msg , 5);
            }
        }, getConsumable().getCooldown() * 20L);
    }

    default void subtractAttributes(Player player){
        RPGPlayer rpgPlayer = RPGPlayerUtility.getData(player);
        MechanicConsumable consumable = this.getConsumable();

        if (consumable.getStamina() != 0 && rpgPlayer.getStamina() >= consumable.getStamina()){
            rpgPlayer.subtractStamina(consumable.getStamina());
            player.sendMessage(" - stamina " + consumable.getStamina());
        }

        if (consumable.getMana() != 0 && rpgPlayer.getMana() >= consumable.getMana()){
            rpgPlayer.subtractMana((int) consumable.getMana());
            player.sendMessage(" - mana " + consumable.getMana());
        }

        if (consumable.getHealth() != 0 && rpgPlayer.getHealth() >= consumable.getHealth()){
            rpgPlayer.subtractHealth((int) consumable.getHealth());
            player.sendMessage(" - health " + consumable.getHealth());

        }
    }
}

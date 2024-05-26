package carlos.dev.games.core.items;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;

import java.util.List;

@Data
@Builder
public class ItemCustomizable {

    private String id;

    private String name;
    private Material base;

    private boolean breakable;
    private int maxDurability;

    private boolean tired;
    private ItemRanked tier;

    private boolean level;
    private int requiredLevel;

    private List<String> lore;

    private int useHealth, gainHealth;
    private int useMana, gainMana;
    private int useStamina, gainStamina, recoverStamina;
    private int attackDamage, attackSpeed, attackRecover;
    private double criticalChance, criticalDamage;
    private double fortuneChance, fortunePercent;

    public boolean containsAttributes() {
        return useHealth != 0 || gainHealth != 0 ||
                useMana != 0 || gainMana != 0 ||
                useStamina != 0 || gainStamina != 0 || recoverStamina != 0 ||
                attackDamage != 0 || attackSpeed != 0 || attackRecover != 0 ||
                criticalChance != 0 || criticalDamage != 0 ||
                fortuneChance != 0 || fortunePercent != 0;
    }

}

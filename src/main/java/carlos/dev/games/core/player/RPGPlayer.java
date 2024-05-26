package carlos.dev.games.core.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RPGPlayer {

    private String name;
    private UUID uuid;
    private double health, maxHealth;
    private int mana, maxMana, defense;
    private double strength, attackSpeed, attackRange, criticalChance, criticalDamage, walkSpeed, fortuneChange, breakSpeed;
    private double stamina, maxStamina, staminaRegen, weight, arcane, maxArcane, experience;
    private int level;

    public RPGPlayer(FileConfiguration fileConfiguration){
        this.name = fileConfiguration.getString("name");
        this.uuid = UUID.fromString(Objects.requireNonNull(fileConfiguration.getString("uuid")));
        this.health = fileConfiguration.getDouble("health");
        this.maxHealth = fileConfiguration.getDouble("max-health");
        this.mana = fileConfiguration.getInt("mana");
        this.maxMana = fileConfiguration.getInt("max-mana");
        this.defense = fileConfiguration.getInt("defense");
        this.strength = fileConfiguration.getDouble("strength");
        this.attackSpeed = fileConfiguration.getDouble("attack-speed");
        this.attackRange = fileConfiguration.getDouble("attack-range");
        this.criticalChance = fileConfiguration.getDouble("critical-chance");
        this.criticalDamage = fileConfiguration.getDouble("critical-damage");
        this.walkSpeed = fileConfiguration.getDouble("walk-speed");
        this.fortuneChange = fileConfiguration.getDouble("fortune-change");
        this.stamina = fileConfiguration.getDouble("stamina");
        this.maxStamina = fileConfiguration.getDouble("max-stamina");
        this.staminaRegen = fileConfiguration.getDouble("stamina-regen");
        this.weight = fileConfiguration.getDouble("weight");
        this.arcane = fileConfiguration.getDouble("arcane");
        this.maxArcane = fileConfiguration.getDouble("max-arcane");
        this.experience = fileConfiguration.getDouble("experience");
        this.level = fileConfiguration.getInt("level");
        this.breakSpeed = fileConfiguration.getDouble("break-speed");
    }

    public RPGPlayer(@NotNull Player player){
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.health = player.getHealth();
        this.maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        this.mana = player.getFoodLevel();
        this.maxMana = player.getFoodLevel();
        this.defense = (int) player.getAbsorptionAmount();
        this.strength = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue();
        this.attackSpeed = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).getValue();
        this.attackRange = 0;
        this.criticalChance = 0;
        this.criticalDamage = 0;
        this.walkSpeed = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getValue();
        this.fortuneChange = 0;
        this.stamina = 0;
        this.maxStamina = 20;
        this.staminaRegen = 1;
    }

    public void addHealth(double health){
        this.health += health;
        if (this.health > this.maxHealth) this.health = this.maxHealth;
    }

    public void addMana(int mana){
        this.mana += mana;
        if (this.mana > this.maxMana) this.mana = this.maxMana;
    }

    public void addStamina(double stamina){
        this.stamina += stamina;
        if (this.stamina > this.maxStamina) this.stamina = this.maxStamina;
    }

    public void subtractStamina(double value){
        if (this.stamina >= value) {
            this.stamina -= value;
        }
    }

    public void subtractMana(double value){
        if (this.mana >= value) {
            this.mana -= (int) value;
        }
    }

    public void subtractHealth(double value){
        if (this.health >= value) {
            this.health -= value;
        }
    }

    public void addArcane(double arcane){
        this.arcane += arcane;
        if (this.arcane > this.maxArcane) this.arcane = this.maxArcane;
    }

    public void addMaxHealth(double health){
        this.maxHealth += health;
    }

    public void addMaxMana(int mana){
        this.maxMana += mana;
    }

    public void addMaxStamina(double stamina){
        this.maxStamina += stamina;
    }

    public void reduceMaxHealth(double value) {
        this.maxHealth -= value;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public void reduceMaxMana(int value) {
        this.maxMana -= value;
        if (this.mana > this.maxMana) {
            this.mana = this.maxMana;
        }
    }

    public void reduceMaxStamina(double value){
        this.maxStamina -= value;
        if(this.stamina > this.maxStamina){
            this.stamina = this.maxStamina;
        }
    }
}

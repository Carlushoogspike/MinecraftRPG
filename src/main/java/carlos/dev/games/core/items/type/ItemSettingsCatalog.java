package carlos.dev.games.core.items.type;

import carlos.dev.games.core.utils.FilesType;
import lombok.Getter;

@Getter
public enum ItemSettingsCatalog {

    REQUIRED_LEVEL("required_level", FilesType.INT),
    USE_HEALTH("use_health", FilesType.INT),
    USE_MANA("use_mana", FilesType.INT),
    USE_STAMINA("use_stamina", FilesType.INT),
    GAIN_HEALTH("gain_health", FilesType.INT),
    GAIN_MANA("gain_mana", FilesType.INT),
    GAIN_STAMINA("gain_stamina", FilesType.INT),
    RECOVER_STAMINA("recover_stamina", FilesType.INT),
    ATTACK_DAMAGE("attack_damage", FilesType.INT),
    ATTACK_SPEED("attack_speed", FilesType.INT),
    ATTACK_RECOVER("attack_recover", FilesType.INT),
    CRITICAL_CHANCE("critical_chance", FilesType.DOUBLE),
    CRITICAL_DAMAGE("critical_damage", FilesType.DOUBLE),
    FORTUNE_CHANCE("fortune_chance", FilesType.DOUBLE),
    FORTUNE_PERCENT("fortune_percent", FilesType.DOUBLE);


    private final String path;
    private final FilesType type;

    ItemSettingsCatalog(String path, FilesType type) {
        this.path = path;
        this.type = type;
    }

}

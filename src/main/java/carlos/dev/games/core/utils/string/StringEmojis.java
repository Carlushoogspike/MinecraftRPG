package carlos.dev.games.core.utils.string;

import lombok.Getter;

@Getter
public enum StringEmojis {

    MANA("✎"),
    HEALTH("❤"),
    STAMINA("✦");

    private final String prefix;

    StringEmojis(String prefix){
        this.prefix = prefix;
    }

}

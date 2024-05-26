package carlos.dev.games.core.meta.resource.triggers;

import carlos.dev.games.api.source.BaseTriggers;

public enum BlockTriggers implements BaseTriggers {
    PLACE("place"),
    BREAK("break");

    private final String identifier;

    BlockTriggers(String identifier){
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}

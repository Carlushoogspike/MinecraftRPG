package carlos.dev.games.api.source;

public interface ValueSource {

    String getType();

    double getXp();

    double getPrice();

    double getDropPercentage();

    int getRequiredLevel();

}
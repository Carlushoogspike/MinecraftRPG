package carlos.dev.games.core.utils.number;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class NumberParser {

    public Double tryParseDouble(@NotNull String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public boolean isInvalid(Double value) {
        return value == null || value.isNaN() || value.isInfinite() || value < 1.0;
    }
}
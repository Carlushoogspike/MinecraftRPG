package carlos.dev.games.core.utils.number;

public class Numerals {
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String toRoman(int value) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < VALUES.length; i++) {
            while (value >= VALUES[i]) {
                value -= VALUES[i];
                roman.append(SYMBOLS[i]);
            }
        }
        return roman.toString();
    }

    public static String toKMB(int value) {
        if (value < 1000) return Integer.toString(value);
        int exp = (int) (Math.log(value) / Math.log(1000));
        char pre = "kMB".charAt(exp-1);
        return String.format("%.1f%c", value / Math.pow(1000, exp), pre);
    }
}

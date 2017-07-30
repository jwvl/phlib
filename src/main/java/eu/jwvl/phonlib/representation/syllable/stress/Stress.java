package eu.jwvl.phonlib.representation.syllable.stress;

/**
 * Created by janwillem on 02/04/16.
 */
public enum Stress {
    NONE(""), SECONDARY("ˌ"), PRIMARY("ˈ"),PRE("<"),POST(">");
    final String symbol;

    Stress(String symbol) {
        this.symbol = symbol;
    }

    public static Stress fromChar(char input) {
        if (input == '\'') {
            return PRIMARY;
        } else if (input == 'ˌ') {
            return SECONDARY;
        } else {
            return NONE;
        }
    }

    @Override
    public String toString() {
        return symbol;
    }
}

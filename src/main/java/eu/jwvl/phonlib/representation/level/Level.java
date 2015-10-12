package eu.jwvl.phonlib.representation.level;

/**
 * Created by janwillem on 04/10/15.
 * A Level is a level of representation.
 * E.g. in standard OT the LORs are usually /Underlying Form/ and [Surface Form].
 */
public class Level {
    private final String name;
    private final String abbreviation;
    private final String leftBracket;
    private final String rightBracket;

    private Level(String name, String abbreviation, String leftBracket, String rightBracket) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.leftBracket = leftBracket;
        this.rightBracket = rightBracket;
    }

    public static Level deduceFromString(String inputString) {
        int stringLength = inputString.length();
        String leftBracket = inputString.substring(0,1);
        String rightBracket = inputString.substring(stringLength-1);
        String name = inputString.substring(1,stringLength-1);
        String[] splitName = name.split(" ");
        StringBuilder abbreviationBuilder = new StringBuilder();
        for (String s: splitName) {
            abbreviationBuilder.append(s.charAt(0));
        }
        String abbreviation = abbreviationBuilder.toString().toUpperCase();
        return new Level(name,abbreviation,leftBracket,rightBracket);
    }
}

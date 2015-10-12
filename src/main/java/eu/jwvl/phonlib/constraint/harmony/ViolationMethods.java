package eu.jwvl.phonlib.constraint.harmony;

/**
 * Created by janwillem on 04/10/15.
 */
public class ViolationMethods {
    public static int violationsFromString(String string) {
        if (string.isEmpty()) {
            return 0;
        }
        if (isInteger(string)) {
            return Integer.parseInt(string);
        } else return asteriskCount(string);
    }

    private static int asteriskCount(String string) {
        return string.length() - string.replace("*", "").length();
    }

    private static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

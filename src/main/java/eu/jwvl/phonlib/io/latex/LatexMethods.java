package eu.jwvl.phonlib.io.latex;

/**
 * Created by janwillem on 24/03/16.
 */
public class LatexMethods {
    public static String bracket(String original) {
        return new StringBuilder("{").append(original).append("}").toString();
    }

    public static String texCommand(String command, String toSurround) {
        return new StringBuilder("\\").append(command).append(bracket(toSurround)).toString();
    }

    public static String texCommand(String command) {
        return new StringBuilder("\\").append(command).toString();
    }


}

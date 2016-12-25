package eu.jwvl.phonlib.io;

/**
 * Created by janwillem on 14/07/16.
 */
public class Stringer {
    public static String writeNTimes(int n, String toWrite) {
        String result = "";
        for (int i=0; i < n; i++) {
            result+=toWrite;
        }
        return result;
    }
}

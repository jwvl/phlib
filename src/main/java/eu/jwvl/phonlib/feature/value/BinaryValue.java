package eu.jwvl.phonlib.feature.value;

/**
 * Created by janwillem on 30/03/16.
 */
public class BinaryValue implements ValueType {
    public static BinaryValue PLUS = new BinaryValue(true);
    public static BinaryValue MINUS = new BinaryValue(false);
    private final boolean value;

    public BinaryValue(boolean value) {
        this.value = value;
    }
}

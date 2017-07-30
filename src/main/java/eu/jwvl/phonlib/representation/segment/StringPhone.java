package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.encoding.ByteEncodable;

/**
 * Created by janwillem on 05/03/2017.
 */
public class StringPhone implements ByteEncodable {
    private final byte byteValue;
    private final String string;
    private static final byte idOffset = 0;
    private static final StringPhone[] list = new StringPhone[128+idOffset];
    private static byte idCounter = 0 - idOffset;

    public StringPhone(String string) {
        this.string = string;
        this.byteValue = idCounter++;
        list[byteValue-idOffset] = this;
    }


    @Override
    public byte getByteValue() {
        return byteValue;
    }

    public String toString() {
        return string;
    }
}

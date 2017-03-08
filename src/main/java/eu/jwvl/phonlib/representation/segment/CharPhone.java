package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.encoding.ByteEncodable;

/**
 * Created by janwillem on 05/03/2017.
 */
public class CharPhone implements ByteEncodable {
    private final byte byteValue;
    private final char c;
    private static final byte idOffset = 0;
    private static final CharPhone[] list = new CharPhone[128+idOffset];
    private static byte idCounter = 0 - idOffset;

    public CharPhone(char c) {
        this.c = c;
        this.byteValue = idCounter++;
        list[byteValue-idOffset] = this;
    }


    @Override
    public byte getByteValue() {
        return byteValue;
    }
}

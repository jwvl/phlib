package eu.jwvl.phonlib.representation.segment;

import java.util.Objects;

/**
 * Created by janwillem on 02/04/16.
 */
public class StringPhonologicalUnit implements PhonologicalUnit {
    private final String asString;
    private final Sonority sonority;
    private final byte id;

    public byte getId() {
        return id;
    }

    private static final byte idOffset = 127;
    private static byte idCounter = 0 - idOffset;
    private static StringPhonologicalUnit[] byteMap = new StringPhonologicalUnit[256];

    public Sonority getSonority() {
        return sonority;
    }

    public StringPhonologicalUnit(String asString, Sonority sonority) {
        this.asString = asString;
        this.sonority = sonority;
        this.id = idCounter++;
        byteMap[((int) this.id) + idOffset] = this;
    }

    /**
     * @param b
     * @return
     */
    public static StringPhonologicalUnit getByCode(byte b) {
        return byteMap[((int) b) + idOffset];
    }

    @Override
    public String getSymbol() {
        return asString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringPhonologicalUnit that = (StringPhonologicalUnit) o;
        return Objects.equals(asString, that.asString) &&
                sonority == that.sonority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(asString, sonority);
    }

    @Override
    public String toString() {
        return asString;
    }


    public static byte[] encode(String line) {
        return StringPhoneReader.parseSequence(line).toByteArray();
    }

    public static byte[] encode(StringPhonologicalUnit[] elements) {
        byte[] result = new byte[elements.length];
        for (int i=0; i < elements.length; i++) {
            result[i] = elements[i].getId();
        }
        return result;
    }
}

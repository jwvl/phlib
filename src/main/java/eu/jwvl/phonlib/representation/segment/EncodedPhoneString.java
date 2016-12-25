package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.representation.structure.PhonemicForm;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by janwillem on 02/04/16.
 */
public class EncodedPhoneString implements PhonemicForm {
    private final byte[] contents;

    public byte[] contents() {
        return contents;
    }

    private EncodedPhoneString(byte[] contents) {
        this.contents = contents;
    }

    public static EncodedPhoneString create(StringPhonologicalUnit... units) {
        int counter = 0;
        byte[] contents = new byte[units.length];
        for (StringPhonologicalUnit unit: units) {
            contents[counter++] = unit.getId();
        }
        return new EncodedPhoneString(contents);
    }

    public static EncodedPhoneString create(Collection<StringPhonologicalUnit> units) {
        int counter = 0;
        byte[] contents = new byte[units.size()];
        for (StringPhonologicalUnit unit: units) {
            contents[counter++] = unit.getId();
        }
        return new EncodedPhoneString(contents);
    }

    public byte[] toByteArray() {
        return contents;
    }

    @Override
    public Iterator<PhonologicalUnit> iterator() {
        Iterator<PhonologicalUnit> it = new Iterator<PhonologicalUnit>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < contents.length;
            }

            @Override
            public PhonologicalUnit next() {
                return StringPhonologicalUnit.getByCode(contents[currentIndex++]);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public int length() {
        return contents.length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EncodedPhoneString{");
        for (byte b: contents) {
            sb.append(StringPhonologicalUnit.getByCode(b));
        }
        sb.append('}');
        return sb.toString();
    }

    public StringPhonologicalUnit[] decodeToArray() {
        return decodeToArray(contents);
    }

    public static StringPhonologicalUnit[] decodeToArray(byte[] contents) {
        StringPhonologicalUnit[] result = new StringPhonologicalUnit[contents.length];
        for (int i=0; i < contents.length; i++) {
            result[i] = StringPhonologicalUnit.getByCode(contents[i]);
        }
        return result;
    }

    public static String toString(byte[] elements) {
        StringBuilder result = new StringBuilder();
        for (byte b: elements) {
            result.append(StringPhonologicalUnit.getByCode(b).toString());
        }
        return result.toString();
    }
}

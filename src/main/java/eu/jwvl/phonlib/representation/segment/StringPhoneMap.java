package eu.jwvl.phonlib.representation.segment;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by janwillem on 07/04/2017.
 */
public class StringPhoneMap {
    private final StringPhone[] items;
    private Map<String,StringPhone> stringToPhones;
    private int size;

    public StringPhoneMap() {
        this.items = new StringPhone[256];
        this.stringToPhones = Maps.newHashMap();
        size = 0;
    }

    public StringPhone createAndAdd(String string) {
        StringPhone stringPhone = new StringPhone(string);
        items[stringPhone.getByteValue()] = stringPhone;
        stringToPhones.put(string,stringPhone);
        size++;
        return stringPhone;
    }

    public StringPhone getByIndex(byte index) {
        return items[index];
    }

    public int size() {
        return size;
    }

    public byte[] encodeAsBytes(String string) {
        char[] chars = string.toCharArray();
        byte[] result = new byte[chars.length];
        for (int i=0; i < chars.length; i++) {
            StringPhone phone = stringToPhones.get(String.valueOf(chars[i]));
            if (phone == null) {
                result[i] = 0;
            } else {
                result[i] = phone.getByteValue();
            }
        }
        return result;

    }

    public StringPhone getPhone(String charString) {
        StringPhone result = stringToPhones.get(charString);
        if (result == null) {
            return items[0];
        }
        return result;
    }

    public String bytesToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(items[b]);
        }
        return builder.toString();
    }

    public String bytesToString(byte[] bytes, int startAt) {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < bytes.length; i++) {
            int actualPos = startAt+i % bytes.length;
            builder.append(items[bytes[actualPos]]);
        }
        return builder.toString();
    }
}

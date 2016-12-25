package eu.jwvl.phonlib.symbol.encoding;

import eu.jwvl.phonlib.representation.segment.Phone;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by janwillem on 20/06/16.
 */
public class ByteCoder {
    private final Phone[] byteToPhone;
    private final Map<Phone,Byte> phoneToByte;

    public ByteCoder(Phone[] phoneList) {
        this.byteToPhone = phoneList;
        this.phoneToByte = new HashMap<Phone,Byte>();
        for (byte i=0; i < phoneList.length; i++) {
            phoneToByte.put(phoneList[i],i);
        }
    }

    public byte[] encode(Phone[] phones) {
        byte[] result = new byte[phones.length];
        for (int i=0; i < result.length; i++) {
            result[i] = phoneToByte.get(phones[i]);
        }
        return result;
    }

    public Phone[] decode(byte[] bytes) {
        Phone[] result = new Phone[bytes.length];
        for (int i=0; i < result.length; i++) {
            byte index = bytes[i];
            result[i] = byteToPhone[index];
        }
        return result;
    }

    public int getNumPhones() {
        return byteToPhone.length;
    }
}

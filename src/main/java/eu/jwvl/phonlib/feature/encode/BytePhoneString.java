package eu.jwvl.phonlib.feature.encode;

/**
 * Created by janwillem on 15/04/2017.
 */
public class BytePhoneString {
    public final byte[] contents;
    public final int postLength;
    public final int preLength;

    public BytePhoneString(byte[] contents, int postLength, int preLength) {
        this.contents = contents;
        this.postLength = postLength;
        this.preLength = preLength;
    }

}

package eu.jwvl.phonlib.representation.segment;

/**
 * Created by janwillem on 05/03/2017.
 */
public class ByteToBooleanArray {
    boolean[][] arrays;

    public ByteToBooleanArray() {
        arrays = new boolean[256][];
    }

    public void setArray(byte index, boolean[] array) {
        arrays[index] = array;
    }

    public boolean[] getArray(byte index) {
        return arrays[index];
    }
}

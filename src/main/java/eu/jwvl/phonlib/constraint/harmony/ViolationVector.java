package eu.jwvl.phonlib.constraint.harmony;

/**
 * Created by janwillem on 04/10/15.
 */
public interface ViolationVector {
    public byte[] toArray();
    public byte getViolationsAtIndex(int index);
    public int getLeftmostViolationIndex();
    public int size();
    public String printAsDigits();
}

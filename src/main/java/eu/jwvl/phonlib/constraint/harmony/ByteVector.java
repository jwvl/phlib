package eu.jwvl.phonlib.constraint.harmony;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;

/**
 * Created by janwillem on 04/10/15.
 */
public class ByteVector implements ViolationVector {
    private final byte[] contents;

    public ByteVector(byte[] contents) {
        this.contents = contents;
    }

    @Override
    public byte[] toArray() {
        return contents;
    }

    @Override
    public byte getViolationsAtIndex(int index) {
        return contents[index];
    }

    @Override
    public int getLeftmostViolationIndex() {
        for (int i=0; i < size(); i++ ) {
            if (contents[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return contents.length;
    }

    public static ByteVector fromCandidateAndConstraints(Candidate candidate, Constraint[] constraintList) {
        byte[] result = new byte[constraintList.length];
        for (int i=0; i < result.length; i++) {
            result[i] = (byte) (constraintList[i].getNumViolations(candidate));
        }
        return new ByteVector(result);
    }
}

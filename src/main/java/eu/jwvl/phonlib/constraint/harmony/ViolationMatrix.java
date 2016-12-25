package eu.jwvl.phonlib.constraint.harmony;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;

import java.util.Arrays;

/**
 * Created by janwillem on 12/10/15.
 */
public class ViolationMatrix {
    private final ViolationVector[] violationVectors;
    private int[] minViolations;
    private int[] fatalViolationIndices;

    public ViolationMatrix(Constraint[] constraints, Candidate[] candidates) {
        violationVectors = new ViolationVector[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            violationVectors[i] = ByteVector.fromCandidateAndConstraints(candidates[i], constraints);
        }
        minViolations = new int[constraints.length];
        fatalViolationIndices = new int[candidates.length];
        Arrays.fill(fatalViolationIndices, width());
        fillMatrix();
    }

    private void fillMatrix() {
        boolean[] eliminated = new boolean[height()];
        int numActive = height();
        int col = 0;
        while (numActive > 1 && col < width()) {
            int minViolation = Integer.MAX_VALUE;
            for (int row = 0; row < height(); row++) {
                //1st loop: find minimum
                if (!eliminated[row]) {
                    int currViolation = violationVectors[row].getViolationsAtIndex(col);
                    minViolation = Math.min(currViolation, minViolation);
                }
            }
            for (int row = 0; row < height(); row++) {
                int currViolation = violationVectors[row].getViolationsAtIndex(col);
                if (!eliminated[row] && currViolation > minViolation) {
                    fatalViolationIndices[row] = col;
                    eliminated[row] = true;
                }
            }
            minViolations[col] = minViolation;
            col++;
        }

    }

    public boolean isGreyedOut(int vectorIndex, int constraintIndex) {
        return (constraintIndex > fatalViolationIndices[vectorIndex]);
    }

    public int width() {
        return violationVectors[0].size();
    }

    private int height() {
        return violationVectors.length;
    }

    public int[] getFatalViolationIndices() {
        return fatalViolationIndices;
    }

    public ViolationVector[] getViolationVectors() {
        return violationVectors;
    }

    public String violationsToString(int vectorIndex, int constraintIndex) {
        StringBuilder builder = new StringBuilder();
        ViolationVector vector = violationVectors[vectorIndex];
        int numViolations = vector.getViolationsAtIndex(constraintIndex);
        int minViolationAt = minViolations[constraintIndex];
        boolean hasFatalViolation = (fatalViolationIndices[vectorIndex] == constraintIndex);
        for (int i=0; i < numViolations; i++) {
            builder.append("*");
            if (hasFatalViolation && i == minViolationAt) {
                builder.append("!");
            }
        }
        return builder.toString();
    }
}

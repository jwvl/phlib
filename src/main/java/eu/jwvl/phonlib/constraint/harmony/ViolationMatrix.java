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
        violationVectors = new ViolationVector[constraints.length];
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

    private int width() {
        return violationVectors[0].size();
    }

    private int height() {
        return violationVectors.length;
    }

    public int[] getFatalViolationIndices() {
        return fatalViolationIndices;
    }
}

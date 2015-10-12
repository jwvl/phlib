package eu.jwvl.phonlib.constraint.harmony;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;

/**
 * Created by janwillem on 12/10/15.
 */
public class ViolationMatrix {
    private final ViolationVector[] violationVectors;
    private int[] minViolations;
    private int[] fatalViolationIndices;

    public ViolationMatrix(Constraint[] constraints, Candidate[] candidates) {
        violationVectors = new ViolationVector[constraints.length];
        for (int i=0; i < candidates.length; i++) {
            violationVectors[i] = ByteVector.fromCandidateAndConstraints(candidates[i], constraints);
        }
        minViolations = new int[constraints.length];
        fatalViolationIndices = new int[candidates.length];

    }
}

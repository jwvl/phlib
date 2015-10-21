package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.candidate.SimpleCandidate;
import eu.jwvl.phonlib.constraint.harmony.ByteVector;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.constraint.hierarchy.ListingConstraint;

/**
 * Created by janwillem on 03/10/15.
 */
public class SimpleTableau implements Tableau<String,String> {
    private final String input;
    private final SimpleCandidate[] candidates;
    private final ListingConstraint[] constraints;
    private final ViolationVector[] violationVectors;
    private final SimpleCandidate[] winners;

    public SimpleTableau(String input, SimpleCandidate[] candidates, ListingConstraint[] constraints) {
        this.input = input;
        this.candidates = candidates;
        this.constraints = constraints;
        this.violationVectors = constructViolationVectors();
        winners = calculateWinners();
    }

    private SimpleCandidate[] calculateWinners() {
        return new SimpleCandidate[0];
    }


    private ViolationVector[] constructViolationVectors() {
        ViolationVector[] result = new ViolationVector[getNumCandidates()];
        for (int i=0; i < getNumCandidates(); i++) {
            Candidate iCandidate = candidates[i];
            byte[] byteArray = new byte[getNumConstraints()];
            for (int j=0; j < getNumConstraints(); i++) {
                byteArray[j] = (byte) constraints[j].getNumViolations(iCandidate);
            }
            result[i] = new ByteVector(byteArray);
        }
        return result;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public Candidate<String,String>[] getCandidates() {
        return candidates;
    }

    @Override
    public Constraint[] getConstraints() {
        return constraints;
    }

    @Override
    public ViolationVector[] getViolationVectors() {
        return violationVectors;
    }

    @Override
    public Candidate[] getWinners() {
        return new Candidate[0];
    }

    public int getNumCandidates() {
        return candidates.length;
    }

    public int getNumConstraints() {
        return constraints.length;
    }


}

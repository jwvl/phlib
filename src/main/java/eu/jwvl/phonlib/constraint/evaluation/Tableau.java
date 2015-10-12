package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Tableau<I,O> {
    public I getInput();
    public Candidate<I,O>[] getCandidates();
    public Constraint[] getConstraints();
    public ViolationVector[] getViolationVectors();
    public Candidate[] getWinners();
}

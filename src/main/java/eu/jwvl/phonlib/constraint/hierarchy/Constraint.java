package eu.jwvl.phonlib.constraint.hierarchy;

import eu.jwvl.phonlib.constraint.candidate.Candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Constraint {
    public String getName();
    public int getNumViolations(Candidate candidate);
}

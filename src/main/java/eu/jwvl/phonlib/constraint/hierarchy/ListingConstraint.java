package eu.jwvl.phonlib.constraint.hierarchy;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import eu.jwvl.phonlib.constraint.candidate.Candidate;

/**
 * Created by janwillem on 03/10/15.
 * A ListingConstraint simply contains a multiset of Candidates
 */
public class ListingConstraint implements Constraint {
    private final String name;
    private Multiset<Candidate> candidatesWithViolations;

    private ListingConstraint(String name) {
        this.name = name;
        this.candidatesWithViolations = HashMultiset.create();
    }

    public static ListingConstraint createFromString(String name) {
        return new ListingConstraint(name);
    }

    public void addCandidateWithViolations(Candidate candidate, int numViolations) {
        this.candidatesWithViolations.add(candidate,numViolations);
    }

    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    @Override
    public int getNumViolations(Candidate candidate) {
        return candidatesWithViolations.count(candidate);
    }
}

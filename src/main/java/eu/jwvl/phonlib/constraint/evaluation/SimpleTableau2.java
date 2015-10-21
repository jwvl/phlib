package eu.jwvl.phonlib.constraint.evaluation;

import com.google.common.collect.ImmutableList;
import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.candidate.SimpleCandidate;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.constraint.hierarchy.ConstraintOrderingImpl;
import eu.jwvl.phonlib.constraint.hierarchy.ListingConstraint;

import java.util.Collection;
import java.util.List;

/**
 * Created by janwillem on 03/10/15.
 */
public class SimpleTableau2 implements Tableau<String,String> {
    private final String input;
    private final EvaluationFunction evaluation;
    private final SimpleCandidate[] candidates;
    private final ListingConstraint[] constraints;
    private SimpleCandidate[] winners;

    public SimpleTableau2(String input, SimpleCandidate[] candidates, ListingConstraint[] constraints) {
        this.input = input;
        this.candidates = candidates;
        this.constraints = constraints;
        ConstraintOrderingImpl constraintOrdering = new ConstraintOrderingImpl(constraints);
        List<Candidate> candidatesAsList = ImmutableList.copyOf(candidates);
        evaluation = new EvaluationByElimination(constraintOrdering,candidatesAsList);
        Collection<Candidate> winningCandidates = evaluation.evaluate();
        SimpleCandidate[] winners = new SimpleCandidate[winningCandidates.size()];
        int count = 0;
        for (Candidate c: winningCandidates) {
            winners[count++] = c.toSimpleCandidate();
        }
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
    public Candidate[] getWinners() {
        return winners;
    }

    public int getNumCandidates() {
        return candidates.length;
    }

    public int getNumConstraints() {
        return constraints.length;
    }


}

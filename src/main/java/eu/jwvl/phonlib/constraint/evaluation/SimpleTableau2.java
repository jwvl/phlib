package eu.jwvl.phonlib.constraint.evaluation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.candidate.SimpleCandidate;
import eu.jwvl.phonlib.constraint.harmony.ViolationMatrix;
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
        List<Candidate> candidatesAsList = Lists.newArrayList();
        for (SimpleCandidate simpleCandidate: candidates) {
            candidatesAsList.add(simpleCandidate);
        }
        evaluation = new EvaluationByElimination(constraintOrdering,candidatesAsList);
        Collection<Candidate> winningCandidates = evaluation.evaluate();
        winners = new SimpleCandidate[winningCandidates.size()];
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
    public ViolationVector[] getViolationVectors() {
        return ((EvaluationByElimination)evaluation).getViolationMatrix().getViolationVectors();
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

    public String toString(String separator) {
        ViolationMatrix matrix = ((EvaluationByElimination)evaluation).getViolationMatrix();
        StringBuilder result = new StringBuilder();
        result.append(input);
        for (Constraint c: getConstraints()) {
            result.append(separator);
            result.append(c);
        }

        for (int i=0; i < getNumCandidates(); i++) {
            result.append("\n");
            Candidate<String, String> candidate = getCandidates()[i];
            result.append(candidate);
            for (int j = 0; j < matrix.width(); j++) {
                result.append(separator);
                result.append(matrix.violationsToString(i, j));
            }
        }
        return result.toString();

    }

    public String toString() {
        return toString("\t");
    }


}

package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Tableau<I, O> {
    I getInput();

    Candidate[] getCandidates();

    Constraint[] getConstraints();

    ViolationVector[] getViolationVectors();

    Candidate[] getWinners();

    boolean isWinner(Candidate candidate);

    EvaluationFunction getEvaluationFunction();

    double[] getRankingValues();
}

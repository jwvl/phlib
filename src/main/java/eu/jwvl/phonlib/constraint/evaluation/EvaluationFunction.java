package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.Candidate;

import java.util.Collection;

/**
 * Created by janwillem on 12/10/15.
 */
public interface EvaluationFunction {
    public Collection<Candidate> evaluate();

}

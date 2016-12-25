package eu.jwvl.phonlib.constraint.grammar;

import eu.jwvl.phonlib.constraint.evaluation.EvaluationFunction;

/**
 * Created by janwillem on 24/03/16.
 */
public interface Grammar {
    EvaluationFunction getEvaluationFunction();
}

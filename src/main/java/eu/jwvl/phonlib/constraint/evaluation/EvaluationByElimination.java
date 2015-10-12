package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.harmony.ByteVector;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.constraint.hierarchy.ConstraintOrdering;

import java.util.Collection;

/**
 * Standard 'tableau' evaluation method.
 * Created by janwillem on 12/10/15.
 */
public class EvaluationByElimination implements EvaluationFunction {
    private final Constraint[] constraintList;
    private final Candidate[] candidateList;

    public EvaluationByElimination(ConstraintOrdering constraintList, Collection<Candidate> candidates) {
        this.constraintList = constraintList.toArray();
        candidateList = new Candidate[candidates.size()];
        int counter = 0;
        for (Candidate c: candidates) {
            candidateList[counter++] = c;
        }
        violationVectors = calculateViolationVectors();
    }

    private ViolationVector[] calculateViolationVectors() {

    }

    @Override
    public Collection<Candidate> evaluate() {

    }
}

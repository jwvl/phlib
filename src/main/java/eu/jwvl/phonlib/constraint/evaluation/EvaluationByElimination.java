package eu.jwvl.phonlib.constraint.evaluation;

import com.google.common.collect.Sets;
import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.harmony.ViolationMatrix;
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
    private final ViolationMatrix violationMatrix;

    public EvaluationByElimination(ConstraintOrdering constraintOrdering, Collection<Candidate> candidates) {
        this.constraintList = constraintOrdering.toArray();
        candidateList = new Candidate[candidates.size()];
        int counter = 0;
        for (Candidate c: candidates) {
            candidateList[counter++] = c;
        }
        violationMatrix = new ViolationMatrix(constraintList, candidateList);
    }

    @Override
    public Collection<Candidate> evaluate() {
        Collection<Candidate> result = Sets.newHashSet();
        for (int i=0; i < candidateList.length; i++) {
            Candidate current = candidateList[i];
            int fatallyViolatedAt = violationMatrix.getFatalViolationIndices()[i];
            if (fatallyViolatedAt >= constraintList.length) {
                result.add(current);
            }
        }
        return result;
    }

    public ViolationMatrix getViolationMatrix() {
        return violationMatrix;
    }
}

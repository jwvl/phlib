package eu.jwvl.phonlib.io.latex;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.evaluation.EvaluationByElimination;
import eu.jwvl.phonlib.constraint.evaluation.EvaluationFunction;
import eu.jwvl.phonlib.constraint.evaluation.Tableau;
import eu.jwvl.phonlib.constraint.harmony.ViolationMatrix;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;

/**
 * Created by janwillem on 24/03/16.
 */
public class LatexOtTablxWriter implements TableauWriter {

    @Override
    public String write(Tableau tableau) {


        EvaluationFunction evaluationFunction = tableau.getEvaluationFunction();
        ViolationMatrix matrix = ((EvaluationByElimination) evaluationFunction).getViolationMatrix();
        StringBuilder result = new StringBuilder();
        Constraint[] constraints = tableau.getConstraints();
        int numConstraints = constraints.length;
        result.append(LatexMethods.texCommand("begin","OTTableau"));
        result.append(LatexMethods.bracket(String.valueOf(numConstraints)));
        result.append(getConstraintColumnCommand(constraints.length));
        result.append("\n");
        result.append("\\inp{").append(tableau.getInput()).append("}");

        for (Constraint constraint : constraints) {
            result.append("\t").append("\\const{").append(constraint.getName() + "}");
        }
        result.append("\n");
        Candidate[] candidates = tableau.getCandidates();

        for (int i = 0; i < candidates.length; i++) {

            Candidate candidate = tableau.getCandidates()[i];
            result.append("\\cand");
            if (tableau.isWinner(candidate)) {
                result.append("[\\Optimal]");
            }
            result.append(getCandidateString(candidate));
            for (int j = 0; j < matrix.width(); j++) {
                result.append("\t");
                result.append(getCellContents(matrix, i, j));
            }
            result.append("\n");
        }
        result.append("\\end{OTtableau}");
        return result.toString();
    }

    private String getCandidateString(Candidate candidate) {
        return "{" + candidate.getOutputAsString() + "}";
    }

    private static String getCellContents(ViolationMatrix matrix, int i, int j) {
        StringBuilder result = new StringBuilder("\\vio{");
        result.append(matrix.violationsToString(i, j));
        result.append("}");
        return result.toString();
    }

    private static String getConstraintColumnCommand(int numConstraints) {
        StringBuilder result = new StringBuilder("{c");
        for (int i = 0; i < numConstraints - 1; i++) {
            result.append("|c");
        }
        result.append("}");
        return result.toString();
    }
}

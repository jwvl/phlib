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
public class LatexOtTableauWriter extends LatexTableauWriter {

    public LatexOtTableauWriter(boolean shadeCells) {
        super(new LatexStringBuilder(),shadeCells);
    }

    public String write(Tableau tableau) {

        ltx.clear();

        StringBuilder result = new StringBuilder();
        Constraint[] constraints = tableau.getConstraints();
        if (shadeCells) {
            ltx.command("ShadingOn").newLine();
        }
        writeTopRow(tableau, constraints);
        ltx.newLine();

        ltx.command("end","tableau");
        return ltx.toString();
    }

    private String getCandidateString(Candidate candidate) {
        return "{"+candidate.getOutputAsString()+"}";
    }

    private static String getCellContents(ViolationMatrix matrix, int i, int j) {
        StringBuilder result = new StringBuilder("\\vio{");
        result.append(matrix.violationsToString(i,j));
        result.append("}");
        return result.toString();
    }

    private static String getConstraintColumnCommand(int numConstraints) {
        StringBuilder result = new StringBuilder("{c");
        for (int i=0; i < numConstraints-1; i++) {
            result.append("|c");
        }
        result.append("}");
        return result.toString();
    }


    @Override
    public void writeTopRow(Tableau tableau, Constraint[] constraints) {
        ltx.command("begin", "tableau");
        ltx.append(getConstraintColumnCommand(constraints.length));
        ltx.newLine();
        ltx.command("inp",tableau.getInput().toString());
        for (Constraint constraint: constraints) {
            ltx.append("\t").command("const",constraint.getName());
        }
    }

    @Override
    public void writeCandidateRows(Tableau tableau) {
        Candidate[] candidates = tableau.getCandidates();
        EvaluationFunction evaluationFunction = tableau.getEvaluationFunction();
        ViolationMatrix matrix = ((EvaluationByElimination)evaluationFunction).getViolationMatrix();

        for (int i=0; i < candidates.length; i++) {

            Candidate candidate = tableau.getCandidates()[i];
            ltx.append("\\cand");
            if (tableau.isWinner(candidate)) {
                ltx.append("[\\Optimal]");
            }
            ltx.append(getCandidateString(candidate));
            for (int j = 0; j < matrix.width(); j++) {
                ltx.tab();
                ltx.append(getCellContents(matrix,i,j));
            }
            ltx.newLine();
        }
    }
}

package eu.jwvl.phonlib.io.latex;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.evaluation.EvaluationByElimination;
import eu.jwvl.phonlib.constraint.evaluation.EvaluationFunction;
import eu.jwvl.phonlib.constraint.evaluation.Tableau;
import eu.jwvl.phonlib.constraint.harmony.ViolationMatrix;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.io.Stringer;

/**
 * Created by janwillem on 14/07/16.
 */
public class LatexAngledTableauWriter extends LatexTableauWriter {
    private final int angle;


    public LatexAngledTableauWriter(boolean shadeCells, int angle) {
        super(new LatexStringBuilder(), shadeCells);
        this.angle = angle;
    }

    @Override
    public void writeTopRow(Tableau tableau, Constraint[] constraints) {
        ltx.append("\\multicolumn{3}{|c||}");
        ltx.bracket(tableau.getInput().toString());
        for (Constraint constraint: constraints) {
            ltx.append(String.format("& \\hspace*{0.1cm} \\begin{rotate}{%d} %s \\end{rotate} \\hspace*{0.2cm}",angle,constraint));
        }
        ltx.append("\\\\[0.5ex]").newLine();
        ltx.append("\\hline");
    }

    @Override
    public void writeCandidateRows(Tableau tableau) {

        EvaluationFunction evaluationFunction = tableau.getEvaluationFunction();
        ViolationMatrix matrix = ((EvaluationByElimination)evaluationFunction).getViolationMatrix();

        Candidate[] candidates = tableau.getCandidates();
        for (int i=0; i < candidates.length; i++) {
            Candidate candidate = candidates[i];
            ltx.append("\\hline & ");
            if (tableau.isWinner(candidate)) {
                ltx.append("\\ding{43} ");
            }
            ltx.append("& ");
            ltx.append(candidate.toString());
            for (int j = 0; j < matrix.width(); j++) {
                ltx.append (" & ");
                ltx.append(getCellContents(matrix,i,j));
            }
            ltx.append("\\\\").newLine();
        }
    }

    private String getCellContents(ViolationMatrix matrix, int i, int j) {
        String first = matrix.isGreyedOut(i, j) ? "\\cellcolor{lightgray} " : "";
        String original = matrix.violationsToString(i,j);
        return first+original.replaceAll("\\*","\\$\\\\ast\\$");
    }

    @Override
    public String write(Tableau tableau) {
        int numConstraints = tableau.getConstraints().length;
        ltx.append("\\begin{center} \\renewcommand*\\arraystretch{1.2}").newLine();
        ltx.append("\\vspace{1.0cm} \\scalebox{1}[1]{\\begin{tabular}[t]{|rrl||");
        ltx.append(Stringer.writeNTimes(numConstraints,"c|"));
        ltx.append("} \\cline{1-3} ").newLine();
        writeTopRow(tableau, tableau.getConstraints());
        writeCandidateRows(tableau);
        ltx.append("\\hline \\end{tabular}} \\renewcommand*\\arraystretch{1} \\end{center}").newLine();
        return ltx.toString();
    }
}

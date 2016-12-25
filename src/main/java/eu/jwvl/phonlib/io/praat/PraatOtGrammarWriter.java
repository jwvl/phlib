package eu.jwvl.phonlib.io.praat;

import eu.jwvl.phonlib.constraint.candidate.Candidate;
import eu.jwvl.phonlib.constraint.evaluation.Tableau;
import eu.jwvl.phonlib.constraint.harmony.ViolationVector;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;
import eu.jwvl.phonlib.io.latex.TableauWriter;

import java.text.DecimalFormat;

/**
 * Created by janwillem on 24/03/16.
 */
public class PraatOtGrammarWriter implements TableauWriter {
    private static final DecimalFormat df = new DecimalFormat("#.####");
    private static final double standardPlasticity = 1.0;

    @Override
    public String write(Tableau tableau) {
        StringBuilder builder = new StringBuilder();
        appendPreamble(tableau, builder);
        Constraint[] constraints = tableau.getConstraints();
        double[] rankings = tableau.getRankingValues();
        for (int i=0; i < constraints.length; i++) {
            String praatFriendly = PraatString.friendly(constraints[i].getName());
            builder.append("\""+praatFriendly+"\" ");
            builder.append(df.format(rankings[i])).append(" ");
            builder.append(df.format(rankings[i])).append(" ");
            builder.append(df.format(standardPlasticity)).append("\n");
        }

        Candidate[] candidates = tableau.getCandidates();
        ViolationVector[] vectors = tableau.getViolationVectors();
        builder.append("0 ! number of fixed candidates\n");
        builder.append("1 ! number of accepted inputs\n");
        String inputString = PraatString.friendly(tableau.getInput().toString());
        appendInEscapedQuotes(builder, inputString);
        builder.append(" ").append(candidates.length);
        for (int i=0; i < candidates.length; i++) {
            builder.append("\n   ");
            Candidate currentCandidate = candidates[i];
            String praatFriendlyCandidate =PraatString.friendly(currentCandidate.getOutputAsString().toString());
            appendInEscapedQuotes(builder, praatFriendlyCandidate);
            builder.append(" ");
            ViolationVector vector = vectors[i];
            builder.append(vector.printAsDigits());
        }
        return builder.toString();
    }

    private void appendPreamble(Tableau tableau, StringBuilder builder) {
        builder.append("\"ooTextFile\"\n\"OTGrammar 2\"\n");
        builder.append("<OptimalityTheory>\n");
        builder.append("0.0 ! leak\n");
        builder.append(tableau.getConstraints().length + " constraints\n");
    }

    private void appendInEscapedQuotes(StringBuilder builder, String toAppend) {
        builder.append("\""+toAppend+"\"");
    }
}

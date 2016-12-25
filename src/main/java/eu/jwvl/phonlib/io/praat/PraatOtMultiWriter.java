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
public class PraatOtMultiWriter implements TableauWriter {
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
        builder.append(candidates.length).append(" candidates");
        String inputString = PraatString.friendly(tableau.getInput().toString());
        for (int i=0; i < candidates.length; i++) {
            Candidate currentCandidate = candidates[i];
            String praatFriendlyCandidate =PraatString.friendly(currentCandidate.toString());
            builder.append("\n");
            appendInEscapedQuotes(builder, praatFriendlyCandidate);
            builder.append(" ");
            ViolationVector vector = vectors[i];
            builder.append(vector.printAsDigits());
        }
        return builder.toString();
    }

    private void appendPreamble(Tableau tableau, StringBuilder builder) {
        builder.append("\"ooTextFile\"\n\"OTMulti 2\"\n");
        builder.append("<OptimalityTheory>\n");
        builder.append("0.0 ! leak\n");
        builder.append(tableau.getConstraints().length + " constraints\n");
    }

    private void appendInEscapedQuotes(StringBuilder builder, String toAppend) {
        builder.append("\""+toAppend+"\"");
    }
}

package eu.jwvl.phonlib.io.latex;

import eu.jwvl.phonlib.constraint.evaluation.Tableau;

/**
 * Created by janwillem on 24/03/16.
 */
public interface TableauWriter {
    String write(Tableau tableau);
}

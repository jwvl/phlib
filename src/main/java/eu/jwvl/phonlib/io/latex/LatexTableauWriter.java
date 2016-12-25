package eu.jwvl.phonlib.io.latex;

import eu.jwvl.phonlib.constraint.evaluation.Tableau;
import eu.jwvl.phonlib.constraint.hierarchy.Constraint;

/**
 * Created by janwillem on 24/03/16.
 */
public abstract class LatexTableauWriter implements TableauWriter {


    public final LatexStringBuilder ltx;
    public final boolean shadeCells;

    protected LatexTableauWriter(LatexStringBuilder ltx, boolean shadeCells) {
        this.ltx = ltx;
        this.shadeCells = shadeCells;
    }

    public abstract void writeTopRow(Tableau tableau, Constraint[] constraints);
    public abstract void writeCandidateRows(Tableau tableau);

    public LatexStringBuilder getLtx() {
        return ltx;
    }

    public boolean isShadeCells() {
        return shadeCells;
    }
}

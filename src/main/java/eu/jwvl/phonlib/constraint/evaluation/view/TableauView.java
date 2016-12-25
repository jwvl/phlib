package eu.jwvl.phonlib.constraint.evaluation.view;

/**
 * Created by janwillem on 27/04/16.
 */
public class TableauView {
    private final String input;
    private final String[] candidates;
    private final String[] candidateSymbols;
    private final String[] constraints;
    private final TableauRow[] rows;

    public TableauView(String input, String[] candidates, String[] candidateSymbols, String[] constraints, TableauRow[] rows) {
        this.input = input;
        this.candidates = candidates;
        this.candidateSymbols = candidateSymbols;
        this.constraints = constraints;
        this.rows = rows;
    }
}

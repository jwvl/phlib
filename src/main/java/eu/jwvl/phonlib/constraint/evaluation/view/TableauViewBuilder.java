package eu.jwvl.phonlib.constraint.evaluation.view;

import java.util.List;

public class TableauViewBuilder {
    private String input;
    private List<String> candidates;
    private List<String> constraints;
    private TableauCell[][] cells;

    public TableauViewBuilder setInput(String input) {
        this.input = input;
        return this;
    }

    public TableauViewBuilder setCandidates(List<String> candidates) {
        this.candidates = candidates;
        return this;
    }

    public TableauViewBuilder addCandidate(String candidate) {
        this.candidates.add(candidate);
        return this;
    }

    public TableauViewBuilder setConstraints(List<String> constraints) {
        this.constraints = constraints;
        return this;
    }

    public TableauViewBuilder addConstraint(String constraint) {
        this.constraints.add(constraint);
        return this;
    }

    public TableauViewBuilder setCells(TableauCell[][] cells) {
        this.cells = cells;
        return this;
    }

    public TableauView createTableauView() {
        String[] candidatesArray = candidates.toArray(new String[candidates.size()]);
        String[] constraintsArray = constraints.toArray(new String[constraints.size()]);
        TableauRow[] asRows = new TableauRow[cells.length];
        for (int i=0; i < cells.length; i++) {
            asRows[i] = new TableauRow(cells[i]);
        }
        return new TableauView(input, candidatesArray, new String[]{}, constraintsArray, asRows); //TODO fix this...
    }
}
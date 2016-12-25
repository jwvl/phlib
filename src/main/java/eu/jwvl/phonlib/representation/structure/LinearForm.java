package eu.jwvl.phonlib.representation.structure;

import eu.jwvl.phonlib.representation.level.Level;
import eu.jwvl.phonlib.util.numeric.Range;

import java.util.Arrays;

/**
 * Created by janwillem on 02/10/15.
 */
public abstract class LinearForm<F extends Form> extends ComplexForm<F> {
    private final F[] forms;

    protected LinearForm(Level level, F[] forms) {
        super(level);
        this.forms = forms;
    }

    @Override
    public F[] getFormsAsArray() {
        return forms;
    }

    public int length() {
        return forms.length;
    }

    public F[] getRange(Range range) {
        return Arrays.copyOfRange(forms,range.getStartAt(),range.getEndAt());
    }

    public F[] getRange(int start, int end) {
        return getRange(Range.of(start,end));

    }

    public F getFormAtIndex(int index) {
        return forms[index];
    }
}

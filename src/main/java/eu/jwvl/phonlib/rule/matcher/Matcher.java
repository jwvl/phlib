package eu.jwvl.phonlib.rule.matcher;

import eu.jwvl.phonlib.representation.structure.Form;
import eu.jwvl.phonlib.representation.structure.LinearForm;
import eu.jwvl.phonlib.util.numeric.Range;

/**
 * Created by janwillem on 03/12/15.
 */
public interface Matcher<F extends Form> {
    public Range[] getMatches(LinearForm<F> phonologicalUnit);
}

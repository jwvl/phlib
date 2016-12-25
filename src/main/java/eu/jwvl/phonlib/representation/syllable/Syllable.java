package eu.jwvl.phonlib.representation.syllable;

import eu.jwvl.phonlib.representation.segment.PhonologicalUnit;
import eu.jwvl.phonlib.representation.syllable.model.SyllablePart;

/**
 * Created by janwillem on 03/12/15.
 */
public interface Syllable extends Comparable<Syllable> {
    SyllablePart getOnset();
    SyllablePart getNucleus();
    SyllablePart getCoda();
    SyllablePart getRhyme();
    PhonologicalUnit[] linearize();

    boolean isEmpty();

}

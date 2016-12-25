package eu.jwvl.phonlib.representation.structure;

import eu.jwvl.phonlib.representation.segment.PhonologicalUnit;

/**
 * Created by janwillem on 02/04/16.
 */
public interface PhonemicForm extends Iterable<PhonologicalUnit> {
    int length();
}

package eu.jwvl.phonlib.representation.syllable.syllabification;

import eu.jwvl.phonlib.representation.structure.PhonemicForm;
import eu.jwvl.phonlib.representation.syllable.Syllable;

import java.util.List;

/**
 * Created by janwillem on 03/12/15.
 */
public interface SyllabificationAlgorithm {
    List<Syllable> syllabify(PhonemicForm sequence);
    List<Integer> findNuclei(PhonemicForm sequence);
}

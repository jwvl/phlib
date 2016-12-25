package eu.jwvl.phonlib.representation.syllable.syllabification.impl;

import eu.jwvl.phonlib.representation.segment.EncodedPhoneString;
import eu.jwvl.phonlib.representation.segment.PhonologicalUnit;
import eu.jwvl.phonlib.representation.segment.StringPhonologicalUnit;
import eu.jwvl.phonlib.representation.syllable.Syllable;
import eu.jwvl.phonlib.representation.syllable.syllabification.OnsetSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by janwillem on 03/12/15.
 */
public class MaxOnsetSyllabification {
    private final StringPhonologicalUnit[] nuclei;
    private final OnsetSet onsets;

    public MaxOnsetSyllabification(StringPhonologicalUnit[] nuclei, OnsetSet onsets) {
        this.nuclei = nuclei;
        this.onsets = onsets;
    }

    public MaxOnsetSyllabification(Collection<StringPhonologicalUnit> nuclei, OnsetSet onsets) {
        this.nuclei = nuclei.toArray(new StringPhonologicalUnit[nuclei.size()]);
        this.onsets = onsets;
    }


    public List<Syllable> syllabify(StringPhonologicalUnit[] units) {
        List<Integer> nucleusIndices = findNuclei(units);
        List<Integer> boundaries = findMaxOnsets(units, nucleusIndices);
        List<Syllable> result = new ArrayList<>(nucleusIndices.size());
        if (nucleusIndices.size() == 0) {
            System.err.println("Should not happen..!");
            return Collections.EMPTY_LIST;
        }
        for (int i=0; i < boundaries.size(); i++) {
            int startAt = boundaries.get(i);
            int endAt = i < boundaries.size()-1 ? boundaries.get(i+1) : units.length;
            StringPhonologicalUnit[] syllable = getSubArray(units, startAt, endAt);
            // TODO fix this
            //result.add(SimpleSyllable.of(syllable,nucleusIndices.get(i)-startAt));
        }
        return result;
    }

    public List<Integer> findNuclei(StringPhonologicalUnit[] units) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i=0; i < units.length; i++) {
            PhonologicalUnit unit = units[i];
            for (PhonologicalUnit nucleus: nuclei) {
                if (unit.equals(nucleus)) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    private List<Integer> findMaxOnsets(StringPhonologicalUnit[] units, List<Integer> nuclei) {
        List<Integer> result = new ArrayList<Integer>(nuclei.size());
        result.add(0);
        for (int i=0; i < nuclei.size()-1; i++) {
            int startAt =nuclei.get(i)+1;
            int endAt = nuclei.get(i+1);
            result.add(findMaxOnsetPosition(units,startAt,endAt));
        }
        return result;

    }

    private static StringPhonologicalUnit[] getSubArray(StringPhonologicalUnit[] original, int from, int to) {
            int newLength = to - from;
            StringPhonologicalUnit[] result = new StringPhonologicalUnit[newLength];
            System.arraycopy(original, from, result, 0, newLength);
            return result;
    }

private int findMaxOnsetPosition(StringPhonologicalUnit[] toSearch, int startAt, int endAt) {
    for (int i= startAt; i < endAt; i++) {
        StringPhonologicalUnit[] subArray = getSubArray(toSearch,i,endAt);
        EncodedPhoneString encodedPhoneString = EncodedPhoneString.create(subArray);
        if (onsets.containsSequence(encodedPhoneString)) {
            return i;
        }
    }
    return endAt;
}



}

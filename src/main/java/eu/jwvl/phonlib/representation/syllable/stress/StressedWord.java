package eu.jwvl.phonlib.representation.syllable.stress;

import eu.jwvl.phonlib.representation.segment.Sonority;
import eu.jwvl.phonlib.representation.segment.StringPhoneReader;
import eu.jwvl.phonlib.representation.segment.StringPhonologicalUnit;
import eu.jwvl.phonlib.representation.syllable.Syllable;
import eu.jwvl.phonlib.representation.syllable.syllabification.impl.MaxOnsetSyllabification;
import eu.jwvl.phonlib.rhyme.RhymeTemplateOrder;
import eu.jwvl.phonlib.rhyme.RhymingWord;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by janwillem on 02/04/16.
 */
public class StressedWord {
    private final Syllable[] syllables;
    private final Stress[] stresses;
    private final int primaryStress;


    public StressedWord(Syllable[] syllables, Stress[] stresses, int primaryStress) {
        this.syllables = syllables;
        this.stresses = stresses;
        this.primaryStress = primaryStress;
    }

    public static StressedWord fromString(String input, MaxOnsetSyllabification syllabification) {
        if (input == null)
            return null;
        String cleaned = input.replaceAll("'","").replaceAll("ˌ","").replaceAll("ˈ","");
//        System.out.println("Trying to parse word " +input + " --> " +cleaned);
        StringPhonologicalUnit[] asUnits = StringPhoneReader.parseSequence(cleaned).decodeToArray();
        List<Syllable> syllableList = syllabification.syllabify(asUnits);
        if (syllableList.isEmpty()) {
            return null;
        }
        Stress[] stresses = new Stress[syllableList.size()];
        int cursor =0, vowelCounter =0, primaryStress = 0;
        Stress stressFound = Stress.NONE;
        while (cursor < cleaned.length()) {
            StringPhonologicalUnit unit = StringPhoneReader.findBestMatch(cleaned,cursor);
            if (unit != null) {
                if (unit.getSonority() == Sonority.V) {
                    stresses[vowelCounter] = stressFound;
                    if (stressFound == Stress.PRIMARY) {
                        primaryStress = vowelCounter;
                    }
                    vowelCounter++;
                    stressFound = Stress.NONE;
                }
                cursor+=unit.getSymbol().length();
            } else {
                char c = cleaned.charAt(cursor);
                if (isStressCharacter(c)) {
                    stressFound = Stress.fromChar(c);
                }
                cursor++;
            }
        }
        Syllable[] syllables = syllableList.toArray(new Syllable[syllableList.size()]);
        return new StressedWord(syllables,stresses, primaryStress);
    }

    private static boolean isStressCharacter(char c) {
        return (c == '\'' || c == 'ˌ');
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i=0; i < stresses.length; i++) {
            if (stresses[i] != Stress.NONE) {
                sb.append(stresses[i]);
            }
            sb.append(syllables[i]);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StressedWord that = (StressedWord) o;
        return Arrays.equals(syllables, that.syllables) &&
                Arrays.equals(stresses, that.stresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(syllables, stresses);
    }

    public Syllable[] getSyllablesRhymingTemplate() {
        Syllable[] result = new Syllable[5];
        for (int i=0; i < 5; i++) {
            result[i] = getSyllableOrReturnEmpty(primaryStress+RhymeTemplateOrder.ORDER[i]);
        }
        result[0] = syllables[primaryStress];
        result[1] = getSyllableOrReturnEmpty(primaryStress+2);
        result[2] = getSyllableOrReturnEmpty(primaryStress+1);
        result[3] = getSyllableOrReturnEmpty(primaryStress-2);
        result[4] = getSyllableOrReturnEmpty(primaryStress-1);
        return result;
    }

    private Syllable getSyllableOrReturnEmpty(int i) {
        if (i >= syllables.length || i < 0) {
            return null;//SimpleSyllable.getEmpty();
        } else {
            return syllables[i];
        }
    }

    public RhymingWord toRhymingWord(String originalWord) {
        return new RhymingWord(originalWord,getSyllablesRhymingTemplate());
    }


}

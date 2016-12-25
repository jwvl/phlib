package eu.jwvl.phonlib.rhyme;

import eu.jwvl.phonlib.representation.syllable.Syllable;
import eu.jwvl.phonlib.representation.syllable.stress.StressedWord;
import eu.jwvl.phonlib.representation.syllable.syllabification.impl.MaxOnsetSyllabification;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by janwillem on 02/04/16.
 */
public class RhymingWord implements Comparable<RhymingWord>{
    private final String originalString;
    private final Syllable[] template;

    public RhymingWord(String originalString, Syllable[] template) {
        this.originalString = originalString;
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RhymingWord that = (RhymingWord) o;
        return Objects.equals(originalString, that.originalString) &&
                Arrays.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalString, template);
    }

    public Syllable getSyllable(int syllableNumber) {
        return template[syllableNumber];
    }

    @Override
    public int compareTo(RhymingWord o) {
        for (int i=0; i < template.length; i++) {
            int result = template[i].compareTo(o.template[i]);
            if (result!=0) {
                return result;
            }
        }
        return originalString.compareTo(o.originalString);
    }

    public static RhymingWord fromStringsAndSyllabification(String originalWord, String transcription, MaxOnsetSyllabification syllabification) {
        StressedWord stressedWord = StressedWord.fromString(transcription,syllabification);
        return new RhymingWord(originalWord,stressedWord.getSyllablesRhymingTemplate());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RhymingWord{");
        sb.append("originalString='").append(originalString).append('\'');
        sb.append(", template=").append(Arrays.toString(template));
        sb.append('}');
        return sb.toString();
    }
}

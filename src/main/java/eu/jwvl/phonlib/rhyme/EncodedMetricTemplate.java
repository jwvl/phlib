package eu.jwvl.phonlib.rhyme;

import eu.jwvl.phonlib.representation.syllable.Syllable;

/**
 * Created by janwillem on 20/06/16.
 */
public class EncodedMetricTemplate {
    private static int footPositions = 8;

    private final byte[] contents;
    private int primaryFootLength;

    public EncodedMetricTemplate(byte[] contents, int primaryFootLength) {
        this.contents = contents;
        this.primaryFootLength = primaryFootLength;
    }

    public int getNumSyllables() {
        return contents.length / footPositions;
    }

    public static EncodedMetricTemplate fromSyllables(Syllable[] syllables, int primaryStressAt) {
        int numSyllables = syllables.length;
        byte[] finalContents = new byte[syllables.length*footPositions];
        int primaryFootLength = numSyllables - primaryStressAt;
        return new EncodedMetricTemplate(finalContents, primaryFootLength);
    }

    public int getPrimaryFootLength() {
        return primaryFootLength;
    }

    public int getSecondaryFootLength() {
        return 0;
    }

    private static void fillByteArray(byte[] toFill, Syllable syllable) {
    }
}

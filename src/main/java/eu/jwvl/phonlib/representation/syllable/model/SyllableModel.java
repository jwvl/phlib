package eu.jwvl.phonlib.representation.syllable.model;

/**
 * Created by janwillem on 16/04/2017.
 */
public class SyllableModel {
    private final int rhymingFootSyllables;
    private final int preRhymeSyllables;
    private final int numOnsetBytes;
    private final int numNucleusBytes;
    private final int numCodaBytes;

    public SyllableModel(int rhymingFootSyllables, int preRhymeSyllables, int numOnsetBytes, int numNucleusBytes, int numCodaBytes) {
        this.rhymingFootSyllables = rhymingFootSyllables;
        this.preRhymeSyllables = preRhymeSyllables;
        this.numOnsetBytes = numOnsetBytes;
        this.numNucleusBytes = numNucleusBytes;
        this.numCodaBytes = numCodaBytes;
    }

    public int getLeftfootStart() {
        return getRhymingFootSyllables()*getSyllableLength();
    }

    public int getSyllableLength() {
        return numOnsetBytes+numNucleusBytes+numCodaBytes;
    }

    public int getTotalLength() {
        return getSyllableLength()*(rhymingFootSyllables+ preRhymeSyllables);
    }

    public int getRhymingFootSyllables() {
        return rhymingFootSyllables;
    }

    public int getPreRhymeSyllables() {
        return preRhymeSyllables;
    }

    public int getNumOnsetBytes() {
        return numOnsetBytes;
    }

    public int getNumNucleusBytes() {
        return numNucleusBytes;
    }

    public int getNumCodaBytes() {
        return numCodaBytes;
    }

    public FootPart getFootPartForIndex(int index) {
        int mod = index % getSyllableLength();
        if (mod < getNumOnsetBytes()) {
            return FootPart.ONSET;
        } else if (mod < getNumOnsetBytes()+getNumNucleusBytes()) {
            return FootPart.NUCLEUS;
        } else {
            return FootPart.CODA;
        }
    }

    public boolean isInStressedSyllable(int index) {
        return index < getSyllableLength();
    }

    public boolean isInRhymingFoot(int index) {
        return index < getLeftfootStart();
    }

}

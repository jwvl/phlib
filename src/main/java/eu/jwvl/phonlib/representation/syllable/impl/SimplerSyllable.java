package eu.jwvl.phonlib.representation.syllable.impl;

import eu.jwvl.phonlib.representation.segment.EncodedPhoneString;
import eu.jwvl.phonlib.representation.segment.PhonologicalUnit;
import eu.jwvl.phonlib.representation.segment.StringPhonologicalUnit;
import eu.jwvl.phonlib.representation.syllable.Syllable;
import eu.jwvl.phonlib.representation.syllable.model.SyllablePart;
import eu.jwvl.phonlib.representation.syllable.stress.Stress;
import eu.jwvl.phonlib.symbol.encoding.trie.NodeType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by janwillem on 03/12/15.
 */
public class SimplerSyllable implements Syllable {
    private final byte[] elements;
    private final SyllablePart[] parts;
    private final int nucleusStart;
    private final int codaStart;

    private final static SimplerSyllable EMPTY = new SimplerSyllable(null,0,0);
    private final static ByteComparator byteComparator = new ByteComparator();


    public SimplerSyllable(EncodedPhoneString elements, int nucleusStart, int codaStart) {
        if (elements != null)  {
        this.elements = elements.contents();}
        else {
            this.elements = new byte[0];
        }
        this.codaStart = codaStart;
        this.nucleusStart = nucleusStart;
        parts = new SyllablePart[3];
        parts[NodeType.NUCLEUS.ordinal()] = new SyllablePart(NodeType.NUCLEUS, getNucleusBytes(), Stress.NONE);
        parts[NodeType.ONSET.ordinal()] = new SyllablePart(NodeType.ONSET, getOnsetBytes(), Stress.NONE);
        parts[NodeType.CODA.ordinal()] = new SyllablePart(NodeType.CODA,getCodaBytes(), Stress.NONE);

    }

    public static SimplerSyllable of(StringPhonologicalUnit[] elements, int nucleusIndex) {
        EncodedPhoneString encodedPhoneString = EncodedPhoneString.create(elements);
        return new SimplerSyllable(encodedPhoneString,nucleusIndex,nucleusIndex+1);
    }

    public PhonologicalUnit[] getOnsetUnits() {
        return EncodedPhoneString.decodeToArray(getOnsetBytes());
    }

    @Override
    public SyllablePart getOnset() {
        return parts[NodeType.ONSET.ordinal()];
    }

    @Override
    public SyllablePart getNucleus() {
        return parts[NodeType.NUCLEUS.ordinal()];
    }

    @Override
    public SyllablePart getCoda() {
        return parts[NodeType.CODA.ordinal()];
    }

    public byte[] getNucleusBytes() {
        return Arrays.copyOfRange(elements, nucleusStart, codaStart);
    }
    public byte[] getOnsetBytes() {return Arrays.copyOfRange(elements, 0, nucleusStart);}

    public PhonologicalUnit[] getCodaUnits() {
        return EncodedPhoneString.decodeToArray(getCodaBytes());
    }

    public byte[] getCodaBytes() {
        if (codaStart >= elements.length)
            return new byte[0];
        return Arrays.copyOfRange(elements, codaStart, elements.length);
    }


    @Override
    public SyllablePart getRhyme() {
        return parts[NodeType.RHYME.ordinal()];
    }

    @Override
    public PhonologicalUnit[] linearize() {
        return EncodedPhoneString.decodeToArray(elements);
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(EncodedPhoneString.toString(elements));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplerSyllable that = (SimplerSyllable) o;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements, nucleusStart, codaStart);
    }

    public static Syllable getEmpty() {
        return EMPTY;
    }


    boolean rhymesWith(Syllable other) {
        return (this.getRhyme().equals(other.getRhyme()));
    }

    private static double getOverlap(byte[] a, byte[] b) {
        int totalMatches = 1;

        byte[] longer = b, shorter = a;
        if (a.length >= b.length) {
            longer = a;
            shorter = b;
        }
        int possibleMatches = longer.length;
        for (byte aByte: longer) {
            if (isInArray(aByte, shorter)) {
                totalMatches+=1;
            }
        }
        return (totalMatches / (double) possibleMatches);
    }

    private static boolean isInArray(byte a, byte[] b) {
        for (byte bByte: b) {
            if (bByte == a)
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Syllable o) {
       // TODO
        return 0;
    }


}

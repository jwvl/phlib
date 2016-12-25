package eu.jwvl.phonlib.rhyme;

/**
 * Created by janwillem on 02/04/16.
 */
public interface RhymeFitness {
    double getFitness(RhymingWord rhymer, RhymingWord rhymee);
    double getScoreForSyllable(RhymingWord one, RhymingWord other, int syllableNumber);

}

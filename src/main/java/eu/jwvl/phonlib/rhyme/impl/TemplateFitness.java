//package eu.jwvl.phonlib.rhyme.impl;
//
//import eu.jwvl.phonlib.representation.syllable.Syllable;
//import eu.jwvl.phonlib.rhyme.RhymeFitness;
//import eu.jwvl.phonlib.rhyme.RhymingWord;
//
///**
// * Created by janwillem on 02/04/16.
// */
//public class TemplateFitness implements RhymeFitness {
//    private static double[] weights = {1,0.5,0.3,0.2,0.1};
//    private static double bothEmpty = 0.8;
//    private static double fullRhyme = 1.0;
//    private static double internalRhyme = 0.5;
//    private static double endRhyme = 0.3;
//    private static double alliteration = 0.2;
//    private static double samenessPenalty= -5.0;
//
//    @Override
//    public double getFitness(RhymingWord rhymer, RhymingWord rhymee) {
//        double score = 0;
//        for (int i=0; i <weights.length; i++) {
//            score+=(weights[i]*getScoreForSyllable(rhymer,rhymee,i));
//        }
//        return score;
//    }
//
//    @Override
//    public double getScoreForSyllable(RhymingWord one, RhymingWord other, int syllableNumber) {
//        double score = 0;
//        Syllable syllableOne = one.getSyllable(syllableNumber);
//        Syllable syllableOther = other.getSyllable(syllableNumber);
//        if (syllableOne.isEmpty()) {
//            return (syllableOther.isEmpty() ? bothEmpty : 0);
//        } else if (syllableOther.isEmpty()) { return 0;}
//        else {
//            if (syllableOne.equals(syllableOther) && syllableNumber == 0) {
//                score += samenessPenalty;
//            }
////            else if (Syllable.rhymesMatch(syllableOne,syllableOther)) {
////                score+= fullRhyme;
////            }
////            else {
////                if (Syllable.nucleiMatch(syllableOne, syllableOther)) {
////                    score += internalRhyme;
////                }
////                score += (endRhyme * syllableOne.endMatches(syllableOther));
////            }
//            score+= (alliteration*syllableOne.onsetMatches(syllableOther));
//        }
//        return score;
//
//    }
//
//}

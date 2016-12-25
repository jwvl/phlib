package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.io.SimpleStringReader;
import eu.jwvl.phonlib.representation.syllable.Syllable;
import eu.jwvl.phonlib.representation.syllable.stress.StressedWord;
import eu.jwvl.phonlib.representation.syllable.syllabification.OnsetSet;
import eu.jwvl.phonlib.representation.syllable.syllabification.impl.MaxOnsetSyllabification;
import eu.jwvl.phonlib.rhyme.RhymingWord;

import java.util.*;

/**
 * Created by janwillem on 02/04/16.
 */
public class TestReadPhones {
    private final static String orthFileName = "data/all-dutchWords.txt";
    private final static String phonFileName = "data/allTranscriptions.txt";
    private final static Map<String,String> orthToPhonMap = new HashMap<String,String>();
    private final static int stopAt = 100000;

    public static void main(String[] args) {
        StringPhoneReader.readFromFile("phones/dutchPhones.txt");
        Collection<StringPhonologicalUnit> vowels = StringPhoneReader.getForSonority(Sonority.V);
        for (PhonologicalUnit phonologicalUnit: vowels) {
            System.out.print(phonologicalUnit);
        }
        OnsetSet onsets = OnsetSet.fromFile("phones/dutchOnsets.txt");

        EncodedPhoneString string = StringPhoneReader.parseSequence("ubrukudubrun");
        System.out.println(string);
        MaxOnsetSyllabification syllabification = new MaxOnsetSyllabification(vowels,onsets);
        List<Syllable> syllables = syllabification.syllabify(string.decodeToArray());
        for (Syllable syllable: syllables) {
            System.out.println(syllable);
        }
        String anotherTestString = "ɣəmˌeːtaːmˌɔrfoːzˈɪːrt";
        StressedWord stressedWord = StressedWord.fromString(anotherTestString, syllabification);
        System.out.println(stressedWord);

        TreeSet<RhymingWord> rhymeSet = getRhymingWords(stopAt,syllabification);


        for (RhymingWord rhymingWord2: rhymeSet) {
            System.out.println(rhymingWord2);
        }

    }

    private static TreeSet<RhymingWord> getRhymingWords(int stopAt, MaxOnsetSyllabification syllabification) {
        TreeSet<RhymingWord> result = new TreeSet<>();
        Collection<RhymingWord> allRhymingWords = new ArrayList<RhymingWord>(stopAt);
        List<String> orthStrings = SimpleStringReader.StringsFromFile(orthFileName);
        List<String> phonStrings = SimpleStringReader.StringsFromFile(phonFileName);
        int i = 0;
        stopAt = Math.min(Math.min(stopAt, orthStrings.size()),phonStrings.size());
        while (i < stopAt) {
            String lowerCasedOrth = orthStrings.get(i).toLowerCase();
            String phonString = phonStrings.get(i);
            System.out.println(lowerCasedOrth +" - "+phonString);
            StressedWord stressedWord = StressedWord.fromString(phonStrings.get(i),syllabification);
            if (stressedWord != null) {
                orthToPhonMap.put(lowerCasedOrth, phonStrings.get(i));
                RhymingWord rhymingWord = new RhymingWord(lowerCasedOrth, stressedWord.getSyllablesRhymingTemplate());
                result.add(rhymingWord);
            }
            i++;
//            if (i % 100 == 0) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return result;
    }
}

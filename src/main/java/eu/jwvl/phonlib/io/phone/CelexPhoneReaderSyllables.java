package eu.jwvl.phonlib.io.phone;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import eu.jwvl.phonlib.io.BinaryFeatureSystem;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;
import eu.jwvl.phonlib.representation.syllable.model.SyllableModel;
import eu.jwvl.phonlib.representation.syllable.model.SyllableToTemplate;
import eu.jwvl.phonlib.rhyme.trie.cost.FeatureWeightsMap;
import eu.jwvl.phonlib.rhyme.trie.cost.WeightedDistanceCost;
import eu.jwvl.phonlib.rhyme.trie.syllpart.STrieRepository;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by janwillem on 24/12/2016.
 * TODO finish this?
 */
public class CelexPhoneReaderSyllables {

    private SyllableModel syllableModel;
    private static int STOP_AT =500000;


    public CelexPhoneReaderSyllables(SyllableModel syllableModel) {
        this.syllableModel = syllableModel;

    }

    public boolean fits(String input) {
        if (!input.contains("'")) {
            return false;
        }
        String[] firstSplit = input.split("'");
        if (firstSplit[0].split("-").length > syllableModel.getPreRhymeSyllables()) {
            return false;
        } else if (firstSplit[1].split("-").length > syllableModel.getRhymingFootSyllables()) {
            return false;
        }
        return true;
    }

    private String[] toSyllables(String input) {
        int LEFT_LENGTH = syllableModel.getPreRhymeSyllables();
        int RIGHT_LENGTH = syllableModel.getRhymingFootSyllables();
        String[] result = new String[RIGHT_LENGTH+LEFT_LENGTH];
        String[] split = input.split("-");
        int primaryStress = findPrimaryStress(split);
        for (int i=0; i <RIGHT_LENGTH; i++) {
            int index = primaryStress+i;
            if (index >= split.length) {
                result[i] = "";
            } else {
                result[i] = split[index].replace("'","");
            }
        }
        for (int i=0; i < LEFT_LENGTH; i++) {
            int resultIndex = RIGHT_LENGTH+i;
            int index = primaryStress-(i+1);
            if (index < 0) {
                result[resultIndex] = "";
            } else {
                result[resultIndex] = split[index];
            }
        }
        return result;
    }

    private int findPrimaryStress(String[] split) {
        for (int i=0; i < split.length; i++) {
            if (split[i].startsWith("'"))
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        String file = "data/celex-phones-3.txt";
        URL url =Resources.getResource(file);
        List<String> strings = Lists.newArrayList();
        try {
            strings = Resources.readLines(url, Charset.defaultCharset());
           // Collections.shuffle(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SyllableModel syllableModel = new SyllableModel(3,2,3,2,4);
        BinaryFeatureSystem featureSystem = new BinaryFeatureSystem("features/featuresHayes-celex.txt");
        FeatureWeightsMap weightsMap = new FeatureWeightsMap(syllableModel,featureSystem,0.5);
        WeightedDistanceCost weightedDistanceCost = new WeightedDistanceCost(weightsMap);
        StringPhoneMap stringPhoneMap = featureSystem.phones;
        SyllableToTemplate syllableToTemplate = new SyllableToTemplate("phones/dutchSyllableParts.txt");
        CelexPhoneReaderSyllables reader = new CelexPhoneReaderSyllables(syllableModel);
        STrieRepository trieRepository = new STrieRepository();
        Map<String,byte[][]> wordFormsToByteMatrices = Maps.newHashMap();
        for (int i=0; i < strings.size() && i < STOP_AT; i++) {
            String line = strings.get(i);
            String[] parts = line.split("\\\\");
            if (parts.length == 2) {
                String phones = parts[1];
                String wordForm = parts[0];
                if (reader.fits(phones) && !phones.contains(" ")) {
                    String[] result = reader.toSyllables(phones);
                    byte[][] asBytesMatrix = syllableToTemplate.getAsByteMatrix(result,stringPhoneMap);
                    wordFormsToByteMatrices.put(wordForm,asBytesMatrix);
                    trieRepository.addByteString(asBytesMatrix,wordForm);
//                    System.out.println("Added to repository: "+wordForm);
//                    System.out.println(Arrays.toString(asBytes));
//                    for (String s : result) {
//                        System.out.print(syllableToTemplate.getAsTemplate(s));
//                    }
//                    System.out.println();

                }

            }
        }
//        STrieMatchFinder matchFinder = new STrieMatchFinder(trieRepository,weightedDistanceCost,stringPhoneMap);
//        String testWord = "winterjas";
//        byte[] wandelBytes = wordFormsToBytes.get(testWord);
//        if (wandelBytes == null) {
//            System.out.println("Niet gevonden!");
//        }
//        List<CostNode> topMatches = matchFinder.getBestN(30,wandelBytes);
//        for (CostNode costNode: topMatches) {
//            byte[] found = costNode.getBytesRecursive();
//            String asString = stringPhoneMap.bytesToString(found);
//            System.out.println(asString);
//        }


    }

}

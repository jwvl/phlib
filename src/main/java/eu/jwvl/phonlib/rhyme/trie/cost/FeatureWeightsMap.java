package eu.jwvl.phonlib.rhyme.trie.cost;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import eu.jwvl.phonlib.io.BinaryFeatureSystem;
import eu.jwvl.phonlib.representation.syllable.model.FootPart;
import eu.jwvl.phonlib.representation.syllable.model.SyllableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janwillem on 15/04/2017.
 */
public class FeatureWeightsMap extends IndexedWeightMap {
    private final BinaryFeatureSystem featureSystem;
    private final int maxPhones;
    private Table<Byte,Byte,int[]> featureDifferencesTable;
    private SyllableModel syllableModel;

    // Create initial weights for equality

    public FeatureWeightsMap(SyllableModel syllableModel, BinaryFeatureSystem binaryFeatureSystem, double initialWeight) {
        this.syllableModel = syllableModel;
        this.featureSystem = binaryFeatureSystem;
        this.maxPhones = syllableModel.getTotalLength();
        int bitsPerByte = binaryFeatureSystem.numBitsPerPhone();
        int expectedLength = maxPhones*bitsPerByte;
        this.weights = Lists.newArrayListWithExpectedSize(expectedLength);
        for (int i=0; i < expectedLength; i++) {
            double initWeight =createWeightForPosition(i / bitsPerByte, initialWeight);
            weights.add(initWeight);
        }
        featureDifferencesTable = HashBasedTable.create();
    }


    private double getWeight(int position, int bitNumber) {
        return getWeight((position*featureSystem.numBitsPerPhone())+bitNumber);
    }




    @Override
    public List<Integer> getViolatedWeights(byte a, byte b, int position) {
        int[] unIndexedWeights = getFeatureDifferences(a,b);
        List<Integer> result = Lists.newArrayListWithCapacity(unIndexedWeights.length);
        for (int w: unIndexedWeights) {
            result.add(position*featureSystem.numBitsPerPhone()+w);
        }
        return result;
    }


    public List<Integer> getViolatedWeights(byte[] aBytes, byte[] bBytes) {
        int length = Math.min(aBytes.length,bBytes.length);
        List<Integer> result = Lists.newArrayList();
        for (int i=0; i < length; i++) {
            int base = i*featureSystem.numBitsPerPhone();
            byte a = aBytes[i];
            byte b = bBytes[i];
            int[] weightIndices = getFeatureDifferences(a,b);
            for (int index: weightIndices) {
                result.add(base+index);
            }
        }
        return result;
    }

    public List<List<Integer>> getViolationDifferences(byte[] original, byte[] learner, byte[] target) {
        List<List<Integer>> result = Lists.newArrayList();
        List<Integer> learnerList = getViolatedWeights(original, learner);
        List<Integer> targetList = getViolatedWeights(original, target);
        result.add(learnerList);
        result.add(targetList);
        return result;
    }

    private int[] getFeatureDifferences(byte a, byte b) {
        if (a == b)
            return new int[0];
        int[] result = featureDifferencesTable.get(a,b);
        if (result == null) {
            boolean[] arr_a = featureSystem.phoneToBooleanArray(a);
            boolean[] arr_b = featureSystem.phoneToBooleanArray(b);
            result = findFeatureDifferences(arr_a, arr_b);
            featureDifferencesTable.put(a,b,result);
            featureDifferencesTable.put(b,a,result);
        }
        return result;
    }

    private int[] findFeatureDifferences(boolean[] a, boolean[] b) {
        int max = (Math.max(a.length, b.length));
        ArrayList<Integer> result = Lists.newArrayListWithCapacity(max);
        for (int i=0; i < max; i++) {
            if (a[i] != b[i]) {
                result.add(i);
            }
        }
        int[] asArray = new int[result.size()];
        for (int i=0; i < result.size(); i++) {
            asArray[i] = result.get(i);
        }
        return asArray;
    }

    private double createWeightForPosition(int position, double initialWeight) {
        int positionMultiplier = 1;
        FootPart footPart = syllableModel.getFootPartForIndex(position);
        if (footPart == FootPart.ONSET) {
            positionMultiplier +=0;
        } else if (footPart == FootPart.NUCLEUS) {
            positionMultiplier +=1;
        }
        if (syllableModel.isInRhymingFoot(position)) {
            positionMultiplier+=1;
            if (syllableModel.isInStressedSyllable(position)) {
                positionMultiplier+=1;
            }
        }
        return initialWeight*positionMultiplier;
    }
}

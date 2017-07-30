package eu.jwvl.phonlib.rhyme.trie.cost;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import eu.jwvl.phonlib.io.BinaryFeatureSystem;

import java.util.List;

/**
 * Created by janwillem on 06/05/2017.
 */
public class TransformWeightsMap extends IndexedWeightMap {
    private final BinaryFeatureSystem featureSystem;
    List<Table<Byte,Byte,Integer>> weightsIndices;
    private final double initWeight;
    private final double distanceConstant;

    public TransformWeightsMap(BinaryFeatureSystem featureSystem, int maxPhones, double initWeight, double distanceConstant) {
        this.featureSystem = featureSystem;
        weightsIndices = Lists.newArrayList();
        for (int i=0; i < maxPhones; i++) {
            weightsIndices.add(HashBasedTable.create());
        }
        weights = Lists.newArrayList();
        this.distanceConstant = distanceConstant;
        this.initWeight = initWeight;
    }

    public int getWeightIndex(int position, byte a, byte b) {
        int result;
        Integer check = weightsIndices.get(position).get(a,b);
        if (check == null) {
            double valueToAdd = initWeight + (distanceConstant * getFeatureDistance(a,b));
            result = addWeight(position, a, b, valueToAdd);
        } else {
            result = check;
        }
        return result;
    }


    private int addWeight(int position, byte a, byte b, double value) {
        weights.add(value);
        int index = weights.size()-1;
        weightsIndices.get(position).put(a,b,index);
        weightsIndices.get(position).put(b,a,index);
        return index;
    }

    private double getFeatureDistance(byte a, byte b) {
        boolean[] aBits = featureSystem.phoneToBooleanArray(a);
        boolean[] bBits = featureSystem.phoneToBooleanArray(b);
        int sum = 0;
        for (int i=0; i < aBits.length; i++) {
            if (aBits[i] != bBits[i]) {
                sum++;
            }
        }
        return sum / (double) aBits.length;
    }

    @Override
    public List<Integer> getViolatedWeights(byte a, byte b, int position) {
        return Lists.newArrayList(getWeightIndex(position,a,b));
    }

    @Override
    public List<List<Integer>> getViolationDifferences(byte[] original, byte[] learner, byte[] target) {
        List<Integer> learnerViolated = Lists.newArrayList();
        List<Integer> targetViolated = Lists.newArrayList();
        for (int i=0; i < original.length; i++) {
            byte lByte = learner[i];
            byte tByte = target[i];
            if (lByte != tByte) {
                byte oByte = original[i];
                learnerViolated.add(getWeightIndex(i,oByte,lByte));
                targetViolated.add(getWeightIndex(i,oByte,tByte));
            }
        }
        return Lists.newArrayList(learnerViolated,targetViolated);
    }
}

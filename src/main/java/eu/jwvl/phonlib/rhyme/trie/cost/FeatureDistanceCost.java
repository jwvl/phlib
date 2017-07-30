package eu.jwvl.phonlib.rhyme.trie.cost;

import com.google.common.collect.Maps;
import eu.jwvl.phonlib.io.BinaryFeatureSystem;
import eu.jwvl.phonlib.representation.segment.StringPhone;

import java.util.Map;

/**
 * Created by janwillem on 15/04/2017.
 */
public class FeatureDistanceCost implements ByteCostFunction {

    private Map<Integer,double[][]> indexedWeights;
    private final BinaryFeatureSystem featureSystem;

    public FeatureDistanceCost(BinaryFeatureSystem featureSystem) {
        this.featureSystem = featureSystem;
        this.indexedWeights = Maps.newHashMap();
    }

    private double[][] initializeWeights() {
        int numPhones = featureSystem.phones.size();
        double[][] result = new double[numPhones][numPhones];
        for (byte i=0; i < numPhones; i++) {
            StringPhone a = featureSystem.phones.getByIndex(i);
            for (byte j= i; j < numPhones; j++) {
                StringPhone b = featureSystem.phones.getByIndex(j);
                if (a != b) {
                    double distance = getEuclideanDistance(a.getByteValue(),b.getByteValue());
                    result[i][j] = distance;
                    result[j][i] = distance;
                }
            }
        }
        return result;
    }

    private double getCostFromMap(byte a, byte b, int position) {
        if (!indexedWeights.containsKey(position)) {
            indexedWeights.put(position,initializeWeights());
        }
        return indexedWeights.get(position)[a][b];
    }

    @Override
    public double getTransformCost(byte a, byte b, int position) {
        return a == b ? 0 : getCostFromMap(a,b,position);
    }

    private double getEuclideanDistance(byte a, byte b) {
        boolean[] b_a = featureSystem.phoneToBooleanArray(a);
        boolean[] b_b = featureSystem.phoneToBooleanArray(b);
        int count = 0;
        for (int i=0; i < b_a.length && i < b_b.length; i++) {
            if (b_a[i] != b_b[i]) {
                count++;
            }
        }
        return Math.sqrt(count);
    }

    public double compareArrays(byte[] array_a, byte[] array_b) {
        double sum = 0;
        for (int i=0; i < array_a.length && i < array_b.length; i++) {
            sum += getTransformCost(array_a[i],array_b[i],0);
        }
        return sum;
    }


}

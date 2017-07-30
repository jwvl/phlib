package eu.jwvl.phonlib.rhyme.trie.cost;

import java.util.List;

/**
 * Created by janwillem on 06/05/2017.
 */
public abstract class IndexedWeightMap {
    protected List<Double> weights;

    public void updateWeights(List<Integer> upIndices, List<Integer> downIndices, double difference) {
        double upWeight = difference / upIndices.size();
        double downWeight = difference / -downIndices.size();
        for (int i=0; i < upIndices.size(); i++) {
            changeWeight(upIndices.get(i), upWeight);
        }
        for (int j=0; j <downIndices.size(); j++) {
            changeWeight(downIndices.get(j), downWeight);
        }
    }

    public void changeWeight(int index, double delta) {
        double oldWeight = weights.get(index);
        weights.set(index, oldWeight+delta);
    }

    public double getWeight(int index) {
        return weights.get(index);
    }

    public double getTransformCost(byte a, byte b, int position) {
        double sum = 0;
        List<Integer> violatedWeights = getViolatedWeights(a,b,position);
        for (int violatedWeightIndex:violatedWeights) {
            sum += weights.get(violatedWeightIndex);
        }
        return sum;
    }

    public abstract List<Integer> getViolatedWeights(byte a, byte b, int position);

    public abstract List<List<Integer>> getViolationDifferences(byte[] original, byte[] source, byte[] target);
}

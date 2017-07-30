package eu.jwvl.phonlib.learn;

import eu.jwvl.phonlib.rhyme.trie.cost.IndexedWeightMap;

import java.util.List;

/**
 * Created by janwillem on 06/05/2017.
 */
public class WeightMapLearner {
    private final IndexedWeightMap weights;

    public WeightMapLearner(IndexedWeightMap weights) {
        this.weights = weights;
    }

    public void learn(byte[] original, byte[] learner, byte[] target, double updateWeight) {
        List<List<Integer>> differences = weights.getViolationDifferences(original, learner, target);
        weights.updateWeights(differences.get(0), differences.get(1), updateWeight);
    }
}

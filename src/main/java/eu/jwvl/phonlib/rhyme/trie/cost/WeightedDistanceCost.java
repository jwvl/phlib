package eu.jwvl.phonlib.rhyme.trie.cost;

/**
 * Created by janwillem on 16/04/2017.
 */
public class WeightedDistanceCost implements ByteCostFunction{
    private final IndexedWeightMap featureWeightsMap;

    public WeightedDistanceCost(IndexedWeightMap featureWeightsMap) {
        this.featureWeightsMap = featureWeightsMap;
    }

    @Override
    public double getTransformCost(byte a, byte b, int position) {
        return featureWeightsMap.getTransformCost(a,b,position);
    }
}

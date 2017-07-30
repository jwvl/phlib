package eu.jwvl.phonlib.rhyme.trie.cost;

/**
 * Created by janwillem on 15/04/2017.
 */
public interface ByteCostFunction {
    double getTransformCost(byte a, byte b, int position);
}

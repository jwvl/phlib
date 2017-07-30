package eu.jwvl.phonlib.feature.bundle;

import eu.jwvl.phonlib.feature.system.IndexedFeatureSystem;

/**
 * Created by janwillem on 05/03/2017.
 */
public class BinaryFeatureToBoolean {
    private final IndexedFeatureSystem featureNames;

    public BinaryFeatureToBoolean(IndexedFeatureSystem featureNames) {
        this.featureNames = featureNames;
    }

    public boolean[] encode(BinaryFeatureBundle bundle) {
        boolean[] result = new boolean[arrayLength()];
        for (int i=0; i < featureNames.size(); i++) {
            int index = 2*i;
            String currentAttribute = featureNames.getFeatureByIndex(i);
            Boolean value = bundle.getFeature(currentAttribute);
            if (value != null)
                if (value.booleanValue()) { // +
                    result[index+1] = true;
                } else {
                    result[index] = true; // -
                }
            }
        return result;
    }

    public int arrayLength() {
        return 2*featureNames.size();
    }

    public int booleansToInt(boolean[] arr){
        int n = 0;
        for (boolean b : arr)
            n = (n << 1) | (b ? 1 : 0);
        return n;
    }

    public boolean[] intToBoolean(int orig) {
        int length = arrayLength();
        final boolean[] ret = new boolean[length];
        for (int i = 0; i < length; i++) {
            ret[length - 1 - i] = (1 << i & orig) != 0;
        }
        return ret;
    }
}

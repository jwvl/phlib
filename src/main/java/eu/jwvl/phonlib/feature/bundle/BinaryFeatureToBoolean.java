package eu.jwvl.phonlib.feature.bundle;

import java.util.List;

/**
 * Created by janwillem on 05/03/2017.
 */
public class BinaryFeatureToBoolean {
    private final List<String> featureNames;

    public BinaryFeatureToBoolean(List<String> featureNames) {
        this.featureNames = featureNames;
    }

    public boolean[] encode(StringFeatureBundle bundle) {
        boolean[] result = new boolean[arrayLength()];
        for (int i=0; i < featureNames.size(); i++) {
            int index = 2*i;
            String currentAttribute = featureNames.get(i);
            if (bundle.hasFeature(currentAttribute) {
                String currentValue = bundle.getFeature(currentAttribute);
                if (currentValue.equals("-")) {
                    result[index] = true;
                } else if (currentValue.equals("+")) {
                    result[index+1] = true;
                }
            }
        }
    }

    public int arrayLength() {
        return 2*featureNames.size();
    }
}

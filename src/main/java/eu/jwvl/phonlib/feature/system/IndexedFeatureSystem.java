package eu.jwvl.phonlib.feature.system;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by janwillem on 07/04/2017.
 */
public class IndexedFeatureSystem {
    private final String[] indexedFeatures;
    private final Map<String,Integer> featuresToIndex;

    public IndexedFeatureSystem(String[] indexedFeatures) {
        this.indexedFeatures = indexedFeatures;
        featuresToIndex = Maps.newHashMap();
        for (int i=0; i < indexedFeatures.length; i++) {
            featuresToIndex.put(indexedFeatures[i],i);
        }
    }

    public int size() {
        return indexedFeatures.length;
    }

    public String getFeatureByIndex(int i) {
        return indexedFeatures[i];
    }
}

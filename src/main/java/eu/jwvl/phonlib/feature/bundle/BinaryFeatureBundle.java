package eu.jwvl.phonlib.feature.bundle;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by janwillem on 05/03/2017.
 */
public class BinaryFeatureBundle {
    private Map<String,Boolean> contents;

    public BinaryFeatureBundle() {
        contents = Maps.newHashMap();
    }

    public void addFeature(String feature, boolean value) {
        contents.put(feature,value);
    }

    public Boolean getFeature(String feature)  {
        return contents.get(feature);
    }


}

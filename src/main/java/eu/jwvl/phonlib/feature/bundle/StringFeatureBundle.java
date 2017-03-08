package eu.jwvl.phonlib.feature.bundle;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by janwillem on 05/03/2017.
 */
public class StringFeatureBundle {
    private Map<String,String> features;

    public StringFeatureBundle() {
        features = Maps.newHashMap();
    }

    public void addFeature(String attribute, String value) {
        features.put(attribute,value);
    }

    public boolean hasFeature(String attribute) {
        return features.containsKey(attribute);
    }

    public String getFeature(String attribute) {
        String toReturn = features.get(attribute);
        if (toReturn == null) {
            return "<Null>";
        } else {
        return toReturn;
    }}
}

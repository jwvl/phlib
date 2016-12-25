package eu.jwvl.phonlib.feature.system;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

/**
 * Created by janwillem on 29/03/16.
 */
public class FeatureSystem<Feature> {
    private final List<String> featureNames;

    public FeatureSystem(Collection<String> featureNames) {
        this.featureNames = ImmutableList.copyOf(featureNames);
    }
}

package eu.jwvl.phonlib.feature.hierarchical.visualize;

import eu.jwvl.phonlib.feature.Feature;

/**
 * Created by janwillem on 27/09/15.
 */
public class FeatureEdge {
    private final int inId;
    private final int oudId;
    private final Feature distinguishingFeature;

    public FeatureEdge(int inId, int oudId, Feature distinguishingFeature) {
        this.inId = inId;
        this.oudId = oudId;
        this.distinguishingFeature = distinguishingFeature;
    }

    @Override
    public String toString() {
        return "FeatureEdge{" +
                "inId=" + inId +
                ", oudId=" + oudId +
                ", distinguishingFeature=" + distinguishingFeature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureEdge that = (FeatureEdge) o;

        if (inId != that.inId) return false;
        if (oudId != that.oudId) return false;
        return !(distinguishingFeature != null ? !distinguishingFeature.equals(that.distinguishingFeature) : that.distinguishingFeature != null);

    }

    @Override
    public int hashCode() {
        int result = inId;
        result = 31 * result + oudId;
        result = 31 * result + (distinguishingFeature != null ? distinguishingFeature.hashCode() : 0);
        return result;
    }
}

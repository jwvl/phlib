package eu.jwvl.phonlib.feature;

import eu.jwvl.phonlib.feature.impl.FeatureType;

public interface Feature {
	
	public FeatureType getType();
	public String getAttribute();
	public boolean valueEquals(Feature other);
	public Feature getNullFeature(String attribute);
	public boolean isNull();

}

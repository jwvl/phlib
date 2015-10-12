/**
 * 
 */
package eu.jwvl.phonlib.feature.bundle;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import eu.jwvl.phonlib.feature.Feature;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureBundleBuilder {
	private ImmutableSet.Builder<Feature> builder;
	private Set<String> attributes;
	
	public FeatureBundleBuilder() {
		builder = ImmutableSet.builder();
		attributes = Sets.newHashSet();
	}
	
	public FeatureBundleBuilder(FeatureBundle old) {
		this();
		for (Feature feature: old) {
			insertFeature(feature);
		}
	}
	
	private void insertFeature(Feature feature) {
		String attribute = feature.getAttribute();
		if (attributes.contains(attribute)) {
			throw new UnsupportedOperationException("Error: attribute "+attribute+" already set for this bundle.");
		} else {
			builder.add(feature);
		}
	}
	
	public void addFeatures(Feature... features) {
		for (Feature f: features) {
			insertFeature(f);
		}
	}
	
	public void addFeatures(Collection<Feature> features) {
		for (Feature f: features) {
			insertFeature(f);
		}	
	}
	
	public FeatureBundle build() {
		return new FeatureBundle(builder);
	}

}

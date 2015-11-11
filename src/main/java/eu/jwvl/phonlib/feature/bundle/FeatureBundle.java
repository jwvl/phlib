/**
 * 
 */
package eu.jwvl.phonlib.feature.bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import eu.jwvl.phonlib.feature.Feature;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureBundle implements Iterable<Feature> {
	private final Map<String,Feature> map;
	private final Set<Feature> featureSet;
	private static FeatureBundle EMPTY = createEmpty();
	
	public FeatureBundle(ImmutableSet.Builder<Feature> builder) {
		this.featureSet = builder.build();
		ImmutableMap.Builder<String,Feature> mapBuilder = ImmutableMap.builder();
		for (Feature feature: featureSet) {
			mapBuilder.put(feature.getAttribute(), feature);
		}
		map = mapBuilder.build();
	}
	
	/**
	 * @return
	 */
	private static FeatureBundle createEmpty() {
		ImmutableSet.Builder<Feature> emptyBuilder = ImmutableSet.builder();
		return new FeatureBundle(emptyBuilder);
	}
	
	public FeatureBundle copyAndAdd(Feature toAdd) {
		ImmutableSet.Builder<Feature> builder = ImmutableSet.builder();
		builder.addAll(featureSet);
		builder.add(toAdd);
		return new FeatureBundle(builder);
	}

	public Set<Feature> getFeatureSet() {
		return featureSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureSet == null) ? 0 : featureSet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeatureBundle))
			return false;
		FeatureBundle other = (FeatureBundle) obj;
		if (featureSet == null) {
			if (other.featureSet != null)
				return false;
		} else if (!featureSet.equals(other.featureSet))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return featureSet.toString();
	}
	
	public boolean hasFeature(Feature f) {
		return featureSet.contains(f);
	}
	
	public Feature getFeature(String attribute) {
		if (map.containsKey(attribute)) {
			return map.get(attribute);
		} else if (getSize() > 0){
			Iterator<String> iterator = map.keySet().iterator();
			Feature dummyFeature = map.get(iterator.next());
			return dummyFeature.getNullFeature(attribute);
		} else {
			return null;
		}
	}
	
	public int getSize() {
		return featureSet.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Feature> iterator() {
		return featureSet.iterator();
	}
	
	public boolean contains(Feature... feature) {
		for (Feature f: feature) {
			if (!featureSet.contains(f))
				return false;
		}
		return true;
	}
	
	public boolean isSupersetOf(FeatureBundle subset) {
		for (Feature f: subset) {
			if (!featureSet.contains(f))
				return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		return getSize() == 0;
	}

	/**
	 * @return
	 */
	public static FeatureBundle empty() {
		return EMPTY;
	}
	
	
	
	

}

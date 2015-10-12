/**
 * 
 */
package eu.jwvl.phonlib.representation.inventory;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureSegmentInventory extends SegmentInventory<FeaturePhonologicalUnit> {
	SetMultimap<String,Feature> knownValues;

	/**
	 * @param name
	 * @param contents
	 */
	public FeatureSegmentInventory(String name, Set<FeaturePhonologicalUnit> segments) {
		super(name, new HashSet<FeaturePhonologicalUnit>());
		knownValues = HashMultimap.create();
		for (FeaturePhonologicalUnit segment: segments){
			addSegment(segment);
		}
	}

	@Override
	public void addSegment(FeaturePhonologicalUnit segment) {
		super.addSegment(segment);
		for (Feature f: segment.getFeatures()) {
			knownValues.put(f.getAttribute(), f);
		}
	}

	/**
	 * @param string
	 * @return
	 */
	public static FeatureSegmentInventory createEmpty(String string) {
		Set<FeaturePhonologicalUnit> emptySet = Sets.newHashSet();
		return new FeatureSegmentInventory(string, emptySet);
	}
	
	public Collection<String> getAttributes() {
		return knownValues.keySet();
	}
	
	public List<Set<Feature>> getFeatureValues() {
		List<Set<Feature>> result = Lists.newArrayList();
		for (String attribute: getAttributes()) {
			Set<Feature> featureSet = knownValues.get(attribute);
			result.add(featureSet);
		}
		return result;
	}
	
	public Set<Feature> getFeaturesForAttribute(String attribute) {
		return knownValues.get(attribute);
	}

	/**
	 * @param f
	 * @return
	 */
	public Set<FeaturePhonologicalUnit> getSegmentsForFeature(Feature feature) {
		Set<FeaturePhonologicalUnit> result = Sets.newHashSet();
		for (FeaturePhonologicalUnit segment: getContents()) {
			if (segment.getFeatures().contains(feature)) {
				result.add(segment);
			}
		}
		return result;
	}
	
	



}

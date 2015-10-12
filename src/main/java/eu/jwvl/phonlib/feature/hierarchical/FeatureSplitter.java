/**
 * 
 */
package eu.jwvl.phonlib.feature.hierarchical;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.feature.bundle.NaturalClass;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;
import eu.jwvl.phonlib.representation.inventory.FeatureSegmentInventory;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureSplitter {
	private final FeatureSegmentInventory featureSegmentInventory;

	/**
	 * @param featureSegmentInventory
	 */
	public FeatureSplitter(FeatureSegmentInventory featureSegmentInventory) {
		this.featureSegmentInventory = featureSegmentInventory;
	}

	public void calculateSplits() {
		for (String attribute : featureSegmentInventory.getAttributes()) {
			System.out.println("Splitting for attribute " + attribute);
			Set<Feature> featureValues = featureSegmentInventory.getFeaturesForAttribute(attribute);
			List<Feature> featuresAsList = Lists.newArrayList();
			List<FeaturePhonologicalUnit> segmentsAsList = Lists.newArrayList();
			for (Feature f : featureValues) {
				Set<FeaturePhonologicalUnit> segments = featureSegmentInventory.getSegmentsForFeature(f);
				System.out.printf("%d members in %s: %s%n", segments.size(), f.toString(), segments.toString());
			}

		}
	}

	public List<SplitSet> calculateSplits(NaturalClass naturalClass) {
		List<SplitSet> result = Lists.newArrayList();
		for (String attribute : featureSegmentInventory.getAttributes()) {

			Set<Feature> featureValues = featureSegmentInventory.getFeaturesForAttribute(attribute);
			List<Feature> featuresAsList = Lists.newArrayList();
			featuresAsList.addAll(featureValues);
			SplitSet current = SplitSet.createForFeature(featuresAsList, naturalClass.getMembers());
			if (current.getMultimap().keySet().size() > 1) {

				result.add(current);
			}
		}
		Collections.sort(result);
		return result;
	}

	public FeatureSegmentInventory getFeatureSegmentInventory() {
		return featureSegmentInventory;
	}

}

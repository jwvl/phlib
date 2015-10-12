/**
 * 
 */
package eu.jwvl.phonlib.feature.hierarchical;

import java.util.List;
import java.util.Set;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class SplitSet implements Comparable<SplitSet> {
	private final String attribute;
	private final ListMultimap<Feature, FeaturePhonologicalUnit> splits;
	private final int smallestSetSize;
	private final double averageAbsoluteDifference;

	public SplitSet(List<Feature> features, List<Set<FeaturePhonologicalUnit>> segmentSets) {
		this.attribute = features.get(0).getAttribute();
		this.splits = LinkedListMultimap.create();
		int minSetSize = Integer.MAX_VALUE;
		for (int i = 0; i < features.size(); i++) {
			Feature key = features.get(i);
			Set<FeaturePhonologicalUnit> values = segmentSets.get(i);
			splits.putAll(key, values);
			minSetSize = Math.min(minSetSize, values.size());
		}
		smallestSetSize = minSetSize;
		averageAbsoluteDifference = getAverageAbsoluteDifference();
	}

	public static SplitSet createForFeature(List<Feature> features, Set<FeaturePhonologicalUnit> fullSet) {
		List<Set<FeaturePhonologicalUnit>> listOfSets = Lists.newArrayList();
		for (Feature feature : features) {
			Set<FeaturePhonologicalUnit> result = Sets.newHashSet();
			for (FeaturePhonologicalUnit segment : fullSet) {
				if (segment.getFeatures().contains(feature)) {
					result.add(segment);
				}
			}
			listOfSets.add(result);
		}
		return new SplitSet(features, listOfSets);

	}

	public double getAverageAbsoluteDifference() {
		int[] sizes = new int[splits.keySet().size()];
		int count = 0;
		for (Feature feature : splits.keySet()) {
			sizes[count++] = splits.get(feature).size();
		}
		int numPairs = 0;
		double summedDifferences = 0;
		for (int i = 0; i < sizes.length - 1; i++) {
			for (int j = i + 1; j < sizes.length; j++) {
				double absoluteDifference = Math.abs(sizes[i] - sizes[j]);
				summedDifferences += absoluteDifference;
				numPairs++;
			}
		}
		return summedDifferences / numPairs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SplitSet o) {
		int firstCompare = -(Integer.compare(smallestSetSize, o.smallestSetSize));
		if (firstCompare != 0) {
			return firstCompare;
		}
		return Double.compare(averageAbsoluteDifference, o.averageAbsoluteDifference);
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	public Multimap<Feature, FeaturePhonologicalUnit> getMultimap() {
		return splits;
	}
}

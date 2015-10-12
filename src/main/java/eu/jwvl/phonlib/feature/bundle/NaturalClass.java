/**
 * 
 */
package eu.jwvl.phonlib.feature.bundle;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class NaturalClass {
	private final Set<FeaturePhonologicalUnit> members;
	private final FeatureBundle sharedFeatures;
	
	/**
	 * @param members
	 * @param sharedFeatures
	 */
	private NaturalClass(Set<FeaturePhonologicalUnit> members, FeatureBundle sharedFeatures) {
		this.members = members;
		this.sharedFeatures = sharedFeatures;
	}
	
	public static NaturalClass create(Collection<FeaturePhonologicalUnit> members, FeatureBundleBuilder builder) {
		Set<FeaturePhonologicalUnit> memberSet = ImmutableSet.copyOf(members);
		return new NaturalClass(memberSet, builder.build());
	}

	public Set<FeaturePhonologicalUnit> getMembers() {
		return members;
	}

	public FeatureBundle getSharedFeatures() {
		return sharedFeatures;
	}

	@Override
	public String toString() {
		return String.format("%d members in %s: %s", members.size(), sharedFeatures.toString(), members.toString());
	}
	
	
	
	

}

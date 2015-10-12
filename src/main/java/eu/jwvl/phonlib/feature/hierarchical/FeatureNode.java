/**
 * 
 */
package eu.jwvl.phonlib.feature.hierarchical;

import com.google.common.collect.Sets;
import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.feature.bundle.FeatureBundleBuilder;
import eu.jwvl.phonlib.feature.bundle.NaturalClass;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;

import java.util.Set;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class FeatureNode {

	private final int id;
	private static int idCounter = 0;
	private final Feature distinguishingFeature;
	private final NaturalClass segmentContents;
	private Set<FeatureNode> children;
	/**
	 * @param nodeName
	 * @param segmentContents
	 * @param children
	 */
	private FeatureNode(Feature distinguishingFeature, NaturalClass naturalClass) {
		this.id = idCounter++;
		this.distinguishingFeature = distinguishingFeature;
		this.segmentContents = naturalClass;
		this.children = Sets.newHashSet();
	}
	
	public static FeatureNode createNew(Feature distinguishingFeature, NaturalClass naturalClass) {
		return new FeatureNode(distinguishingFeature, naturalClass);
	}
	
	public static FeatureNode createRoot(Set<FeaturePhonologicalUnit> members) {
		NaturalClass fullClass = NaturalClass.create(members, new FeatureBundleBuilder());
		return new FeatureNode(null, fullClass);
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
	public boolean isRoot() {
		return getMembers().getSharedFeatures().isEmpty();
	}

	/**
	 * @return the members
	 */
	public NaturalClass getMembers() {
		return segmentContents;
	}


	public void addChild(FeatureNode child) {
		children.add(child);
	}
	
	public void printRecursively(int depth) {
		for (int i=0; i < depth; i++) {
			System.out.print(" ");
		}
		System.out.print("\\-");
		System.out.println(distinguishingFeature + ">>" + segmentContents.toString());
		for (FeatureNode featureNode: children) {
			featureNode.printRecursively(depth+1);
		}
	}


	public Set<FeatureNode> getChildren() {
		return children;
	}

	public Feature getDistinguishingFeature() {
		return distinguishingFeature;
	}

	public String toString() {
		if (this.isRoot()) {
			return "ROOT";
		}
		StringBuilder result = new StringBuilder(id + ") "+distinguishingFeature.toString());
		result.append(" >> ");
		result.append(segmentContents.toString());
		return result.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FeatureNode that = (FeatureNode) o;

		if (id != that.id) return false;
		if (segmentContents != null ? !segmentContents.equals(that.segmentContents) : that.segmentContents != null)
			return false;
		return !(children != null ? !children.equals(that.children) : that.children != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (segmentContents != null ? segmentContents.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
	}

	public int getId() {
		return id;
	}
}

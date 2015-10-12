/**
 * 
 */
package eu.jwvl.phonlib.feature.hierarchical;

import java.util.Set;

import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;
import eu.jwvl.phonlib.representation.inventory.FeatureSegmentInventory;

/**
 * @author jwvl
 * @date 26/09/2015
 *
 */
public class FeatureTree {
	private final FeatureNode root;

	/**
	 * @param root
	 */
	private FeatureTree(Set<FeaturePhonologicalUnit> segments) {
		root = FeatureNode.createRoot(segments);
	}

	/**
	 * @return
	 */
	public static FeatureTree createInstance(FeatureSegmentInventory inventory) {
		return new FeatureTree(inventory.getContents());
	}

	public FeatureNode getRoot() {
		return root;
	}
	
	public void printRecursively() {
		root.printRecursively(0);
	}


	
}

/**
 * 
 */
package eu.jwvl.phonlib.feature.hierarchical;

import com.google.common.collect.Multimap;
import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.feature.bundle.FeatureBundle;
import eu.jwvl.phonlib.feature.bundle.FeatureBundleBuilder;
import eu.jwvl.phonlib.feature.bundle.NaturalClass;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;
import eu.jwvl.phonlib.representation.inventory.FeatureSegmentInventory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jwvl
 * @date 26/09/2015
 *
 */
public class FeatureTreeMaker {
	private final FeatureTree tree;
	private ArrayDeque<FeatureNode> unexpanded;
	private FeatureSegmentInventory fullInventory;
	private FeatureSplitter splitter;
	
	private FeatureTreeMaker(FeatureSegmentInventory inventory) {
		tree = FeatureTree.createInstance(inventory);
		fullInventory = inventory;
		splitter = new FeatureSplitter(inventory);
		unexpanded = new ArrayDeque<FeatureNode>();
		unexpanded.add(getTree().getRoot());
		while (!unexpanded.isEmpty()) {
			FeatureNode toExpand = unexpanded.poll();
			Collection<FeatureNode> expansions = expand(toExpand);
			for (FeatureNode featureNode: expansions) {
				unexpanded.add(featureNode);
			}
		}
	}
	
	public static FeatureTree create(FeatureSegmentInventory inventory) {
		FeatureTreeMaker maker = new FeatureTreeMaker(inventory);
		return maker.getTree();
	}

	/**
	 * @param toExpand
	 * @return
	 */
	private List<FeatureNode> expand(FeatureNode toExpand) {
		List<FeatureNode> result = new ArrayList<FeatureNode>();
		List<SplitSet> splits = splitter.calculateSplits(toExpand.getMembers());
		if (splits.isEmpty()) {
			return result;
		}
		FeatureBundle oldBundle = toExpand.getMembers().getSharedFeatures();
		// For now, just take top one
		SplitSet winner = splits.get(0);
		Multimap<Feature,FeaturePhonologicalUnit> multimap = winner.getMultimap();
		for (Feature f: multimap.keySet()) {
			FeatureBundleBuilder expandedBuilder = new FeatureBundleBuilder(oldBundle);
			expandedBuilder.addFeatures(f);
			Collection<FeaturePhonologicalUnit> smallerClassMembers = multimap.get(f);
			NaturalClass smallerClass = NaturalClass.create(smallerClassMembers, expandedBuilder);
			FeatureNode newChild = FeatureNode.createNew(f, smallerClass);
			toExpand.addChild(newChild);
			result.add(newChild);
		}
		return result;
	}

	/**
	 * @return the myTree
	 */
	public FeatureTree getTree() {
		return tree;
	}

}

/**
 * 
 */
package eu.jwvl.phonlib.app;

import eu.jwvl.phonlib.feature.hierarchical.FeatureTree;
import eu.jwvl.phonlib.feature.hierarchical.FeatureTreeMaker;
import eu.jwvl.phonlib.io.FeatureSegmentInventoryReader;
import eu.jwvl.phonlib.io.SymbolReader;
import eu.jwvl.phonlib.representation.inventory.FeatureSegmentInventory;
import eu.jwvl.phonlib.symbol.PhoneticUnitCache;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class FeatureReader {
	private static String unitsPath = "/ipa2xsampa.txt";
	private static String featuresTable = "/features/featuresHayes.txt";
	
	public static void main(String[] args) {
		PhoneticUnitCache phoneticUnitCache = SymbolReader.initSymbolsFromFile(unitsPath,"\t");
		FeatureSegmentInventoryReader segmentReader = FeatureSegmentInventoryReader.initFromFile(featuresTable, "\t", phoneticUnitCache);
		FeatureSegmentInventory inventory = segmentReader.readInventory();
		FeatureTree tree = FeatureTreeMaker.create(inventory);
		tree.printRecursively();
	}

}

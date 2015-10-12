/**
 * 
 */
package eu.jwvl.phonlib.io;

import java.util.HashSet;
import java.util.Set;

import eu.jwvl.phonlib.feature.Feature;
import eu.jwvl.phonlib.feature.FeatureFactory;
import eu.jwvl.phonlib.feature.bundle.FeatureBundle;
import eu.jwvl.phonlib.feature.bundle.FeatureBundleBuilder;
import eu.jwvl.phonlib.feature.impl.FeatureType;
import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;
import eu.jwvl.phonlib.representation.inventory.FeatureSegmentInventory;
import eu.jwvl.phonlib.symbol.PhoneticUnit;
import eu.jwvl.phonlib.symbol.PhoneticUnitCache;
import eu.jwvl.phonlib.symbol.encoding.PhoneticEncoding;
import eu.jwvl.phonlib.symbol.types.SpeechSound;
import eu.jwvl.phonlib.util.string.MyStringTable;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureSegmentInventoryReader {
	private PhoneticEncoding encoding;
	private final PhoneticUnitCache units;
	private final MyStringTable stringTable;
	private FeatureType featureType;

	private FeatureSegmentInventoryReader(MyStringTable myStringTable, PhoneticUnitCache phoneticUnitCache) {
		this.stringTable = myStringTable;
		this.units = phoneticUnitCache;
		System.out.printf("Found table with %d columns and %d rows.%n", stringTable.getNumCols(),
				stringTable.getNumRows());
	}

	public static FeatureSegmentInventoryReader initFromFile(String filename, String separator,
			PhoneticUnitCache cache) {
		MyStringTable stringTable = MyStringTable.fromFile(filename, true, separator);
		return new FeatureSegmentInventoryReader(stringTable, cache);
	}

	public FeatureSegmentInventory readInventory() {
		initFields();
		System.out.printf("Trying to create segment inventory with encoding=%s and featureType=%s%n",
				getEncoding().toString(), getFeatureType().toString());
		FeatureSegmentInventory result = FeatureSegmentInventory.createEmpty("FromFile");
		for (int i = 0; i < stringTable.getNumRows(); i++) {
			String phoneString = stringTable.getString(i, 0);
			PhoneticUnit currentUnit = units.getByString(encoding, phoneString);
			if (currentUnit instanceof SpeechSound) {
				SpeechSound speechSound = (SpeechSound) currentUnit;
				FeatureBundle bundle = getFeatureBundle(i);
				FeaturePhonologicalUnit currentSegment = FeaturePhonologicalUnit.create(speechSound, bundle);
				result.addSegment(currentSegment);
			} else {
				System.out.println(phoneString+ " is no speech sound.");
			}
			

		}
		return result;

	}

	/**
	 * @param i
	 * @return
	 */
	private FeatureBundle getFeatureBundle(int row) {
		FeatureBundleBuilder builder = new FeatureBundleBuilder();
		for (int col = 1; col < stringTable.getNumCols(); col++) {
			String currentAttribute = stringTable.getColName(col);
			String symbolFound = stringTable.getString(row, col);
			Feature foundFeature = FeatureFactory.createFeature(getFeatureType(), currentAttribute, symbolFound);
			if (!foundFeature.isNull()) {
				builder.addFeatures(foundFeature);
			}
		}
		return builder.build();
	}

	public PhoneticEncoding getEncoding() {
		return encoding;
	}

	public PhoneticUnitCache getUnits() {
		return units;
	}

	public void setEncoding(PhoneticEncoding encoding) {
		this.encoding = encoding;
	}

	/**
	 * 
	 */
	private void initFields() {
		if (encoding == null) {
			setEncoding(units.determineEncoding(stringTable.getColumnContents(0)));
		}
		if (featureType == null) {
			Set<String> allCells = new HashSet<String>();
			for (int i = 1; i < stringTable.getNumCols(); i++) {
				allCells.addAll(stringTable.getColumnContents(i));
			}
			setFeatureType(FeatureType.determineType(allCells));
		}

	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

}

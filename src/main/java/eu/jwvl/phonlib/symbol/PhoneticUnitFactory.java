/**
 * 
 */
package eu.jwvl.phonlib.symbol;

import eu.jwvl.phonlib.symbol.encoding.PhoneticEncodingMap;
import eu.jwvl.phonlib.symbol.types.Boundary;
import eu.jwvl.phonlib.symbol.types.Diacritic;
import eu.jwvl.phonlib.symbol.types.SpeechSound;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class PhoneticUnitFactory {
	private final PhoneticUnitCache cache;

	/**
	 */
	public PhoneticUnitFactory() {
		this.cache = new PhoneticUnitCache();
	}

	public PhoneticUnit createFromMapAndClassification(PhoneticEncodingMap map, String classification) {
		PhoneticUnit result = null;
		if (classification.contains("Vowel") || classification.contains("Consonant")) {
			result = new SpeechSound(map, classification);
		} else if (classification.contains("Diacritic")) {
			result = new Diacritic(map, classification);
		} else if (classification.contains("Boundary")) {
			result = new Boundary(map, classification);
		}
		if (result != null) {
			cache.addPhoneticUnit(result);
		}
		return result; // TODO Fix other types!
	}

	public PhoneticUnitCache getCache() {
		return cache;
	}

}

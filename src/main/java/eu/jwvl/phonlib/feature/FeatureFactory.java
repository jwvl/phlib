/**
 * 
 */
package eu.jwvl.phonlib.feature;

import eu.jwvl.phonlib.feature.impl.BinaryFeature;
import eu.jwvl.phonlib.feature.impl.FeatureType;
import eu.jwvl.phonlib.feature.impl.IntegerFeature;
import eu.jwvl.phonlib.feature.impl.PrivativeFeature;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class FeatureFactory {
	

	public static Feature createFeature(FeatureType type, String attribute, String valueString) {
		if (type == FeatureType.BINARY) {
			return BinaryFeature.fromAttributeAndString( attribute,  valueString);
		} else if (type == FeatureType.PRIVATIVE) {
			return PrivativeFeature.fromAttributeAndString(attribute, valueString);
		} else if (type == FeatureType.INTEGER) {
			return IntegerFeature.fromAttributeAndString(attribute, valueString);
		} else {
			return StringFeature.fromAttributeAndString(attribute,valueString);
		}
	}

}

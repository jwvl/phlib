/**
 * 
 */
package eu.jwvl.phonlib.feature;

import eu.jwvl.phonlib.feature.impl.FeatureType;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class StringFeature extends AbstractFeature<String>{

	/**
	 * @param attribute
	 * @param object
	 */
	private StringFeature(String attribute, String value) {
		super(attribute,value);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getType()
	 */
	@Override
	public FeatureType getType() {
		return FeatureType.STRING;
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getNullFeature(java.lang.String)
	 */
	@Override
	public Feature getNullFeature(String attribute) {
		return new StringFeature(attribute,null);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#valueEquals(eu.jwvl.phonlib.feature.Feature)
	 */
	@Override
	public boolean valueEquals(Feature other) {
		if (other instanceof StringFeature) {
			return getValue().equals(getValueString());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#getValueString()
	 */
	@Override
	public String getValueString() {
		return getValue();
	}

	/**
	 * @param attribute
	 * @param valueString
	 * @return
	 */
	public static Feature fromAttributeAndString(String attribute, String valueString) {
		return new StringFeature(attribute, valueString);
	}

}

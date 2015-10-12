/**
 * 
 */
package eu.jwvl.phonlib.feature.impl;

import eu.jwvl.phonlib.feature.Feature;

/**
 * @author jwvl
 * @date Aug 8, 2015
 *
 */
public class PrivativeFeature extends BinaryFeature {
	private static Feature dummy = PrivativeFeature.create("DUMMY");
	
	/**
	 * @param attribute
	 * @param value
	 */
	private PrivativeFeature(String attribute, boolean value) {
		super(attribute, value);
	}
	
	/**
	 * @param attribute
	 * @param value
	 */
	private PrivativeFeature(String attribute) {
		this(attribute, true);
	}
	
	
	
	public static PrivativeFeature create(String attribute) {
		return new PrivativeFeature(attribute);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getNullFeature(java.lang.String)
	 */
	@Override
	public Feature getNullFeature(String attribute) {
		return new PrivativeFeature(attribute, false);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#valueEquals(eu.jwvl.phonlib.feature.Feature)
	 */
	@Override
	public boolean valueEquals(Feature other) {
		if (other instanceof PrivativeFeature) {
			return ((PrivativeFeature) other).getValue().equals(getValue());
		} else {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getType()
	 */
	@Override
	public FeatureType getType() {
		return FeatureType.PRIVATIVE;
	}
	
	/**
	 * @param attribute
	 * @param valueString
	 * @return
	 */
	public static Feature fromAttributeAndString(String attribute, String valueString) {
		if (valueString.equals("1") || valueString.equals("+")) {
			return createTrue(attribute);}
		else if (valueString.equals("-1") || valueString.equals("-")) {
			return createFalse(attribute); }
		else {
			return dummy.getNullFeature(attribute);
		}
	}
	

}

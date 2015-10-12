/**
 * 
 */
package eu.jwvl.phonlib.feature.impl;

import eu.jwvl.phonlib.feature.AbstractFeature;
import eu.jwvl.phonlib.feature.Feature;

/**
 * @author jwvl
 * @date Aug 8, 2015
 *
 */
public class IntegerFeature extends AbstractFeature<Integer> {
	private static Feature dummy = IntegerFeature.createInstance("DUMMY", Integer.MIN_VALUE);

	/**
	 * @param attribute
	 * @param value
	 */
	private IntegerFeature(String attribute, Integer value) {
		super(attribute, value);
	}

	public static IntegerFeature createInstance(String attribute, int value) {
		return new IntegerFeature(attribute, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jwvl.phonlib.feature.Feature#getNullFeature(java.lang.String)
	 */
	@Override
	public Feature getNullFeature(String attribute) {
		// TODO Auto-generated method stub
		return new IntegerFeature(attribute, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.jwvl.phonlib.feature.AbstractFeature#valueEquals(eu.jwvl.phonlib.feature
	 * .Feature)
	 */
	@Override
	public boolean valueEquals(Feature other) {
		if (other instanceof IntegerFeature) {
			IntegerFeature o = (IntegerFeature) other;
			return o.getValue().equals(getValue());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#getValueString()
	 */
	@Override
	public String getValueString() {
		return getValue().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jwvl.phonlib.feature.Feature#getType()
	 */
	@Override
	public FeatureType getType() {
		return FeatureType.INTEGER;
	}

	/**
	 * @param attribute
	 * @param valueString
	 * @return
	 */
	public static Feature fromAttributeAndString(String attribute, String valueString) {

		try {
			int parsedInt = Integer.parseInt(valueString);
			return createInstance(attribute, parsedInt);
		} catch (Exception e) {
			return dummy.getNullFeature(attribute);
		}
	}

}

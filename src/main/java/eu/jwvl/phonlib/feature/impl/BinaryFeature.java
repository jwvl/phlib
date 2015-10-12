package eu.jwvl.phonlib.feature.impl;

import eu.jwvl.phonlib.feature.AbstractFeature;
import eu.jwvl.phonlib.feature.Feature;

public class BinaryFeature extends AbstractFeature<Boolean> {
	private static Feature dummy = BinaryFeature.createFalse("DUMMY");
	
	protected BinaryFeature(String attribute, Boolean value) {
		super(attribute, value);
	}
	
	public static BinaryFeature createTrue(String attribute) {
		return new BinaryFeature(attribute, Boolean.TRUE);
	}
	
	public static BinaryFeature createFalse(String attribute) {
		return new BinaryFeature(attribute, Boolean.FALSE);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#valueEquals(eu.jwvl.phonlib.feature.Feature)
	 */
	@Override
	public boolean valueEquals(Feature other) {
		if (other instanceof BinaryFeature) {
			return ((BinaryFeature) other).getValue().equals(getValue());
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getNullFeature()
	 */
	@Override
	public Feature getNullFeature(String attribute) {
		return new BinaryFeature(attribute, null);
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.AbstractFeature#getValueString()
	 */
	@Override
	public String getValueString() {
		return getValue().equals(Boolean.TRUE) ? "+" : "-";
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#getType()
	 */
	@Override
	public FeatureType getType() {
		return FeatureType.BINARY;
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

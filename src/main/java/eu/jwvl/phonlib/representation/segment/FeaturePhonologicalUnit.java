/**
 * 
 */
package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.feature.bundle.FeatureBundle;

/**
 * @author jwvl
 * @date Aug 8, 2015
 *
 */
public class FeaturePhonologicalUnit implements PhonologicalUnit {
	private final Phone phone;
	private final FeatureBundle features;

	/**
	 * @param features
	 */
	private FeaturePhonologicalUnit(Phone phone, FeatureBundle features) {
		this.phone = phone;
		this.features = features;
	}
	
	public static FeaturePhonologicalUnit create(Phone phone, FeatureBundle features) {
		return new FeaturePhonologicalUnit(phone, features);
	}
	
	public String toVerboseString() {
		return phone.toString()+" "+features.toString();
	}
	
	@Override
	public String toString() {
		return phone.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeaturePhonologicalUnit))
			return false;
		FeaturePhonologicalUnit other = (FeaturePhonologicalUnit) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	/**
	 * @return
	 */
	public FeatureBundle getFeatures() {
		return features;
	}


	@Override
	public String getSymbol() {
		return phone.toString();
	}
}

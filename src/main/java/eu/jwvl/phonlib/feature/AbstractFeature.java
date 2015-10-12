package eu.jwvl.phonlib.feature;

public abstract class AbstractFeature<O extends Object> implements Feature {

	private final String attribute;
	private final O value;
	
	public AbstractFeature(String attribute, O value) {

		this.attribute = attribute;
		this.value = value;
	}

	public O getValue() {
		return value;
	}

	public String getAttribute() {
		return attribute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFeature<?> other = (AbstractFeature<?>) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see eu.jwvl.phonlib.feature.Feature#valueEquals(eu.jwvl.phonlib.feature.Feature)
	 */
	@Override
	public abstract boolean valueEquals(Feature other);

	@Override
	public boolean isNull() {
		return value == null;
	}
	
	public String toString() {
		String valueString = isNull() ? "∅" : getValueString();
		return String.format("[%s]%s", valueString,attribute);
	}

	/**
	 * @return a String representation of the value
	 */
	public abstract String getValueString();
	
	

}
 
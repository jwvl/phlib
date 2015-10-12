/**
 * 
 */
package eu.jwvl.phonlib.symbol;

import eu.jwvl.phonlib.symbol.encoding.PhoneticEncoding;
import eu.jwvl.phonlib.symbol.encoding.PhoneticEncodingMap;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public abstract class PhoneticUnit {

	private final String classification;
	private final byte id;
	private static byte ID_COUNTER = Byte.MIN_VALUE;
	private final PhoneticEncodingMap encodingMap;
	static PhoneticEncoding DEFAULT_ENCODING = PhoneticEncoding.IPA;
	private PhoneticEncoding encoding;

	protected PhoneticUnit(PhoneticEncodingMap encodingMap, String classification) {
		this.id = ID_COUNTER++;
		this.encoding = DEFAULT_ENCODING;
		this.encodingMap = encodingMap;
		this.classification = classification;
	}

	public PhoneticEncodingMap getSymbolMap() {
		return encodingMap;
	}

	public byte getId() {
		return id;
	}

	/**
	 * @return the encoding
	 */
	public PhoneticEncoding getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding
	 *           the encoding to set
	 */
	public void setEncoding(PhoneticEncoding encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	public String toStringForEncoding(PhoneticEncoding phoneticEncoding) {
		return encodingMap.getForEncoding(phoneticEncoding);
	}

	@Override
	public String toString() {
		return toStringForEncoding(encoding);
	}

	public String toVerboseString() {
		return (id - Byte.MIN_VALUE) + ": " + toStringForEncoding(encoding);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PhoneticUnit))
			return false;
		PhoneticUnit other = (PhoneticUnit) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}

/**
 * 
 */
package eu.jwvl.phonlib.symbol;

import java.util.List;

import eu.jwvl.phonlib.symbol.encoding.PhoneticEncoding;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class PhoneticUnitCache {
	private PhoneticUnit[] map;
	private int size;
	
	public PhoneticUnitCache() {
		map = new PhoneticUnit[256];
		size = 0;
	}
	
	public PhoneticUnit getByID(byte id) {
		return map[id-Byte.MIN_VALUE];
	}
	
	public void addPhoneticUnit(PhoneticUnit toAdd) {
		map[size++] = toAdd;
	}

	/**
	 * @param dEFAULT_ENCODING
	 * @param string
	 * @return
	 */
	public PhoneticUnit getByString(PhoneticEncoding phoneticEncoding, String string) {
		for (int i=0; i < size; i++) {
			PhoneticUnit current = map[i];
			if (current != null & current.getSymbolMap().getForEncoding(phoneticEncoding).equals(string)) {
				return current;
			}
		}
		return null;
	}

	public int getSize() {
		return size;
	}

	/**
	 * @param symbols
	 * @return
	 */
	public PhoneticEncoding determineEncoding(List<String> symbols) {
		PhoneticEncoding[] values = {PhoneticEncoding.IPA, PhoneticEncoding.X_SAMPA, PhoneticEncoding.PRAAT};
		PhoneticEncoding currArgMax = values[0];
		int currMax = 0;
		int[] foundPerEncoding = new int[values.length];
		for (String symbol: symbols) {
			for (int valueCount =0; valueCount < values.length; valueCount++) {
				PhoneticEncoding phoneticEncoding = values[valueCount];
				if (getByString(phoneticEncoding,symbol) != null) {
					foundPerEncoding[valueCount]+=1;
					if (foundPerEncoding[valueCount] > currMax) {
						currMax = foundPerEncoding[valueCount];
						currArgMax = phoneticEncoding;
					}
				}
			}
		}
		return currArgMax;
		
	}
	
	
}

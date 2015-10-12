/**
 * 
 */
package eu.jwvl.phonlib.symbol.encoding;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class PhoneticEncodingMap {
	private Map<PhoneticEncoding, String> mappings;
	private final char asChar;

	private PhoneticEncodingMap(Map<PhoneticEncoding, String> mappings, char asChar) {
		this.mappings = mappings;
		this.asChar = asChar;
	}

	public static PhoneticEncodingMap createFromMap(Map<PhoneticEncoding, String> mappings) {
		String sampaString = mappings.get(PhoneticEncoding.X_SAMPA);
		char asChar = findAsChar(sampaString);
		return new PhoneticEncodingMap(mappings, asChar);
	}

	public static PhoneticEncodingMap createWithChar(Map<PhoneticEncoding, String> mappings, char asChar) {
		return new PhoneticEncodingMap(mappings, asChar);
	}

	public String getForEncoding(PhoneticEncoding phoneticEncoding) {
		return mappings.get(phoneticEncoding);
	}

	public char getAsChar() {
		return asChar;
	}

	private static char findAsChar(String toSearch) {
		char first = toSearch.charAt(0);
		if (Character.isLetterOrDigit(first)) {
			return first;
		} else {
			for (int i = 1; i < toSearch.length(); i++) {
				if (Character.isLetterOrDigit(toSearch.charAt(i))) {
					return toSearch.charAt(i);
				}

			}
			return first;

		}
	}

}

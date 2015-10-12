/**
 * 
 */
package eu.jwvl.phonlib.feature.impl;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public enum FeatureType {
	PRIVATIVE, BINARY, INTEGER, STRING;

	public static FeatureType determineType(Set<String> symbolSet) {
		symbolSet.remove("");
		symbolSet.remove("0");
		if (hasOnlyPrivativeSymbols(symbolSet)) {
			return PRIVATIVE;
		} else if (hasOnlyBinarySymbols(symbolSet)) {
			return BINARY;
		} else if (hasOnlyDigits(symbolSet)) {
			return INTEGER;
		} else {
			return STRING;
		}
	}

	private static boolean hasOnlyPrivativeSymbols(Set<String> symbolSet) {
		return symbolSet.size() == 1;
	}

	private static boolean hasOnlyBinarySymbols(Set<String> symbolSet) {
		if (symbolSet.size() == 2 && symbolSet.contains("1") && symbolSet.contains("-1")) {
			return true;
		} else if (symbolSet.size() == 2 && symbolSet.contains("-") && symbolSet.contains("+")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasOnlyDigits(Set<String> symbolSet) {
		for (String symbol : symbolSet) {
			try {
				Integer.parseInt(symbol);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}

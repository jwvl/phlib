/**
 * 
 */
package eu.jwvl.phonlib.app;

import eu.jwvl.phonlib.io.SymbolReader;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class PhoneticUnitReader {
	private static String symbolsPath = "ipa2xsampa.txt";
	
	public static void main(String[] args) {
		SymbolReader.initSymbolsFromFile(symbolsPath,"\t");
	}

}

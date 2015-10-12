/**
 * 
 */
package eu.jwvl.phonlib.symbol.types;

import eu.jwvl.phonlib.symbol.PhoneticUnit;
import eu.jwvl.phonlib.symbol.encoding.PhoneticEncodingMap;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class Boundary extends PhoneticUnit {

	/**
	 * @param encodingMap
	 * @param classification
	 */
	public Boundary(PhoneticEncodingMap encodingMap, String classification) {
		super(encodingMap, classification);
	}

}

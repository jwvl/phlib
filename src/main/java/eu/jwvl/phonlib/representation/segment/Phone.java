/**
 * 
 */
package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.types.Diacritic;
import eu.jwvl.phonlib.symbol.types.SpeechSound;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class Phone {
	private SpeechSound sound;
	private Diacritic diacritic;

	public String toString() {
		return sound.toString();
	}

}

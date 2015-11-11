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
	private final SpeechSound sound;
	private final Diacritic diacritic;

	private Phone(SpeechSound sound, Diacritic diacritic) {
		this.sound = sound;
		this.diacritic = diacritic;
	}

	public static Phone createFromSpeechSound(SpeechSound sound) {
		return new Phone(sound,Diacritic.NULL);
	}

	public String toString() {
		return sound.toString();
	}

}

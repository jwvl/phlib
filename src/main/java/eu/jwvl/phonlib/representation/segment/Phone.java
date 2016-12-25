/**
 * 
 */
package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.encoding.ByteEncodable;
import eu.jwvl.phonlib.symbol.types.Diacritic;
import eu.jwvl.phonlib.symbol.types.SpeechSound;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class Phone implements PhonologicalUnit, ByteEncodable {

	private final SpeechSound sound;
	private final Diacritic diacritic;
	private final byte byteValue;

	private static final byte idOffset = 0;
	private static final Phone[] list = new Phone[128+idOffset];
	private static byte idCounter = 0 - idOffset;

	private Phone(SpeechSound sound, Diacritic diacritic, byte byteValue) {
		this.sound = sound;
		this.diacritic = diacritic;
		this.byteValue = byteValue;
		list[byteValue-idOffset] = this;
	}


	public static Phone createFromSpeechSound(SpeechSound sound) {
		return new Phone(sound,Diacritic.NULL, idCounter++);
	}

	public static Phone createWithoutSound(SpeechSound sound) {
		return new Phone(null,Diacritic.NULL, idCounter++);
	}

	public String toString() {
		return sound.toString();
	}

	@Override
	public String getSymbol() {
		return toString();
	}

	@Override
	public byte getByteValue() {
		return byteValue;
	}
}

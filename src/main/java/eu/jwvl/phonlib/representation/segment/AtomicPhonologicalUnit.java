/**
 * 
 */
package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.types.SpeechSound;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class AtomicPhonologicalUnit implements PhonologicalUnit {
	private final SpeechSound phone;
	
	private AtomicPhonologicalUnit(SpeechSound phone){
		this.phone = phone;
	}

	public String toString() {
		return phone.toString();
	}

	@Override
	public String getSymbol() {
		return phone.toString();
	}
}

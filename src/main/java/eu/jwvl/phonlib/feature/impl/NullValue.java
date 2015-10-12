/**
 * 
 */
package eu.jwvl.phonlib.feature.impl;

/**
 * @author jwvl
 * @date Aug 28, 2015
 *
 */
public class NullValue {
	private static NullValue INSTANCE = new NullValue();
	private NullValue(){}
	
	public static NullValue getInstance() {
		return INSTANCE;
	}
	
	public String toString() {
		return "∅";
	}
	

}

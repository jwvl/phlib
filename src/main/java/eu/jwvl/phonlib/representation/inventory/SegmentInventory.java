/**
 * 
 */
package eu.jwvl.phonlib.representation.inventory;

import java.util.Set;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public abstract class SegmentInventory<AtomicForm> {
	private String name;
	private Set<AtomicForm> contents;

	/**
	 * @param name
	 * @param contents
	 */
	public SegmentInventory(String name, Set<AtomicForm> contents) {
		this.name = name;
		this.contents = contents;
	}
		

	public void addSegment(AtomicForm segment) {
		contents.add(segment);
	}

	/**
	 * 
	 */
	public void listContents() {
		for (AtomicForm s: contents) {
			System.out.println(s);
		}
	}
	
	public Set<AtomicForm> getContents() {
		return contents;
	}

}

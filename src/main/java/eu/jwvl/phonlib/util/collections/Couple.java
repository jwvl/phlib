package eu.jwvl.phonlib.util.collections;

/**
 * Simple helper class to represent a 2-tuple of objects of identical type.
 * Needed for e.g. alignment of forms in this project
 * @author jwvl, Nov 12, 2014
 * @param <T>
 */
public class Couple<T> {
	private final T left;
	private final T right;
	

	public static <T> Couple<T> of(T l, T r) {
		return new Couple<T>(l,r);
	}
	
	protected Couple(T l, T r) {
		left =l;
		right =r;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple<T> other = (Couple<T>) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}
	
	/**
	 * @return the left element of the pair
	 */
	public T getLeft() {
		return left;
	}
	
	public T getRight() {
		return right;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	public boolean isReverse(Couple<T> c) {
		return c.left.equals(right) && c.right.equals(left);
	}
	
	

	public Couple<T> makeReverse() {
		return new Couple<T>(right,left);
	}
	
	public boolean contains(T t) {
		return left.equals(t) || right.equals(t);
	}

	@Override
	public String toString() {
		return String.format("%s -- %s", left.toString(), right.toString());
	}
	
	
	
	
}

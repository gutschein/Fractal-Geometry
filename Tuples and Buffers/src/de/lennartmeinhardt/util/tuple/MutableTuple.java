package de.lennartmeinhardt.util.tuple;

/**
 * A basic mutable {@link Tuple} implementation.
 * 
 * @author Lennart Meinhardt
 *
 * @param <L> the left object's type
 * @param <R> the right object's type
 */
public class MutableTuple <L, R> implements Tuple<L, R> {

	// the objects
	private L left;
	private R right;
	
	
	/**
	 * Create a new {@link MutableTuple} with given objects.
	 * 
	 * @param left the left object
	 * @param right the right object
	 */
	public MutableTuple(L left, R right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Create a new empty {@link MutableTuple}.
	 */
	public MutableTuple() {
	}
	
	
	@Override public L getLeft() {
		return left;
	}
	
	@Override public R getRight() {
		return right;
	}
	
	public void setLeft(L left) {
		this.left = left;
	}
	
	public void setRight(R right) {
		this.right = right;
	}
	
	/**
	 * Set both objects at once.
	 * 
	 * @param left the new left object
	 * @param right the new right object
	 */
	public void setBoth(L left, R right) {
		setLeft(left);
		setRight(right);
	}
}

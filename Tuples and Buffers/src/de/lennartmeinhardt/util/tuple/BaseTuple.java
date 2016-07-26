package de.lennartmeinhardt.util.tuple;

/**
 * A base {@link Tuple} implementation.
 * 
 * @author Lennart Meinhardt
 *
 * @param <L> the left object's type
 * @param <R> the right object's type
 */
public class BaseTuple <L, R> implements Tuple<L, R> {

	// the objects
	private final L left;
	private final R right;
	
	
	/**
	 * Create a new {@link BaseTuple} with given left and right objects.
	 * 
	 * @param left the left object
	 * @param right the right object
	 */
	public BaseTuple(L left, R right) {
		this.left = left;
		this.right = right;
	}
	
	
	@Override public L getLeft() {
		return left;
	}
	
	@Override public R getRight() {
		return right;
	}
}

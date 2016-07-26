package de.lennartmeinhardt.util.tuple;

/**
 * A {@link Tuple} contains two objects, a left and a right one.
 * 
 * @author Lennart Meinhardt
 *
 * @param <L> the left object's type
 * @param <R> the right object's type
 */
public interface Tuple <L, R> {

	/**
	 * Get the left object.
	 * 
	 * @return the left object
	 */
	L getLeft();
	
	/**
	 * Get the right object
	 * 
	 * @return the right object
	 */
	R getRight();
}

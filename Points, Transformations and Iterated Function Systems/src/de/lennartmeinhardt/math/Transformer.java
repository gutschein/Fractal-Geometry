package de.lennartmeinhardt.math;

/**
 * A {@link Transformer} holds an object and transforms it.
 * 
 * @author Lennart Meinhardt
 *
 * @param <P> the type of object being transformed
 * @param <Q> the type of objects to reset
 * @param <T> the type of transformations that can be used
 */
public interface Transformer <P, Q, T> {

	/**
	 * Set the current object.
	 * 
	 * @param object the object to set
	 */
	void reset(Q object);
	
	/**
	 * Get the current object.
	 * 
	 * @return the object
	 */
	P get();
	
	/**
	 * Transform the current object.
	 * 
	 * @param transformation the transformation to use
	 */
	void transform(T transformation);
}

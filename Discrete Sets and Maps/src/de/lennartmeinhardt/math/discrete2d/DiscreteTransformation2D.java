package de.lennartmeinhardt.math.discrete2d;

/**
 * A {@link DiscreteTransformation2D} transforms two-dimensional discrete points to two-dimensional discrete points.
 * 
 * @author Lennart Meinhardt
 */
public interface DiscreteTransformation2D {

	/**
	 * Get the x coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the transformed x coordinate
	 */
	int transformX(int x, int y);
	
	/**
	 * Get the y coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the transformed y coordinate
	 */
	int transformY(int x, int y);
}

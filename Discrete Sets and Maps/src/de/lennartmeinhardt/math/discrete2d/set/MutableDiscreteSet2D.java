package de.lennartmeinhardt.math.discrete2d.set;

/**
 * A mutable discrete two-dimensional set. Points can be added and removed.
 * 
 * @author Lennart Meinhardt
 */
public interface MutableDiscreteSet2D extends DiscreteSet2D {

	/**
	 * Set if the given point will be contained in this set.
	 * If the point's contained set cannot be set (for example if it's out of range), false is returned.
	 * 
	 * @param x the point's x coordinate
	 * @param y the point's y coordinate
	 * @param contains true if the point is to be contained in this set
	 * @return true if the operation was successful
	 */
	boolean setContainsPointAt(int x, int y, boolean contains);
	
	/**
	 * Remove all points from this set.
	 * 
	 * @return true if the operation was successful
	 */
	boolean removeAll();
	
	
	/**
	 * Adds the point with given coordinates to this set.
	 * 
	 * @param x the point's x coordinate
	 * @param y the point's y coordinate
	 * @return true if the operation was successful
	 */
	default boolean addPointAt(int x, int y) {
		return setContainsPointAt(x, y, true);
	}
	
	/**
	 * Removes the point with given coordinates from this set.
	 * 
	 * @param x the point's x coordinate
	 * @param y the point's y coordinate
	 * @return true if the operation was successful
	 */
	default boolean removePointAt(int x, int y) {
		return setContainsPointAt(x, y, false);
	}
	
	/**
	 * Toggles the contained state of the point with given coordinates.
	 * If it was contained it will be removed, if it wasn't contained it will be added.
	 * 
	 * @param x the point's x coordinate
	 * @param y the point's y coordinate
	 * @return true if the operation was successful
	 */
	default boolean togglePointAt(int x, int y) {
		return setContainsPointAt(x, y, ! containsPointAt(x, y));
	}
}

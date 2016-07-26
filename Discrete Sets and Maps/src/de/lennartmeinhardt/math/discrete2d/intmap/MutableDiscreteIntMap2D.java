package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntUnaryOperator;

/**
 * A mutable discrete two-dimensional integer-valued map. Values on points can be set.
 * 
 * @author Lennart Meinhardt
 */
public interface MutableDiscreteIntMap2D extends DiscreteIntMap2D {

	/**
	 * Set the value of the given point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value to set
	 * @return true if the operation was successful
	 */
	boolean setValueAt(int x, int y, int value);
	
	/**
	 * Set this map to a constant value.
	 * 
	 * @param value the constant value to set
	 * @return true if the operation was successful
	 */
	boolean setToConstantValue(int value);
	
	
	/**
	 * Set this map to zero.
	 * 
	 * @return true if the operation was successful
	 */
	default boolean setToZero() {
		return setToConstantValue(0);
	}

	/**
	 * Add a number to the value at given position.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param increaseBy number to add to the value
	 * @return true if the operation was successful
	 */
	default boolean addToValueAt(int x, int y, int increaseBy) {
		int newValue = increaseBy + getValueAt(x, y);
		return setValueAt(x, y, newValue);
	}
	
	/**
	 * Remap the value at given position.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param function the function to remap with
	 * @return true if the operation was successful
	 */
	default boolean remapValueAt(int x, int y, IntUnaryOperator function) {
		int newValue = function.applyAsInt(getValueAt(x, y));
		return setValueAt(x, y, newValue);
	}

	/**
	 * Increments the value at given position.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the operation was successful
	 */
	default boolean incrementValueAt(int x, int y) {
		return addToValueAt(x, y, 1);
	}

	/**
	 * Decrements the value at given position.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the operation was successful
	 */
	default boolean decrementValueAt(int x, int y) {
		return addToValueAt(x, y, -1);
	}
}

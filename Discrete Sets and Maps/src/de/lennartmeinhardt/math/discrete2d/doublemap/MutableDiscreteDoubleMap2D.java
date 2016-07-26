package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleUnaryOperator;

/**
 * A mutable discrete two-dimensional double-valued map. Values on points can be set.
 * 
 * @author Lennart Meinhardt
 */
public interface MutableDiscreteDoubleMap2D extends DiscreteDoubleMap2D {

	/**
	 * Set the value of the given point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value to set
	 * @return true if the operation was successful
	 */
	boolean setValueAt(int x, int y, double value);

	/**
	 * Set this map to a constant value.
	 * 
	 * @param value the constant value to set
	 * @return true if the operation was successful
	 */
	boolean setToConstantValue(double value);
	
	
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
	default boolean addToValueAt(int x, int y, double increaseBy) {
		double newValue = increaseBy + getValueAt(x, y);
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
	default boolean remapValueAt(int x, int y, DoubleUnaryOperator function) {
		double newValue = function.applyAsDouble(getValueAt(x, y));
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

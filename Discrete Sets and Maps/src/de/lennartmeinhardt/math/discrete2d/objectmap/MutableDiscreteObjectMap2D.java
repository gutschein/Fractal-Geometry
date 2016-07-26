package de.lennartmeinhardt.math.discrete2d.objectmap;

import java.util.function.Function;

/**
 * A mutable discrete two-dimensional object-valued map. Values on points can be set.
 * 
 * @author Lennart Meinhardt
 */
public interface MutableDiscreteObjectMap2D <T> extends DiscreteObjectMap2D<T> {

	/**
	 * Set the value of the given point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value to set
	 * @return true if the operation was successful
	 */
	boolean setValueAt(int x, int y, T value);
	
	/**
	 * Set this map to a constant value.
	 * 
	 * @param value the constant value to set
	 * @return true if the operation was successful
	 */
	boolean setToConstantValue(T value);
	
	
	/**
	 * Set this map to null.
	 * 
	 * @return true if the operation was successful
	 */
	default boolean setToNull() {
		return setToConstantValue(null);
	}
	
	/**
	 * Remap the value at given position.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param function the function to remap with
	 * @return true if the operation was successful
	 */
	default boolean remapValueAt(int x, int y, Function<? super T, ? extends T> function) {
		T newValue = function.apply(getValueAt(x, y));
		return setValueAt(x, y, newValue);
	}
}

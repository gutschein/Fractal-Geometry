package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntConsumer;

/**
 * This object has integer values and can run operations on them.
 * 
 * @author Lennart Meinhardt
 */
public interface OperableIntValues {
	
	/**
	 * Performs an operation on the values.
	 * 
	 * @param consumer the operation to perform
	 */
	void forEachValue(IntConsumer consumer);
	
	
	/**
	 * Get the maximum value.
	 * 
	 * @return the maximum value
	 */
	default int getMaximumValue() {
		MaximumIntFinder finder = new MaximumIntFinder();
		forEachValue(finder);
		return finder.getMaximumValue();
	}

	/**
	 * Get the minimum value.
	 * 
	 * @return the minimum value
	 */
	default int getMinimumValue() {
		MinimumIntFinder finder = new MinimumIntFinder();
		forEachValue(finder);
		return finder.getMinimumValue();
	}
	
	/**
	 * Get the sum of values.
	 * 
	 * @return the value sum
	 */
	default long getSum() {
		LongSum calculator = new LongSum();
		forEachValue(calculator);
		return calculator.getSum();
	}
}

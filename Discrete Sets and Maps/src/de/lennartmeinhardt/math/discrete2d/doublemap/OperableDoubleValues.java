package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleConsumer;

/**
 * This object has double values and can run operations on them.
 * 
 * @author Lennart Meinhardt
 */
public interface OperableDoubleValues {

	/**
	 * Performs an operation on the values.
	 * 
	 * @param consumer the operation to perform
	 */
	void forEachValue(DoubleConsumer operation);
	

	/**
	 * Get the maximum value.
	 * 
	 * @return the maximum value
	 */
	default double getMaximumValue() {
		MaximumDoubleFinder finder = new MaximumDoubleFinder();
		forEachValue(finder);
		return finder.getMaximumValue();
	}

	/**
	 * Get the minimum value.
	 * 
	 * @return the minimum value
	 */
	default double getMinimumValue() {
		MinimumDoubleFinder finder = new MinimumDoubleFinder();
		forEachValue(finder);
		return finder.getMinimumValue();
	}

	/**
	 * Get the sum of values.
	 * 
	 * @return the value sum
	 */
	default double getSum() {
		DoubleSum calculator = new DoubleSum();
		forEachValue(calculator);
		return calculator.getSum();
	}
}

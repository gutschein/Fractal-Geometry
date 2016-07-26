package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleConsumer;

/**
 * An operation that sums floating point numbers.
 * 
 * @author Lennart Meinhardt
 */
public class DoubleSum implements DoubleConsumer {

	// the sum calculated so far
	private double sum = 0;
	
	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		sum = 0;
	}
	
	@Override public void accept(double value) {
		sum += value;
	}
	
	/**
	 * Get the sum calculated so far.
	 * 
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}
}

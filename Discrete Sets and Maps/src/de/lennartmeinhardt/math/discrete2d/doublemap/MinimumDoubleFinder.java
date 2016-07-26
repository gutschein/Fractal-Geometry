package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleConsumer;

/**
 * An operation that finds the minimum double value.
 * 
 * @author Lennart Meinhardt
 */
public class MinimumDoubleFinder implements DoubleConsumer {

	// the minimum so far
	private double minimum;

	
	/**
	 * Create a new {@link MinimumDoubleFinder}.
	 */
	public MinimumDoubleFinder() {
		reset();
	}

	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		minimum = Double.MAX_VALUE;
	}
	
	@Override public void accept(double i) {
		if(i < minimum)
			minimum = i;
	}

	/**
	 * Get the minimum so far.
	 * 
	 * @return the minimum
	 */
	public double getMinimumValue() {
		return minimum;
	}
}

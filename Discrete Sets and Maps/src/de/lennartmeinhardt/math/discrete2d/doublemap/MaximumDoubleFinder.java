package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleConsumer;

/**
 * An operation that finds the maximum double value.
 * 
 * @author Lennart Meinhardt
 */
public class MaximumDoubleFinder implements DoubleConsumer {

	// the maximum so far
	private double maximum;

	
	/**
	 * Create a new {@link MaximumDoubleFinder}.
	 */
	public MaximumDoubleFinder() {
		reset();
	}

	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		maximum = Double.MIN_VALUE;
	}
	
	@Override public void accept(double i) {
		if(i > maximum)
			maximum = i;
	}

	/**
	 * Get the maximum so far.
	 * 
	 * @return the maximum
	 */
	public double getMaximumValue() {
		return maximum;
	}
}

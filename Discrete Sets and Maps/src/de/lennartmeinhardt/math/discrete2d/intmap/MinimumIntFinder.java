package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntConsumer;

/**
 * An operation that finds the minimum integer value.
 * 
 * @author Lennart Meinhardt
 */
public class MinimumIntFinder implements IntConsumer {

	// the minimum so far
	private int minimum;

	
	/**
	 * Create a new {@link MinimumIntFinder}.
	 */
	public MinimumIntFinder() {
		reset();
	}

	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		minimum = Integer.MAX_VALUE;
	}
	
	@Override public void accept(int i) {
		if(i < minimum)
			minimum = i;
	}

	/**
	 * Get the minimum so far.
	 * 
	 * @return the minimum
	 */
	public int getMinimumValue() {
		return minimum;
	}
}

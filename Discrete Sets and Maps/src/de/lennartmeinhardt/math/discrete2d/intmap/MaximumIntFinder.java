package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntConsumer;

/**
 * An operation that finds the maximum integer value.
 * 
 * @author Lennart Meinhardt
 */
public class MaximumIntFinder implements IntConsumer {
	
	// the maximum so far
	private int maximum;
	
	
	/**
	 * Create a new {@link MaximumIntFinder}.
	 */
	public MaximumIntFinder() {
		reset();
	}
	
	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		maximum = Integer.MIN_VALUE;
	}
	
	@Override public void accept(int i) {
		if(i > maximum)
			maximum = i;
	}
	
	/**
	 * Get the maximum so far.
	 * 
	 * @return the maximum
	 */
	public int getMaximumValue() {
		return maximum;
	}
}

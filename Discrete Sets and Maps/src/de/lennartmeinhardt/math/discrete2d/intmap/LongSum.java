package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * An operation that sums integers numbers.
 * 
 * @author Lennart Meinhardt
 */
public class LongSum implements LongConsumer, IntConsumer {

	// the sum calculated so far
	private long sum = 0;
	
	
	/**
	 * Reset the calculation.
	 */
	public void reset() {
		sum = 0;
	}
	
	@Override public void accept(long value) {
		sum += value;
	}

	@Override public void accept(int value) {
		accept((long) value);
	}
	
	/**
	 * Get the sum calculated so far.
	 * 
	 * @return the sum
	 */
	public long getSum() {
		return sum;
	}
}

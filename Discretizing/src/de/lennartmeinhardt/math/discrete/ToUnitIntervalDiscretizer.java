package de.lennartmeinhardt.math.discrete;

/**
 * A {@link Discretizer} that maps a given discrete interval to the unit interval [0, 1].
 * 
 * @author Lennart Meinhardt
 */
public class ToUnitIntervalDiscretizer implements Discretizer {
	
	private final int intervalLength;
	private final int intervalOffset;
	
	
	/**
	 * Create a new {@link ToUnitIntervalDiscretizer} with given interval data.
	 * 
	 * @param intervalLength the interval length
	 * @param intervalOffset the interval offset
	 */
	public ToUnitIntervalDiscretizer(int intervalLength, int intervalOffset) {
		this.intervalLength = intervalLength;
		this.intervalOffset = intervalOffset;
	}
	
	/**
	 * Create a new {@link ToUnitIntervalDiscretizer} with interval of given length, at the origin.
	 * 
	 * @param intervalLength the interval length
	 */
	public ToUnitIntervalDiscretizer(int intervalLength) {
		this(intervalLength, 0);
	}
	
	
	@Override public double deDiscretize(int absolute) {
		return (1d + 2 * (absolute - intervalOffset)) / (2 * intervalLength);
	}
	
	@Override public int discretize(double relative) {
		int a = (int) Math.floor(relative * intervalLength);
		a = Math.min(a, intervalLength - 1);
		a = Math.max(a, 0);
		return intervalOffset + a;
	}
}

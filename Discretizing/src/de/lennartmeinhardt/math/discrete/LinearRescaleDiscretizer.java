package de.lennartmeinhardt.math.discrete;

/**
 * A {@link Discretizer} that linearly rescales the values.
 * 
 * @author Lennart Meinhardt
 */
public class LinearRescaleDiscretizer implements Discretizer {
	
	private final double scaling;
	
	
	/**
	 * Create a new {@link LinearRescaleDiscretizer} with given scaling.
	 * 
	 * @param scaling the relative to absolute scaling
	 */
	public LinearRescaleDiscretizer(double scaling) {
		this.scaling = scaling;
	}
	
	@Override public double deDiscretize(int absolute) {
		return absolute / scaling;
	}
	
	@Override public int discretize(double relative) {
		return (int) Math.round(relative * scaling);
	}
}

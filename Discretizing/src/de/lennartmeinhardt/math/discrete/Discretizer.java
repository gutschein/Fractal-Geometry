package de.lennartmeinhardt.math.discrete;

/**
 * A {@link Discretizer} maps between integer and double values.
 * Mapping from double to int is called discretizing, mapping from int to double is called de-discretizing.
 * In order to work properly the concatenation of discretization after de-discretization should be the identity on integers. 
 * 
 * @author Lennart Meinhardt
 *
 */
public interface Discretizer {

	/**
	 * Discretize the given value.
	 * 
	 * @param value the value to discretize
	 * @return the discretized value
	 */
	int discretize(double value);
	
	/**
	 * De-discretize the given value.
	 * 
	 * @param value the value to de-discretize
	 * @return the de-discretized value
	 */
	double deDiscretize(int value);
}

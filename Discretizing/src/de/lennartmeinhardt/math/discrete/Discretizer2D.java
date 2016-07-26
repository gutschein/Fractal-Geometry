package de.lennartmeinhardt.math.discrete;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;

/**
 * A {@link Discretizer2D} does discretize and de-discretize two-dimensional points. Different methods for discretizing the coordinates may be used.
 * 
 * @author Lennart Meinhardt
 */
public interface Discretizer2D {

	/**
	 * Discretize the given value in x direction.
	 * 
	 * @param value the x coordinate to discretize
	 * @return the x discretization
	 */
	int discretizeX(double value);
	/**
	 * Discretize the given value in y direction.
	 * 
	 * @param value the y coordinate to discretize
	 * @return the y discretization
	 */
	int discretizeY(double value);
	
	/**
	 * De-discretize the given value in x direction.
	 * 
	 * @param value the x coordinate to de-discretize
	 * @return the x de-discretization
	 */
	double deDiscretizeX(int value);
	/**
	 * De-discretize the given value in y direction.
	 * 
	 * @param value the y coordinate to de-discretize
	 * @return the y de-discretization
	 */
	double deDiscretizeY(int value);
	
	
	/**
	 * De-discretize the given discrete transformation.
	 * The obtained point transformation will operate by discretizing, applying the discrete transformation, and then de-discretizing again.
	 * 
	 * @param transformation the discrete transformation to de-discretize
	 * @return a point transformation operating by applying the discrete transformation on discretized points
	 */
	default PointTransformation2D deDiscretize(DiscreteTransformation2D transformation) {
		return new DeDiscretizedTransformation2D(transformation, this);
	}
	
	/**
	 * Discretize the given point transformation.
	 * The obtained discrete transformation will operate by de-discretizing, applying the point transformation, and then discretizing again.
	 * 
	 * @param transformation the point transformation to discretize
	 * @return a discrete transformatoin operating by applying the point transformation on de-discretized points
	 */
	default DiscreteTransformation2D discretize(PointTransformation2D transformation) {
		return new DiscretizedTransformation2D(transformation, this);
	}
}

package de.lennartmeinhardt.math.discrete;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;

/**
 * A {@link PointTransformation2D} that acts on points by discretizing, applying a discrete transformation and then de-discretizing again.
 * 
 * @author Lennart Meinhardt
 */
public class DeDiscretizedTransformation2D implements PointTransformation2D {

	// the discrete transformation to use
	private final DiscreteTransformation2D transformation;
	// the discretizer to use
	private final Discretizer2D discretizer;

	
	/**
	 * Create a new {@link DeDiscretizedTransformation2D} with given discrete transformation and discretizer.
	 * 
	 * @param transformation the discrete transformation to use
	 * @param discretizer the discretizer to use
	 */
	public DeDiscretizedTransformation2D(DiscreteTransformation2D transformation, Discretizer2D discretizer) {
		this.transformation = transformation;
		this.discretizer = discretizer;
	}
	
	
	@Override public double transformX(double x, double y) {
		int xAbs = discretizer.discretizeX(x);
		int yAbs = discretizer.discretizeY(y);
		int xAbsTransformed = transformation.transformX(xAbs, yAbs);
		return discretizer.deDiscretizeX(xAbsTransformed);
	}

	@Override public double transformY(double x, double y) {
		int xAbs = discretizer.discretizeX(x);
		int yAbs = discretizer.discretizeY(y);
		int yAbsTransformed = transformation.transformY(xAbs, yAbs);
		return discretizer.deDiscretizeY(yAbsTransformed);
	}
}

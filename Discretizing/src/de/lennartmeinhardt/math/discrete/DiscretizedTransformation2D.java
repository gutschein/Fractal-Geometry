package de.lennartmeinhardt.math.discrete;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;

/**
 * A {@link DiscreteTransformation2D} that acts on points by de-discretizing, applying a point transformation and then discretizing again.
 * 
 * @author Lennart Meinhardt
 */
public class DiscretizedTransformation2D implements DiscreteTransformation2D {
	
	// the point transformation to use
	private final PointTransformation2D transformation;
	// the discretizer to use
	private final Discretizer2D discretizer;
	
	
	/**
	 * Create a new {@link DiscretizedTransformation2D} with given point transformation and discretizer.
	 * 
	 * @param transformation the point transformation to use
	 * @param discretizer the discretizer to use
	 */
	public DiscretizedTransformation2D(PointTransformation2D transformation, Discretizer2D discretizer) {
		this.transformation = transformation;
		this.discretizer = discretizer;
	}
	

	@Override public int transformX(int x, int y) {
		double xRel = discretizer.deDiscretizeX(x);
		double yRel = discretizer.deDiscretizeY(y);
		double xRelTransformed = transformation.transformX(xRel, yRel);
		return discretizer.discretizeX(xRelTransformed);
	}
	
	@Override public int transformY(int x, int y) {
		double xRel = discretizer.deDiscretizeX(x);
		double yRel = discretizer.deDiscretizeY(y);
		double yRelTransformed = transformation.transformY(xRel, yRel);
		return discretizer.discretizeY(yRelTransformed);
	}
}

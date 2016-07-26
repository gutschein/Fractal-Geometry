package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A {@link DiscreteSet2D} that is bounded by a rectangle. The rectangle is used for operating on points.
 * 
 * @author Lennart Meinhardt
 */
public interface RectangularBoundsDiscreteSet2D extends OperableDiscreteSet2D {

	/**
	 * Get this set's rectangular bounds.
	 * 
	 * @return the bounds
	 */
	DiscreteRectangle getBounds();
	

	@Override default void forEachPoint(DiscreteConsumer2D operation) {
		getBounds().forEachPoint(operation.when(this::containsPointAt));
	}
}

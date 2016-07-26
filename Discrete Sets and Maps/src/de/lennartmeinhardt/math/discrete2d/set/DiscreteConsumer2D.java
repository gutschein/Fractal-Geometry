package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;

/**
 * A consumer that takes discrete points.
 * It may be considered an operation on discrete points that returns no result.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteConsumer2D {
	
	/**
	 * Performs this operation on the given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	void accept(int x, int y);
	
	
	/**
	 * Get the composed discrete consumer that operates by applying this operation, and then the given one.
	 * 
	 * @param other the other discrete consumer
	 * @return composed discrete consumer
	 */
	default DiscreteConsumer2D andThen(DiscreteConsumer2D other) {
		return (x, y) -> {
			this.accept(x, y);
			other.accept(x, y);
		};
	}
	
	/**
	 * Get the discrete consumer that operates on a point by first applying a transformation and then performing this operation on the result.
	 * 
	 * @param transformation the transformation to pre-transform with
	 * @return discrete consumer that pre-transforms input
	 */
	default DiscreteConsumer2D preTransform(DiscreteTransformation2D transformation) {
		return (x, y) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			accept(xTransformed, yTransformed);
		};
	}
	
	/**
	 * Performs this operation only on points for which the given condition is satisfied.
	 * 
	 * @param condition the condition on points to operate on
	 * @return the discrete consumer performing only when the condition is satisfied
	 */
	default DiscreteConsumer2D when(DiscretePredicate2D condition) {
		return (x, y) -> {
			if(condition.test(x, y))
				this.accept(x, y);
		};
	}
}

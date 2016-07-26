package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;

/**
 * A consumer that takes discrete points and boolean values.
 * It may be considered an operation on tuples of discrete points and booleans that returns no result.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteBooleanConsumer2D {

	/**
	 * Performs this operation on the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value
	 */
	void accept(int x, int y, boolean value);
	
	
	/**
	 * Get the composed consumer that operates by applying this operation, and then the given one.
	 * 
	 * @param other the other consumer
	 * @return composed consumer
	 */
	default DiscreteBooleanConsumer2D andThen(DiscreteBooleanConsumer2D that) {
		return (x, y, value) -> {
			this.accept(x, y, value);
			that.accept(x, y, value);
		};
	}
	
	/**
	 * Get the consumer that operates only when the point satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when point is tested with positive result
	 */
	default DiscreteBooleanConsumer2D whenPointSatisfies(DiscretePredicate2D condition) {
		return (x, y, b) -> {
			if(condition.test(x, y))
				this.accept(x, y, b);
		};
	}
	
	/**
	 * Get the consumer that operates by performing this operation with a fixed boolean value.
	 * 
	 * @param value the value to use for operating
	 * @return the consumer of fixed boolean value
	 */
	default DiscreteConsumer2D withFixedValue(boolean value) {
		return (x, y) -> this.accept(x, y, value);
	}
	
	/**
	 * Get the consumer that operates by performing this operation with a boolean value from a predicate.
	 * 
	 * @param condition the set generating the boolean values
	 * @return the consumer of value from predicate
	 */
	default DiscreteConsumer2D withSetValue(DiscreteSet2D condition) {
		return (x, y) -> this.accept(x, y, condition.containsPointAt(x, y));
	}
	
	/**
	 * Get the consumer that operates on a point by first applying a transformation to the point and then performing this operation on the result.
	 * The value is not transformed.
	 * 
	 * @param transformation the transformation to pre-transform with
	 * @return consumer that pre-transforms the point
	 */
	default DiscreteBooleanConsumer2D preTransformPoint(DiscreteTransformation2D transformation) {
		return (x, y, value) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			accept(xTransformed, yTransformed, value);
		};
	}
}

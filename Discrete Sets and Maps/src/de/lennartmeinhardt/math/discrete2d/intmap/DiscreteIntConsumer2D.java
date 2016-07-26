package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A consumer that takes discrete points and integer values.
 * It may be considered an operation on tuples of discrete points and integer that returns no result.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteIntConsumer2D {
	
	/**
	 * Performs this operation on the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value
	 */
	void accept(int x, int y, int value);
	
	
	/**
	 * Get the composed consumer that operates by applying this operation, and then the given one.
	 * 
	 * @param other the other consumer
	 * @return composed consumer
	 */
	default DiscreteIntConsumer2D andThen(DiscreteIntConsumer2D other) {
		return (x, y, i) -> {
			this.accept(x, y, i);
			other.accept(x, y, i);
		};
	}
	
	/**
	 * Get the consumer that operates only when a predicate is tested with positive result.
	 * 
	 * @param condition the condition for points to operate on
	 * @return consumer that operates only when condition is satisfied
	 */
	default DiscreteIntConsumer2D when(DiscreteIntPredicate2D condition) {
		return (x, y, i) -> {
			if(condition.test(x, y, i))
				accept(x, y, i);
		};
	}
	
	/**
	 * Get the consumer that operates only when the value satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when value is tested with positive result
	 */
	default DiscreteIntConsumer2D whenValueSatisfies(IntPredicate condition) {
		return when(DiscreteIntPredicate2D.ofValuePredicate(condition));
	}

	/**
	 * Get the consumer that operates only when the point satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when point is tested with positive result
	 */
	default DiscreteIntConsumer2D whenPointSatisfies(DiscretePredicate2D condition) {
		return when(DiscreteIntPredicate2D.ofPointPredicate(condition));
	}
	
	/**
	 * Get the consumer that operates by performing this operation with a fixed integer value.
	 * 
	 * @param value the value to use for operating
	 * @return the consumer of fixed integer value
	 */
	default DiscreteConsumer2D withFixedValue(int value) {
		return (x, y) -> this.accept(x, y, value);
	}
	
	/**
	 * Get the consumer that operates by performing this operation with an integer value from a {@link DiscreteIntMap2D}.
	 * 
	 * @param condition the predicate generating integer values
	 * @return the consumer of value from predicate
	 */
	default DiscreteConsumer2D withIntMapValue(DiscreteIntMap2D valueMap) {
		return (x, y) -> this.accept(x, y, valueMap.getValueAt(x, y));
	}
	
	/**
	 * Get the consumer that operates on a point by first applying a transformation to the point and then performing this operation on the result.
	 * The value is not transformed.
	 * 
	 * @param transformation the transformation to pre-transform with
	 * @return consumer that pre-transforms the point
	 */
	default DiscreteIntConsumer2D preTransformPoint(DiscreteTransformation2D transformation) {
		return (x, y, i) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			this.accept(xTransformed, yTransformed, i);
		};
	}
	
	/**
	 * Get the consumer that operates on a point by first applying a transformation to the value and then performing this operation on the result.
	 * The point is not transformed.
	 * 
	 * @param function the function to apply to values
	 * @return consumer with pre-transformed value
	 */
	default DiscreteIntConsumer2D preTransformValue(IntUnaryOperator function) {
		return (x, y, d) -> this.accept(x, y, function.applyAsInt(d));
	}
}

package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A consumer that takes discrete points and double values.
 * It may be considered an operation on tuples of discrete points and doubles that returns no result.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteDoubleConsumer2D {
	
	/**
	 * Performs this operation on the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value
	 */
	void accept(int x, int y, double value);
	
	
	/**
	 * Get the composed consumer that operates by applying this operation, and then the given one.
	 * 
	 * @param other the other discrete double consumer
	 * @return composed consumer
	 */
	default DiscreteDoubleConsumer2D andThen(DiscreteDoubleConsumer2D other) {
		return (x, y, d) -> {
			this.accept(x, y, d);
			other.accept(x, y, d);
		};
	}

	/**
	 * Get the consumer that operates only when a predicate is tested with positive result.
	 * 
	 * @param condition the condition for points to operate on
	 * @return consumer that operates only when condition is satisfied
	 */
	default DiscreteDoubleConsumer2D when(DiscreteDoublePredicate2D condition) {
		return (x, y, d) -> {
			if(condition.test(x, y, d))
				accept(x, y, d);
		};
	}
	
	/**
	 * Get the consumer that operates only when the value satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when value is tested with positive result
	 */
	default DiscreteDoubleConsumer2D whenValueSatisfies(DoublePredicate condition) {
		return when(DiscreteDoublePredicate2D.ofValuePredicate(condition));
	}

	/**
	 * Get the consumer that operates only when the point satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when point is tested with positive result
	 */
	default DiscreteDoubleConsumer2D whenPointSatisfies(DiscretePredicate2D condition) {
		return when(DiscreteDoublePredicate2D.ofPointPredicate(condition));
	}
	
	/**
	 * Get the consumer that operates by performing this operation with a fixed double value.
	 * 
	 * @param value the value to use for operating
	 * @return the discrete consumer of fixed double value
	 */
	default DiscreteConsumer2D withFixedValue(double value) {
		return (x, y) -> this.accept(x, y, value);
	}

	/**
	 * Get the consumer that operates by performing this operation with an double value from a {@link DiscreteDoubleMap2D}.
	 * 
	 * @param condition the predicate generating double values
	 * @return the consumer of value from predicate
	 */
	default DiscreteConsumer2D withDoubleMapValue(DiscreteDoubleMap2D valueMap) {
		return (x, y) -> this.accept(x, y, valueMap.getValueAt(x, y));
	}
	
	/**
	 * Get the double consumer that operates on a point by first applying a transformation to the point and then performing this operation on the result.
	 * The value is not transformed.
	 * 
	 * @param transformation the transformation to pre-transform with
	 * @return consumer that pre-transforms the point
	 */
	default DiscreteDoubleConsumer2D preTransformPoint(DiscreteTransformation2D transformation) {
		return (x, y, d) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			this.accept(xTransformed, yTransformed, d);
		};
	}
	
	/**
	 * Get the consumer that operates on a point by first applying a transformation to the value and then performing this operation on the result.
	 * The point is not transformed.
	 * 
	 * @param function the function to apply to values
	 * @return consumer with pre-transformed value
	 */
	default DiscreteDoubleConsumer2D preTransformValue(DoubleUnaryOperator function) {
		return (x, y, d) -> this.accept(x, y, function.applyAsDouble(d));
	}
}

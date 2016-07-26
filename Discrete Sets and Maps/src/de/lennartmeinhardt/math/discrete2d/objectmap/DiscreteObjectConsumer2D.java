package de.lennartmeinhardt.math.discrete2d.objectmap;

import java.util.function.Function;
import java.util.function.Predicate;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A consumer that takes discrete points and object values.
 * It may be considered an operation on tuples of discrete points and objects that returns no result.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteObjectConsumer2D <T> {

	/**
	 * Performs this operation on the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value
	 */
	void accept(int x, int y, T value);

	
	/**
	 * Get the composed consumer that operates by applying this operation, and then the given one.
	 * 
	 * @param other the other consumer
	 * @return composed consumer
	 */
	default <S extends T> DiscreteObjectConsumer2D<S> andThen(DiscreteObjectConsumer2D<? super S> other) {
		return (x, y, s) -> {
			this.accept(x, y, s);
			other.accept(x, y, s);
		};
	}

	/**
	 * Get the consumer that operates only when a predicate is tested with positive result.
	 * 
	 * @param condition the condition for points to operate on
	 * @return consumer that operates only when condition is satisfied
	 */
	default <S extends T> DiscreteObjectConsumer2D<S> when(DiscreteObjectPredicate2D<? super S> condition) {
		return (x, y, s) -> {
			if(condition.test(x, y, s))
				accept(x, y, s);
		};
	}
	
	/**
	 * Get the consumer that operates only when the value satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when value is tested with positive result
	 */
	default DiscreteObjectConsumer2D<T> whenValueSatisfies(Predicate<? super T> condition) {
		return when(DiscreteObjectPredicate2D.ofValuePredicate(condition));
	}

	/**
	 * Get the consumer that operates only when the point satisfies a condition.
	 * 
	 * @param condition the condition to satisfy
	 * @return consumer that operates only when point is tested with positive result
	 */
	default DiscreteObjectConsumer2D<T> whenPointSatisfies(DiscretePredicate2D condition) {
		return when(DiscreteObjectPredicate2D.ofPointPredicate(condition));
	}
	
	/**
	 * Get the consumer that operates by performing this operation with a fixed value.
	 * 
	 * @param value the value to use for operating
	 * @return the discrete consumer of fixed double value
	 */
	default DiscreteConsumer2D withFixedValue(T value) {
		return (x, y) -> this.accept(x, y, value);
	}

	/**
	 * Get the consumer that operates by performing this operation with an double value from a {@link DiscreteDoubleMap2D}.
	 * 
	 * @param condition the predicate generating double values
	 * @return the consumer of value from predicate
	 */
	default DiscreteConsumer2D withDoubleMapValue(DiscreteObjectMap2D<? extends T> valueMap) {
		return (x, y) -> this.accept(x, y, valueMap.getValueAt(x, y));
	}

	/**
	 * Get the consumer that operates on a point by first applying a transformation to the point and then performing this operation on the result.
	 * The value is not transformed.
	 * 
	 * @param transformation the transformation to pre-transform with
	 * @return consumer that pre-transforms the point
	 */
	default DiscreteObjectConsumer2D<T> preTransformPoint(DiscreteTransformation2D transformation) {
		return (x, y, t) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			this.accept(xTransformed, yTransformed, t);
		};
	}
	
	/**
	 * Get the consumer that operates on a point by first applying a transformation to the value and then performing this operation on the result.
	 * The point is not transformed.
	 * 
	 * @param function the function to apply to values
	 * @return consumer with pre-transformed value
	 */
	default <U> DiscreteObjectConsumer2D<U> preTransformValue(Function<? super U, ? extends T> function) {
		return (x, y, t) -> this.accept(x, y, function.apply(t));
	}
}

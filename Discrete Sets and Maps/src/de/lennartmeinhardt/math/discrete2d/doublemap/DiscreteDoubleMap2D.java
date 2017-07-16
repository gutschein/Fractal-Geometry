package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.objectmap.DiscreteObjectMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A map from discrete two-dimensional points to double values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteDoubleMap2D {

	/**
	 * Get the value at given discrete point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the value at the specified point
	 */
	double getValueAt(int x, int y);

	
	/**
	 * Consider this map as a map of {@link Double}s.
	 * 
	 * @return this map as {@link Double} map
	 */
	default DiscreteObjectMap2D<Double> asObjectMap() {
		return pushForwardToObject(Double::new);
	}

	/**
	 * Get the map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return double map with remapped values
	 */
	default DiscreteDoubleMap2D pushForward(DoubleUnaryOperator function) {
		return (x, y) -> function.applyAsDouble(getValueAt(x, y));
	}

	/**
	 * Get the integer map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return integer map with remapped values
	 */
	default DiscreteIntMap2D pushForwardToInt(DoubleToIntFunction function) {
		return (x, y) -> function.applyAsInt(getValueAt(x, y));
	}

	/**
	 * Get the object map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return object map with remapped values
	 */
	default <T> DiscreteObjectMap2D<T> pushForwardToObject(DoubleFunction<T> function) {
		return (x, y) -> function.apply(getValueAt(x, y));
	}

	/**
	 * Get the pullback of this map under the given transformation.
	 * 
	 * @param transformation the transformation
	 * @return the pullback of this map
	 */
	default DiscreteDoubleMap2D pullback(DiscreteTransformation2D transformation) {
		return (x, y) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			return this.getValueAt(xTransformed, yTransformed);
		};
	}

	/**
	 * Make this map operable, using the given {@link OperableDiscreteSet2D} as operating set.
	 * 
	 * @param range the set that handles operations
	 * @return operable version of this map
	 */
	default <S extends OperableDiscreteSet2D> OperableDiscreteDoubleMap2D<S> asOperable(S range) {
		return new WrapperOperableDiscreteDoubleMap2D<>(this, range);
	}

	/**
	 * Get the set of points satisfying a condition.
	 * 
	 * @param condition the condition
	 * @return the set of points satisfying the condition
	 */
	default DiscreteSet2D pointsSatisfying(DoublePredicate condition) {
		return (x, y) -> condition.test(getValueAt(x, y));
	}

	/**
	 * Get the level set for given value.
	 * Contains the points for which this maps's value equals the given value.
	 * 
	 * @param value the value
	 * @return level set for given value
	 */
	default DiscreteSet2D levelSet(double value) {
		return pointsSatisfying(d -> d == value);
	}

	/**
	 * Get the sub-level set for given value.
	 * Contains the points for which this maps's value is less than or equals the given value.
	 * 
	 * @param value the value
	 * @return sub-level set for given value
	 */
	default DiscreteSet2D subLevelSet(double value) {
		return pointsSatisfying(d -> d <= value);
	}

	/**
	 * Get the super-level set for given value.
	 * Contains the points for which this maps's value is greater than or equals the given value.
	 * 
	 * @param value the value
	 * @return super-level set for given value
	 */
	default DiscreteSet2D superLevelSet(double value) {
		return pointsSatisfying(d -> d >= value);
	}

	/**
	 * Get the support of this map.
	 * Contains the points for which this maps's value is zero.
	 * 
	 * @return this map's support
	 */
	default DiscreteSet2D support() {
		return pointsSatisfying(d -> d != 0);
	}
	

	/**
	 * Get the point-wise sum of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise sum map
	 */
	default DiscreteDoubleMap2D plus(DiscreteDoubleMap2D other) {
		return (x, y) -> this.getValueAt(x, y) + other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise sum of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise sum map
	 */
	default DiscreteDoubleMap2D plus(double value) {
		return pushForward(d -> d + value);
	}

	/**
	 * Get the point-wise difference of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise difference map
	 */
	default DiscreteDoubleMap2D minus(DiscreteDoubleMap2D other) {
		return (x, y) -> this.getValueAt(x, y) - other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise difference of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise difference map
	 */
	default DiscreteDoubleMap2D minus(double value) {
		return pushForward(d -> d - value);
	}

	/**
	 * Get the point-wise product of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise product map
	 */
	default DiscreteDoubleMap2D times(DiscreteDoubleMap2D other) {
		return (x, y) -> this.getValueAt(x, y) * other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise product of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise product map
	 */
	default DiscreteDoubleMap2D times(double value) {
		return pushForward(d -> d * value);
	}

	/**
	 * Get the point-wise quotient of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise quotient map
	 */
	default DiscreteDoubleMap2D dividedBy(DiscreteDoubleMap2D other) {
		return (x, y) -> this.getValueAt(x, y) / other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise quotient of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise quotient map
	 */
	default DiscreteDoubleMap2D dividedBy(double value) {
		return pushForward(d -> d / value);
	}

	/**
	 * Get the point-wise minimum map of this map and the given one.
	 * 
	 * @param other the other map
	 * @return point-wise minimum map
	 */
	default DiscreteDoubleMap2D pointWiseMinimum(DiscreteDoubleMap2D other) {
		return (x, y) -> Math.min(this.getValueAt(x, y), other.getValueAt(x, y));
	}
	
	/**
	 * Get the point-wise minimum map of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise minimum map
	 */
	default DiscreteDoubleMap2D pointWiseMinimum(double value) {
		return (x, y) -> Math.min(this.getValueAt(x, y), value);
	}

	/**
	 * Get the point-wise maximum map of this map and the given one.
	 * 
	 * @param other the other map
	 * @return point-wise maximum map
	 */
	default DiscreteDoubleMap2D pointWiseMaximum(DiscreteDoubleMap2D other) {
		return (x, y) -> Math.max(this.getValueAt(x, y), other.getValueAt(x, y));
	}

	/**
	 * Get the point-wise maximum map of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise maximum map
	 */
	default DiscreteDoubleMap2D pointWiseMaximum(double value) {
		return (x, y) -> Math.max(this.getValueAt(x, y), value);
	}

	/**
	 * Get the point-wise absolute value map.
	 * 
	 * @return point-wise absolute value map
	 */
	default DiscreteDoubleMap2D absoluteValue() {
		return pushForward(Math::abs);
	}

	/**
	 * Get the point-wise negative of this map.
	 * 
	 * @return this map's negative
	 */
	default DiscreteDoubleMap2D negative() {
		return pushForward(d -> - d);
	}

	/**
	 * Get the point-wise reciprocal of this map.
	 * 
	 * @return this map's reciprocal
	 */
	default DiscreteDoubleMap2D reciprocal() {
		return (x, y) -> 1. / this.getValueAt(x, y);
	}

	/**
	 * Get this map's non-negative part.
	 * Maps to zero if this map has negative value.
	 * 
	 * @return non-negative part map
	 */
	default DiscreteDoubleMap2D nonNegativePart() {
		return pointWiseMaximum(ZERO);
	}

	/**
	 * Get this map's non-positive part.
	 * Maps to zero if this map has positive value.
	 * 
	 * @return non-positive part map
	 */
	default DiscreteDoubleMap2D nonPositivePart() {
		return pointWiseMinimum(ZERO);
	}
	

	/**
	 * Get the double map that has values either the first or the second maps, determined by the condition.
	 * 
	 * @param condition the condition
	 * @param thenMap value map for points for which the condition is satisfied
	 * @param elseMap value map for points for which the condition is not satisfied
	 * @return
	 */
	static DiscreteDoubleMap2D ifThenElse(DiscretePredicate2D condition, DiscreteDoubleMap2D thenMap, DiscreteDoubleMap2D elseMap) {
		return (x, y) -> {
			if(condition.test(x, y))
				return thenMap.getValueAt(x, y);
			else
				return elseMap.getValueAt(x, y);
		};
	}
	

	// the zero map
	final DiscreteDoubleMap2D ZERO = ofConstant(0);

	/**
	 * Get the map with constant value.
	 * 
	 * @param constant the value
	 * @return the constant map
	 */
	static DiscreteDoubleMap2D ofConstant(double constant) {
		return (x, y) -> constant;
	}
}

package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.objectmap.DiscreteObjectMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A map from discrete two-dimensional points to integer values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteIntMap2D {

	/**
	 * Get the value at given discrete point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the value at the specified point
	 */
	int getValueAt(int x, int y);
	

	/**
	 * Consider this map a discrete double map.
	 * 
	 * @return this map as double map
	 */
	default DiscreteDoubleMap2D asDoubleMap() {
		return this::getValueAt;
	}
	
	/**
	 * Consider this map as a map of {@link Integer}s.
	 * 
	 * @return this map as {@link Integer} map
	 */
	default DiscreteObjectMap2D<Integer> asObjectMap() {
		return pushForwardToObject(Integer::new);
	}
	
	/**
	 * Get the map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return integer map with remapped values
	 */
	default DiscreteIntMap2D pushForward(IntUnaryOperator function) {
		return (x, y) -> function.applyAsInt(getValueAt(x, y));
	}
	
	/**
	 * Get the double map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return double map with remapped values
	 */
	default DiscreteDoubleMap2D pushForwardToDouble(IntToDoubleFunction function) {
		return (x, y) -> function.applyAsDouble(getValueAt(x, y));
	}

	/**
	 * Get the object map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return object map with remapped values
	 */
	default <T> DiscreteObjectMap2D<T> pushForwardToObject(IntFunction<T> function) {
		return (x, y) -> function.apply(getValueAt(x, y));
	}

	/**
	 * Get the pullback of this map under the given transformation.
	 * 
	 * @param transformation the transformation
	 * @return the pullback of this map
	 */
	default DiscreteIntMap2D pullback(DiscreteTransformation2D transformation) {
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
	default <S extends OperableDiscreteSet2D> OperableDiscreteIntMap2D<S> asOperable(S range) {
		return new WrapperOperableDiscreteIntMap2D<>(this, range);
	}
	
	/**
	 * Get the set of points satisfying a condition.
	 * 
	 * @param condition the condition
	 * @return the set of points satisfying the condition
	 */
	default DiscreteSet2D pointsSatisfying(IntPredicate condition) {
		return (x, y) -> condition.test(getValueAt(x, y));
	}
	
	/**
	 * Get the level set for given value.
	 * Contains the points for which this maps's value equals the given value.
	 * 
	 * @param value the value
	 * @return level set for given value
	 */
	default DiscreteSet2D levelSet(int value) {
		return pointsSatisfying(d -> d == value);
	}

	/**
	 * Get the sub-level set for given value.
	 * Contains the points for which this maps's value is less than or equals the given value.
	 * 
	 * @param value the value
	 * @return sub-level set for given value
	 */
	default DiscreteSet2D subLevelSet(int value) {
		return pointsSatisfying(d -> d <= value);
	}

	/**
	 * Get the super-level set for given value.
	 * Contains the points for which this maps's value is greater than or equals the given value.
	 * 
	 * @param value the value
	 * @return super-level set for given value
	 */
	default DiscreteSet2D superLevelSet(int value) {
		return pointsSatisfying(d -> d >= value);
	}
	
	/**
	 * Get the support of this map.
	 * Contains the points for which this maps's value is non-zero.
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
	default DiscreteIntMap2D plus(DiscreteIntMap2D other) {
		return (x, y) -> this.getValueAt(x, y) + other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise sum of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise sum map
	 */
	default DiscreteIntMap2D plus(int value) {
		return pushForward(d -> d + value);
	}

	/**
	 * Get the point-wise difference of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise difference map
	 */
	default DiscreteIntMap2D minus(DiscreteIntMap2D other) {
		return (x, y) -> this.getValueAt(x, y) - other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise difference of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise difference map
	 */
	default DiscreteIntMap2D minus(int value) {
		return pushForward(d -> d - value);
	}

	/**
	 * Get the point-wise product of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise product map
	 */
	default DiscreteIntMap2D times(DiscreteIntMap2D other) {
		return (x, y) -> this.getValueAt(x, y) * other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise product of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise product map
	 */
	default DiscreteIntMap2D times(int value) {
		return pushForward(d -> d * value);
	}

	/**
	 * Get the point-wise quotient of this map and the given map.
	 * 
	 * @param other the other map
	 * @return point-wise quotient map
	 */
	default DiscreteIntMap2D dividedBy(DiscreteIntMap2D other) {
		return (x, y) -> this.getValueAt(x, y) / other.getValueAt(x, y);
	}

	/**
	 * Get the point-wise quotient of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise quotient map
	 */
	default DiscreteIntMap2D dividedBy(int value) {
		return pushForward(d -> d / value);
	}
	
	/**
	 * Get the point-wise minimum map of this map and the given one.
	 * 
	 * @param other the other map
	 * @return point-wise minimum map
	 */
	default DiscreteIntMap2D pointWiseMinimum(DiscreteIntMap2D other) {
		return (x, y) -> Math.min(this.getValueAt(x, y), other.getValueAt(x, y));
	}
	
	/**
	 * Get the point-wise minimum map of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise minimum map
	 */
	default DiscreteIntMap2D pointWiseMinimum(int value) {
		return (x, y) -> Math.min(this.getValueAt(x, y), value);
	}

	/**
	 * Get the point-wise maximum map of this map and the given one.
	 * 
	 * @param other the other map
	 * @return point-wise maximum map
	 */
	default DiscreteIntMap2D pointWiseMaximum(DiscreteIntMap2D other) {
		return (x, y) -> Math.max(this.getValueAt(x, y), other.getValueAt(x, y));
	}

	/**
	 * Get the point-wise maximum map of this map and the given value.
	 * 
	 * @param value the value
	 * @return point-wise maximum map
	 */
	default DiscreteIntMap2D pointWiseMaximum(int value) {
		return (x, y) -> Math.max(this.getValueAt(x, y), value);
	}

	/**
	 * Get the point-wise absolute value map.
	 * 
	 * @return point-wise absolute value map
	 */
	default DiscreteIntMap2D absoluteValue() {
		return pushForward(Math::abs);
	}
	
	/**
	 * Get the point-wise negative of this map.
	 * 
	 * @return this map's negative
	 */
	default DiscreteIntMap2D negative() {
		return pushForward(d -> - d);
	}
	
	/**
	 * Get this map's non-negative part.
	 * Maps to zero if this map has negative value.
	 * 
	 * @return non-negative part map
	 */
	default DiscreteIntMap2D nonNegativePart() {
		return pointWiseMaximum(ZERO);
	}

	/**
	 * Get this map's non-positive part.
	 * Maps to zero if this map has positive value.
	 * 
	 * @return non-positive part map
	 */
	default DiscreteIntMap2D nonPositivePart() {
		return pointWiseMinimum(ZERO);
	}
	
	
	/**
	 * Get the integer map that has values either the first or the second maps, determined by the condition.
	 * 
	 * @param condition the condition
	 * @param thenMap value map for points for which the condition is satisfied
	 * @param elseMap value map for points for which the condition is not satisfied
	 * @return
	 */
	static DiscreteIntMap2D ifThenElse(DiscretePredicate2D condition, DiscreteIntMap2D thenMap, DiscreteIntMap2D elseMap) {
		return (x, y) -> {
			if(condition.test(x, y))
				return thenMap.getValueAt(x, y);
			else
				return elseMap.getValueAt(x, y);
		};
	}
	
	
	// the zero map
	public static final DiscreteIntMap2D ZERO = ofConstant(0);
	
	/**
	 * Get the map with constant value.
	 * 
	 * @param constant the value
	 * @return the constant map
	 */
	static DiscreteIntMap2D ofConstant(int constant) {
		return (x, y) -> constant;
	}
}

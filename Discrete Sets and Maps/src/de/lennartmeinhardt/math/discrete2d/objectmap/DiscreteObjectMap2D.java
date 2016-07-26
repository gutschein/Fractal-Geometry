package de.lennartmeinhardt.math.discrete2d.objectmap;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A map from discrete two-dimensional points to objects.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteObjectMap2D <T> {

	/**
	 * Get the value at given discrete point.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the value at the specified point
	 */
	T getValueAt(int x, int y);


	/**
	 * Get the object map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return object map with remapped values
	 */
	default <U> DiscreteObjectMap2D<U> pushForward(Function<? super T, ? extends U> function) {
		return (x, y) -> function.apply(getValueAt(x, y));
	}

	/**
	 * Get the map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return integer map with remapped values
	 */
	default DiscreteIntMap2D pushForwardToInt(ToIntFunction<? super T> function) {
		return (x, y) -> function.applyAsInt(getValueAt(x, y));
	}

	/**
	 * Get the double map that applies a function to the results of this map.
	 * 
	 * @param function the function
	 * @return double map with remapped values
	 */
	default DiscreteDoubleMap2D pushForwardToDouble(ToDoubleFunction<? super T> function) {
		return (x, y) -> function.applyAsDouble(getValueAt(x, y));
	}

	/**
	 * Get the pullback of this map under the given transformation.
	 * 
	 * @param transformation the transformation
	 * @return the pullback of this map
	 */
	default DiscreteObjectMap2D<T> pullback(DiscreteTransformation2D transformation) {
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
	default <S extends OperableDiscreteSet2D> OperableDiscreteObjectMap2D<T, S> asOperable(S range) {
		return new WrapperOperableDiscreteObjectMap2D<>(this, range);
	}

	/**
	 * Get the set of points satisfying a condition.
	 * 
	 * @param condition the condition
	 * @return the set of points satisfying the condition
	 */
	default DiscreteSet2D pointsSatisfying(Predicate<? super T> condition) {
		return (x, y) -> condition.test(getValueAt(x, y));
	}
	

	// the null map
	public static final DiscreteObjectMap2D<Void> NULL = ofConstant(null);

	/**
	 * Get the map with constant value.
	 * 
	 * @param constant the value
	 * @return the constant map
	 */
	static <T> DiscreteObjectMap2D<T> ofConstant(T constant) {
		return (x, y) -> constant;
	}
}

package de.lennartmeinhardt.imaging;

import java.util.function.DoubleUnaryOperator;

import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.OperableDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * Provides helper methods for amplifying relative maps so they are better visible.
 * 
 * @author Lennart Meinhardt
 */
public class Amplifiers {

	/**
	 * Create {@link DiscretePredicate2D} that checks if a point is in the bottom left quarter.
	 * 
	 * @param bounds the bounds rectangle
	 * @return predicate that checks for bottom left quarter
	 */
	public static DiscretePredicate2D createBottomLeftCondition(DiscreteRectangle bounds) {
		return (x, y) -> bounds.getLeft() <= x
				&& bounds.getBottom() <= y
				&& x < bounds.getLeft() + bounds.getWidth() / 2
				&& y < bounds.getBottom() + bounds.getHeight() / 2;
	}

	/**
	 * Create {@link DiscretePredicate2D} that checks if a point is in the top left quarter.
	 * 
	 * @param bounds the bounds rectangle
	 * @return predicate that checks for top left quarter
	 */
	public static DiscretePredicate2D createTopLeftCondition(DiscreteRectangle bounds) {
		return (x, y) -> bounds.getLeft() <= x
				&& bounds.getBottom() + bounds.getHeight() / 2 <= y
				&& x < bounds.getLeft() + bounds.getWidth() / 2
				&& y <= bounds.getTop();
	}

	/**
	 * Create {@link DiscretePredicate2D} that checks if a point is in the bottom right quarter.
	 * 
	 * @param bounds the bounds rectangle
	 * @return predicate that checks for bottom right quarter
	 */
	public static DiscretePredicate2D createBottomRightCondition(DiscreteRectangle bounds) {
		return (x, y) -> bounds.getLeft() + bounds.getWidth() / 2 <= x
				&& bounds.getBottom() <= y
				&& x <= bounds.getRight()
				&& y < bounds.getBottom() + bounds.getHeight() / 2;
	}

	/**
	 * Create {@link DiscretePredicate2D} that checks if a point is in the top right quarter.
	 * 
	 * @param bounds the bounds rectangle
	 * @return predicate that checks for top right quarter
	 */
	public static DiscretePredicate2D createTopRightCondition(DiscreteRectangle bounds) {
		return (x, y) -> bounds.getLeft() + bounds.getWidth() / 2 <= x
				&& bounds.getBottom() + bounds.getHeight() / 2 <= y
				&& x <= bounds.getRight()
				&& y <= bounds.getTop();
	}
	
	/**
	 * Get operator for linear scaling, and then cropping to [0, 1], t -> min(1, tp).
	 * 
	 * @param param the parameter to amplify with.
	 * @return the amplified map
	 */
	public static DoubleUnaryOperator amplifyMin(double param) {
		return v -> Math.min(1, v * param);
	}
	
	/**
	 * Get operator for roots, t -> t^(1/p).
	 * 
	 * @param param the parameter to amplify with.
	 * @return the amplified map
	 */
	public static DoubleUnaryOperator amplifyRoot(double param) {
		return v -> Math.pow(v, 1 / param);
	}
	
	/**
	 * Get operator for powers, t -> 1 - (1-t)^p.
	 * 
	 * @param param the parameter to amplify with.
	 * @return the amplified map
	 */
	public static DoubleUnaryOperator amplifyPower(double param) {
		return v -> 1 - Math.pow(1 - v, param);
	}
	
	/**
	 * Amplify a map in the top left quarter, using the default method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param param the amplification parameter
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyTopLeftDefault(DiscreteDoubleMap2D map, DiscreteRectangle bounds, double param) {
		return map;
	}
	
	/**
	 * Amplify a map in the top right quarter, using the default method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param param the amplification parameter
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyTopRightDefault(DiscreteDoubleMap2D map, DiscreteRectangle bounds, double param) {
		return amplifyTopRight(map, bounds, amplifyPower(param));
	}
	
	/**
	 * Amplify a map in the bottom left quarter, using the default method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param param the amplification parameter
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyBottomLeftDefault(DiscreteDoubleMap2D map, DiscreteRectangle bounds, double param) {
		return amplifyBottomLeft(map, bounds, amplifyMin(param));
	}
	
	/**
	 * Amplify a map in the bottom right quarter, using the default method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param param the amplification parameter
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyBottomRightDefault(DiscreteDoubleMap2D map, DiscreteRectangle bounds, double param) {
		return amplifyBottomRight(map, bounds, amplifyRoot(param));
	}
	
	/**
	 * Amplify a map in the top right quarter, using the given method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param function the amplification method
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyTopRight(DiscreteDoubleMap2D map, DiscreteRectangle bounds, DoubleUnaryOperator function) {
		return postConcatIf(map, createTopRightCondition(bounds), function);
	}
	
	/**
	 * Amplify a map in the top left quarter, using the given method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param function the amplification method
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyTopLeft(DiscreteDoubleMap2D map, DiscreteRectangle bounds, DoubleUnaryOperator function) {
		return postConcatIf(map, createTopLeftCondition(bounds), function);
	}
	
	/**
	 * Amplify a map in the bottom right quarter, using the given method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param function the amplification method
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyBottomRight(DiscreteDoubleMap2D map, DiscreteRectangle bounds, DoubleUnaryOperator function) {
		return postConcatIf(map, createBottomRightCondition(bounds), function);
	}
	
	/**
	 * Amplify a map in the bottom left quarter, using the given method.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param function the amplification method
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyBottomLeft(DiscreteDoubleMap2D map, DiscreteRectangle bounds, DoubleUnaryOperator function) {
		return postConcatIf(map, createBottomLeftCondition(bounds), function);
	}
	
	/**
	 * Post-concatenates a map on points satisfying a given condition.
	 * 
	 * @param base the map to concatenate
	 * @param condition the point condition
	 * @param function the function to post-concatenate with
	 * @return the concatenated map
	 */
	public static DiscreteDoubleMap2D postConcatIf(DiscreteDoubleMap2D base, DiscretePredicate2D condition, DoubleUnaryOperator function) {
		return DiscreteDoubleMap2D.ifThenElse(condition, base.pushForward(function), base);
	}
	
	/**
	 * Amplify a map in all quarters, using different methods.
	 * 
	 * @param map the map to amplify
	 * @param bounds the bounds rectangle
	 * @param param the amplification parameter
	 * @return the amplified map
	 */
	public static DiscreteDoubleMap2D amplifyAllDefault(DiscreteDoubleMap2D map, DiscreteRectangle bounds, double param) {
		map = amplifyTopRightDefault(map, bounds, param);
		map = amplifyTopLeftDefault(map, bounds, param);
		map = amplifyBottomRightDefault(map, bounds, param);
		map = amplifyBottomLeftDefault(map, bounds, param);
		return map;
	}
	
	/**
	 * Scale a map by its maximum value, making it attain values in [0, 1] if it is non-negative.
	 * 
	 * @param map the map to scale
	 * @return scaled map with values in [0, 1]
	 */
	public static DiscreteDoubleMap2D scaleToMax(OperableDiscreteDoubleMap2D<?> map) {
		double max = map.getMaximumValue();
		return map.dividedBy(max);
	}
	
	/**
	 * Scale a map by its maximum value, making it attain values in [0, 1] if it is non-negative.
	 * 
	 * @param map the map to scale
	 * @return scaled map with values in [0, 1]
	 */
	public static DiscreteDoubleMap2D scaleToMax(OperableDiscreteIntMap2D<?> intMap) {
		double max = intMap.getMaximumValue();
		return intMap.pushForwardToDouble(i -> i / max);
	}
	
	
	// no instances
	private Amplifiers() {
	}
}

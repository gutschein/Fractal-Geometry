package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoublePredicate;

import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A condition to test on tuples of discrete 2d-points and double values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteDoublePredicate2D {

	/**
	 * Test the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param d the double value
	 * @return the result of the test
	 */
	boolean test(int x, int y, double d);
	
	
	/**
	 * Get the discrete double predicate that is true if this predicate is false.
	 * 
	 * @return the negated predicate
	 */
	default DiscreteDoublePredicate2D not() {
		return (x, y, d) -> ! this.test(x, y, d);
	}
	
	/**
	 * Get the predicate that is true if this and a given one are true.
	 * 
	 * @param other the other predicate
	 * @return the and-predicate
	 */
	default DiscreteDoublePredicate2D and(DiscreteDoublePredicate2D other) {
		return (x, y, d) -> this.test(x, y, d) && other.test(x, y, d);
	}

	/**
	 * Get the predicate that is true if this or a given one, or both, are true.
	 * 
	 * @param other the other predicate
	 * @return the or-predicate
	 */
	default DiscreteDoublePredicate2D or(DiscreteDoublePredicate2D other) {
		return (x, y, d) -> this.test(x, y, d) || other.test(x, y, d);
	}

	/**
	 * Get the predicate that is true if this or a given one, but not both, are true.
	 * 
	 * @param other the other predicate
	 * @return the exclusive-or-predicate
	 */
	default DiscreteDoublePredicate2D xor(DiscreteDoublePredicate2D other) {
		return (x, y, d) -> this.test(x, y, d) ^ other.test(x, y, d);
	}

	
	/**
	 * Get the predicate that tests only the value.
	 * 
	 * @param valuePredicate the prediate to test values with
	 * @return the predicate testing the value
	 */
	static DiscreteDoublePredicate2D ofValuePredicate(DoublePredicate valuePredicate) {
		return (x, y, d) -> valuePredicate.test(d);
	}

	/**
	 * Get the predicate that tests only the point.
	 * 
	 * @param pointPredicate the prediate to test points with
	 * @return the predicate testing the point
	 */
	static DiscreteDoublePredicate2D ofPointPredicate(DiscretePredicate2D pointPredicate) {
		return (x, y, d) -> pointPredicate.test(x, y);
	}
	
	
	// default predicates that are always true or false
	final DiscreteDoublePredicate2D ALWAYS_TRUE = (x, y, d) -> true;
	final DiscreteDoublePredicate2D ALWAYS_FALSE = (x, y, d) -> false;
}

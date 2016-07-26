package de.lennartmeinhardt.math.discrete2d.intmap;

import java.util.function.IntPredicate;

import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A condition to test on tuples of discrete 2d-points and integer values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteIntPredicate2D {

	/**
	 * Test the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param i the integer value
	 * @return the result of the test
	 */
	boolean test(int x, int y, int i);
	

	/**
	 * Get the predicate that is true if this predicate is false.
	 * 
	 * @return the negated predicate
	 */
	default DiscreteIntPredicate2D not() {
		return (x, y, i) -> ! this.test(x, y, i);
	}

	/**
	 * Get the predicate that is true if this and a given one are true.
	 * 
	 * @param other the other predicate
	 * @return the and-predicate
	 */
	default DiscreteIntPredicate2D and(DiscreteIntPredicate2D other) {
		return (x, y, i) -> this.test(x, y, i) && other.test(x, y, i);
	}

	/**
	 * Get the predicate that is true if this or a given one, or both, are true.
	 * 
	 * @param other the other predicate
	 * @return the or-predicate
	 */
	default DiscreteIntPredicate2D or(DiscreteIntPredicate2D other) {
		return (x, y, i) -> this.test(x, y, i) || other.test(x, y, i);
	}

	/**
	 * Get the predicate that is true if this or a given one, but not both, are true.
	 * 
	 * @param other the other predicate
	 * @return the exclusive-or-predicate
	 */
	default DiscreteIntPredicate2D xor(DiscreteIntPredicate2D other) {
		return (x, y, i) -> this.test(x, y, i) ^ other.test(x, y, i);
	}


	/**
	 * Get the predicate that tests only the value.
	 * 
	 * @param valuePredicate the prediate to test values with
	 * @return the predicate testing the value
	 */
	static DiscreteIntPredicate2D ofValuePredicate(IntPredicate valuePredicate) {
		return (x, y, d) -> valuePredicate.test(d);
	}

	/**
	 * Get the predicate that tests only the point.
	 * 
	 * @param pointPredicate the prediate to test points with
	 * @return the predicate testing the point
	 */
	static DiscreteIntPredicate2D ofPointPredicate(DiscretePredicate2D pointPredicate) {
		return (x, y, d) -> pointPredicate.test(x, y);
	}
	
	
	// default predicates that are always true or false
	final DiscreteIntPredicate2D ALWAYS_TRUE = (x, y, d) -> true;
	final DiscreteIntPredicate2D ALWAYS_FALSE = (x, y, d) -> false;
}

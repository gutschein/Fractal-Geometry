package de.lennartmeinhardt.math.discrete2d.objectmap;

import java.util.function.Predicate;

import de.lennartmeinhardt.math.discrete2d.set.DiscretePredicate2D;

/**
 * A condition to test on tuples of discrete 2d-points and object values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteObjectPredicate2D <T> {

	/**
	 * Test the given coordinates and value.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param value the value
	 * @return the result of the test
	 */
	boolean test(int x, int y, T value);
	

	/**
	 * Get the predicate that is true if this predicate is false.
	 * 
	 * @return the negated predicate
	 */
	default DiscreteObjectPredicate2D<T> not() {
		return (x, y, t) -> ! this.test(x, y, t);
	}

	/**
	 * Get the predicate that is true if this and a given one are true.
	 * 
	 * @param other the other predicate
	 * @return the and-predicate
	 */
	default <S extends T> DiscreteObjectPredicate2D<S> and(DiscreteObjectPredicate2D<? super S> other) {
		return (x, y, s) -> this.test(x, y, s) && other.test(x, y, s);
	}

	/**
	 * Get the predicate that is true if this or a given one, or both, are true.
	 * 
	 * @param other the other predicate
	 * @return the or-predicate
	 */
	default <S extends T> DiscreteObjectPredicate2D<S> or(DiscreteObjectPredicate2D<? super S> other) {
		return (x, y, s) -> this.test(x, y, s) || other.test(x, y, s);
	}

	/**
	 * Get the predicate that is true if this or a given one, but not both, are true.
	 * 
	 * @param other the other predicate
	 * @return the exclusive-or-predicate
	 */
	default <S extends T> DiscreteObjectPredicate2D<S> xor(DiscreteObjectPredicate2D<? super S> other) {
		return (x, y, s) -> this.test(x, y, s) ^ other.test(x, y, s);
	}

	
	/**
	 * Get the predicate that tests only the value.
	 * 
	 * @param valuePredicate the prediate to test values with
	 * @return the predicate testing the value
	 */
	static <T> DiscreteObjectPredicate2D<T> ofValuePredicate(Predicate<? super T> valuePredicate) {
		return (x, y, d) -> valuePredicate.test(d);
	}

	/**
	 * Get the predicate that tests only the point.
	 * 
	 * @param pointPredicate the prediate to test points with
	 * @return the predicate testing the point
	 */
	static <T> DiscreteObjectPredicate2D<T> ofPointPredicate(DiscretePredicate2D pointPredicate) {
		return (x, y, d) -> pointPredicate.test(x, y);
	}
	

	/**
	 * Get a predicate that is always true.
	 * 
	 * @return the constant true predicate
	 */
	static <T> DiscreteObjectPredicate2D<T> alwaysTruePredicate() {
		return (x, y, t) -> true;
	}
	
	/**
	 * Get a predicate that is always false.
	 * 
	 * @return the constant false predicate
	 */
	static <T> DiscreteObjectPredicate2D<T> alwaysFalsePredicate() {
		return (x, y, t) -> false;
	}
}

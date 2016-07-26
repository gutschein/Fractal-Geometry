package de.lennartmeinhardt.math.discrete2d.set;

/**
 * A predicate that test two-dimensional discrete points.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscretePredicate2D {
	
	/**
	 * Test the given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the result of the test
	 */
	boolean test(int x, int y);
	
	
	/**
	 * Get the discrete predicate that is true if this predicate is false.
	 * 
	 * @return the negated predicate
	 */
	default DiscretePredicate2D not() {
		return (x, y) -> ! this.test(x, y);
	}
	
	/**
	 * Get the discrete predicate that is true if this and a given one are true.
	 * 
	 * @param other the other predicate
	 * @return the and-predicate
	 */
	default DiscretePredicate2D and(DiscretePredicate2D other) {
		return (x, y) -> this.test(x, y) && other.test(x, y);
	}
	
	/**
	 * Get the discrete predicate that is true if this or a given one, or both, are true.
	 * 
	 * @param other the other predicate
	 * @return the or-predicate
	 */
	default DiscretePredicate2D or(DiscretePredicate2D other) {
		return (x, y) -> this.test(x, y) || other.test(x, y);
	}

	/**
	 * Get the discrete predicate that is true if this or a given one, but not both, are true.
	 * 
	 * @param other the other predicate
	 * @return the exclusive-or-predicate
	 */
	default DiscretePredicate2D xor(DiscretePredicate2D other) {
		return (x, y) -> this.test(x, y) ^ other.test(x, y);
	}
	
	
	// default predicates that are always true or false
	final DiscretePredicate2D ALWAYS_TRUE = (x, y) -> true;
	final DiscretePredicate2D ALWAYS_FALSE = (x, y) -> false;
}

package de.lennartmeinhardt.math.util;

/**
 * This object can generate random integers in a given range.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface RangeRandomIntGenerator {

	/**
	 * Get a random integer between 0 (inclusive) and maxExcl(exclusive).
	 * 
	 * @param maxExcl the range length (exclusive)
	 * @return a random integer in the given range
	 */
	int nextInt(int maxExcl);
}

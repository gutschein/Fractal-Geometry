package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.intmap.LongSum;

/**
 * A {@link DiscreteSet2D} that can perform operations on all of its points.
 * 
 * @author Lennart Meinhardt
 */
public interface OperableDiscreteSet2D extends DiscreteSet2D {

	/**
	 * Perform a given operation on this set's points.
	 * 
	 * @param operation the operation to perform
	 */
	void forEachPoint(DiscreteConsumer2D operation);
	
	/**
	 * Get this set's cardinality.
	 * 
	 * @return the number of points in this set
	 */
	default long getCardinality() {
		LongSum counter = new LongSum();
		forEachPoint((x, y) -> counter.accept(1));
		return counter.getSum();
	}
}

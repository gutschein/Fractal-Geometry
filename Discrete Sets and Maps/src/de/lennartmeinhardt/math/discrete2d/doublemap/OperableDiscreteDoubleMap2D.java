package de.lennartmeinhardt.math.discrete2d.doublemap;

import java.util.function.DoubleConsumer;

import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A {@link DiscreteDoubleMap2D} with an operable range. Operations on points, values and point-value-pairs can be performed.
 * 
 * @author Lennart Meinhardt
 *
 * @param <S> the range type
 */
public interface OperableDiscreteDoubleMap2D <S extends OperableDiscreteSet2D> extends DiscreteDoubleMap2D, OperableDoubleValues {

	/**
	 * Get this map's range.
	 * 
	 * @return this map's range
	 */
	S getRange();


	/**
	 * Perform the given operation for any point in the range.
	 * 
	 * @param operation the operation to perform
	 */
	default void forEachPointInRange(DiscreteConsumer2D operation) {
		getRange().forEachPoint(operation);
	}

	/**
	 * Perform the given operation for any point-value pair.
	 * 
	 * @param operation the operation to perform
	 */
	default void forEach(DiscreteDoubleConsumer2D operation) {
		forEachPointInRange((x, y) -> operation.accept(x, y, getValueAt(x, y)));
	}
	
	@Override default void forEachValue(DoubleConsumer operation) {
		forEachPointInRange((x, y) -> operation.accept(getValueAt(x, y)));
	}
}

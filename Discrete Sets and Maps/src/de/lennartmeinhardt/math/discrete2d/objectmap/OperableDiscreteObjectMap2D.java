package de.lennartmeinhardt.math.discrete2d.objectmap;

import java.util.function.Consumer;

import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A {@link DiscreteObjectMap2D} with an operable range. Operations on points, values and point-value-pairs can be performed.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the objects' type
 * @param <S> the range's type
 */
public interface OperableDiscreteObjectMap2D <T, S extends OperableDiscreteSet2D> extends DiscreteObjectMap2D <T> {

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
	 * Perform the given operation for any value.
	 * 
	 * @param operation the operation to perform
	 */
	default void forEachValue(Consumer<? super T> operation) {
		forEachPointInRange((x, y) -> operation.accept(getValueAt(x, y)));
	}

	/**
	 * Perform the given operation for any point-value pair.
	 * 
	 * @param operation the operation to perform
	 */
	default void forEach(DiscreteObjectConsumer2D<? super T> operation) {
		forEachPointInRange((x, y) -> operation.accept(x, y, getValueAt(x, y)));
	}
}

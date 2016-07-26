package de.lennartmeinhardt.math.discrete2d.set;

/**
 * A base {@link OperableDiscreteSet2D} implementation that wraps a set and an operable set.
 * 
 * @author Lennart Meinhardt
 */
public class WrapperOperableDiscreteSet2D implements OperableDiscreteSet2D {
	
	// the set that determines which points are contained in this set
	private final DiscreteSet2D set;
	// the operable range of this set
	private final OperableDiscreteSet2D range;
	
	
	/**
	 * Create a new {@link WrapperOperableDiscreteSet2D} with given set and range.
	 * 
	 * @param set the set that contains points
	 * @param range the bounds to use
	 */
	public WrapperOperableDiscreteSet2D(DiscreteSet2D set, OperableDiscreteSet2D range) {
		this.set = set;
		this.range = range;
	}

	
	@Override public boolean containsPointAt(int x, int y) {
		return set.containsPointAt(x, y);
	}
	
	@Override public void forEachPoint(DiscreteConsumer2D operation) {
		range.forEachPoint(operation.when(this::containsPointAt));
	}
}

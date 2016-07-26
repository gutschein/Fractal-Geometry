package de.lennartmeinhardt.math.discrete2d.objectmap;

import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A basic {@link OperableDiscreteObjectMap2D} implementation that wraps an object map and an operable set.
 * 
 * @author Lennart Meinhardt

 * @param <T> the objects' type
 * @param <S> the range's type
 */
public class WrapperOperableDiscreteObjectMap2D <T, S extends OperableDiscreteSet2D> implements OperableDiscreteObjectMap2D<T, S> {
	
	// the set that determines values
	private final DiscreteObjectMap2D<? extends T> map;
	// the operable range of this map
	private final S range;
	

	/**
	 * Create a new {@link WrapperOperableDiscreteObjectMap2D} with given map and range.
	 * 
	 * @param map the map that has the values
	 * @param range the bounds to use
	 */
	public WrapperOperableDiscreteObjectMap2D(DiscreteObjectMap2D<? extends T> map, S range) {
		this.map = map;
		this.range = range;
	}
	
	
	@Override public T getValueAt(int x, int y) {
		return map.getValueAt(x, y);
	}
	
	@Override public S getRange() {
		return range;
	}
}

package de.lennartmeinhardt.math.discrete2d.intmap;

import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A basic {@link OperableDiscreteIntMap2D} implementation that wraps an integer map and an operable set.
 * 
 * @author Lennart Meinhardt
 *
 * @param <S> the range's type
 */
public class WrapperOperableDiscreteIntMap2D <S extends OperableDiscreteSet2D> implements OperableDiscreteIntMap2D<S> {
	
	// the set that determines values
	private final DiscreteIntMap2D map;
	// the operable range of this map
	private final S range;
	

	/**
	 * Create a new {@link WrapperOperableDiscreteIntMap2D} with given map and range.
	 * 
	 * @param map the map that has the values
	 * @param range the bounds to use
	 */
	public WrapperOperableDiscreteIntMap2D(DiscreteIntMap2D map, S range) {
		this.map = map;
		this.range = range;
	}
	
	
	@Override public int getValueAt(int x, int y) {
		return map.getValueAt(x, y);
	}
	
	@Override public S getRange() {
		return range;
	}
}

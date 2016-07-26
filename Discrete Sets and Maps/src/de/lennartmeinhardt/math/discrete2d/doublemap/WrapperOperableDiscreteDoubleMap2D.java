package de.lennartmeinhardt.math.discrete2d.doublemap;

import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;

/**
 * A basic {@link OperableDiscreteDoubleMap2D} implementation that wraps an double map and an operable set.
 * 
 * @author Lennart Meinhardt
 *
 * @param <S> the range's type
 */
public class WrapperOperableDiscreteDoubleMap2D <S extends OperableDiscreteSet2D> implements OperableDiscreteDoubleMap2D<S> {

	// the set that determines values
	private final DiscreteDoubleMap2D map;
	// the operable range of this map
	private final S range;


	/**
	 * Create a new {@link WrapperOperableDiscreteDoubleMap2D} with given map and range.
	 * 
	 * @param map the map that has the values
	 * @param range the bounds to use
	 */
	public WrapperOperableDiscreteDoubleMap2D(DiscreteDoubleMap2D map, S range) {
		this.map = map;
		this.range = range;
	}
	
	
	@Override public double getValueAt(int x, int y) {
		return map.getValueAt(x, y);
	}
	
	@Override public S getRange() {
		return range;
	}
}

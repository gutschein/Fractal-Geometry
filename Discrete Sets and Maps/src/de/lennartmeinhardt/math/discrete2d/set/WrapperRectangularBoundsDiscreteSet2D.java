package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A base {@link RectangularBoundsDiscreteSet2D} implementation that wraps a set and a rectangle.
 * 
 * @author Lennart Meinhardt
 */
public class WrapperRectangularBoundsDiscreteSet2D implements RectangularBoundsDiscreteSet2D {
	
	// the set that determines which points are contained in this set
	private final DiscreteSet2D set;
	// the rectangular bounds of this set
	private final DiscreteRectangle rect;
	
	
	/**
	 * Create a new {@link WrapperRectangularBoundsDiscreteSet2D} with given set and range.
	 * 
	 * @param set the set that contains points
	 * @param range the bounds to use
	 */
	public WrapperRectangularBoundsDiscreteSet2D(DiscreteSet2D set, DiscreteRectangle range) {
		this.set = set;
		this.rect = range;
	}
	

	@Override public boolean containsPointAt(int x, int y) {
		return set.containsPointAt(x, y);
	}

	@Override public DiscreteRectangle getBounds() {
		return rect;
	}
}

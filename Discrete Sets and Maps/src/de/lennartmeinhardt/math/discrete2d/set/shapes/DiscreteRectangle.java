package de.lennartmeinhardt.math.discrete2d.set.shapes;

import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.RectangularBoundsDiscreteSet2D;

/**
 * A rectangle has left, right, bottom and top bounds.
 * 
 * @author Lennart Meinhardt
 */
public interface DiscreteRectangle extends RectangularBoundsDiscreteSet2D {

	/**
	 * Get the left bounds.
	 * 
	 * @return left bounds
	 */
	int getLeft();

	/**
	 * Get the right bounds.
	 * 
	 * @return right bounds
	 */
	int getRight();

	/**
	 * Get the bottom bounds.
	 * 
	 * @return bottom bounds
	 */
	int getBottom();

	/**
	 * Get the top bounds.
	 * 
	 * @return top bounds
	 */
	int getTop();
	
	
	@Override default DiscreteRectangle getBounds() {
		return this;
	}
	
	/**
	 * Get this rectangle's width.
	 * 
	 * @return the width
	 */
	default int getWidth() {
		return Math.max(0, 1 + getRight() - getLeft());
	}

	/**
	 * Get this rectangle's height.
	 * 
	 * @return the height
	 */
	default int getHeight() {
		return Math.max(0, 1 + getRight() - getLeft());
	}
	

	@Override default boolean containsPointAt(int x, int y) {
		return getLeft() <= x && x <= getRight() && getBottom() <= y && y <= getTop();
	}
	
	@Override default void forEachPoint(DiscreteConsumer2D operation) {
		for(int x = getLeft(); x <= getRight(); x++)
			for(int y = getBottom(); y <= getTop(); y++)
				operation.accept(x, y);
	}
	
	@Override default long getCardinality() {
		int w = Math.max(0, getWidth());
		int h = Math.max(0, getHeight());
		return ((long) w) * h;
	}
}

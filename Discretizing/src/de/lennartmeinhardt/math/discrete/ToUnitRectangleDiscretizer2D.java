package de.lennartmeinhardt.math.discrete;

import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A {@link Discretizer2D} that linearly maps points in a {@link DiscreteRectangle} to the unit square [0, 1]^2.
 * 
 * @author Lennart Meinhardt
 */
public class ToUnitRectangleDiscretizer2D extends BaseDiscretizer2D {

	/**
	 * Create a new {@link ToUnitRectangleDiscretizer2D} with given bounds.
	 * 
	 * @param rect the bounds
	 */
	public ToUnitRectangleDiscretizer2D(DiscreteRectangle rect) {
		this(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
	}
	
	/**
	 * Create a new {@link ToUnitRectangleDiscretizer2D} with given bounds.
	 * 
	 * @param left the left bound
	 * @param bottom the bottom bound
	 * @param width the rectangle width
	 * @param height the rectangle height
	 */
	public ToUnitRectangleDiscretizer2D(int left, int bottom, int width, int height) {
		super(new ToUnitIntervalDiscretizer(width, left), new ToUnitIntervalDiscretizer(height, bottom));
	}
}

package de.lennartmeinhardt.math.discrete2d.set.shapes;

import de.lennartmeinhardt.math.discrete2d.set.RectangularBoundsDiscreteSet2D;

/**
 * A simple ellipse implementation.
 * 
 * @author Lennart Meinhardt
 */
public class BaseEllipse implements RectangularBoundsDiscreteSet2D {

	// the ellipse center coordinates
	private final double centerX;
	private final double centerY;
	// the ellise radius in x and y directions
	private final double radiusX;
	private final double radiusY;
	private final DiscreteRectangle bounds = new Bounds();
	
	/**
	 * Create a new {@link BaseEllipse} with given center and radius.
	 * 
	 * @param centerX the center's x coordinate
	 * @param centerY the center's y coordinate
	 * @param radiusX the radius in x direction
	 * @param radiusY the radius in y direction
	 */
	public BaseEllipse(double centerX, double centerY, double radiusX, double radiusY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}
	
	/**
	 * Create a new {@link DiscreteRectangle} with given bounds.
	 * 
	 * @param bounds the ellipse bounds
	 */
	public BaseEllipse(DiscreteRectangle bounds) {
		this(bounds.getLeft() + bounds.getWidth() / 2., bounds.getBottom() + bounds.getHeight() / 2., bounds.getWidth() / 2., bounds.getHeight() / 2.);
	}
	
	
	@Override public boolean containsPointAt(int x, int y) {
		return square((x - centerX) / radiusX) + square((y - centerY) / radiusY) <= 1;
	}
	
	// square a double value
	private static double square(double i) {
		return i * i;
	}
	
	@Override public DiscreteRectangle getBounds() {
		return bounds;
	}
	
	
	private class Bounds implements DiscreteRectangle {
		@Override public int getLeft() { return (int) Math.floor(centerX - radiusX); }
		@Override public int getBottom() { return (int) Math.floor(centerY - radiusY); }
		@Override public int getRight() { return (int) Math.ceil(centerX + radiusX); }
		@Override public int getTop() { return (int) Math.ceil(centerY + radiusY); }
	}
}

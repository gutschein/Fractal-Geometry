package de.lennartmeinhardt.math.argb.dim2.point;

import de.lennartmeinhardt.argb.ArgbColors;
import de.lennartmeinhardt.math.dim2.point.Point2D;

/**
 * An immutable {@link ArgbPoint2D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public class ImmutableArgbPoint2D implements ArgbPoint2D {
	
	// the coordinates
	private final double x, y;
	// the color
	private final int argb;

	
	/**
	 * Create a new {@link ImmutableArgbPoint2D} with given point.
	 * 
	 * @param point the base point
	 */
	public ImmutableArgbPoint2D(ArgbPoint2D point) {
		this(point.getX(), point.getY(), point.getArgb());
	}
	
	/**
	 * Create a new {@link ImmutableArgbPoint2D} with given point and color.
	 * 
	 * @param point the point that holds coordinates
	 * @param argb the color
	 */
	public ImmutableArgbPoint2D(Point2D point, int argb) {
		this(point.getX(), point.getY(), argb);
	}
	
	/**
	 * Create a new {@link ImmutableArgbPoint2D} with given coordinates and color.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param argb the color
	 */
	public ImmutableArgbPoint2D(double x, double y, int argb) {
		this.x = x;
		this.y = y;
		this.argb = argb;
	}
	
	
	@Override public double getX() {
		return x;
	}
	
	@Override public double getY() {
		return y;
	}
	
	@Override public int getArgb() {
		return argb;
	}
	
	@Override public String toString() {
		return String.format("(%f, %f, %s)", getX(), getY(), ArgbColors.toString(getArgb()));
	}
}

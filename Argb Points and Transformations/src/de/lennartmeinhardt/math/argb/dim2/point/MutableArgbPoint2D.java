package de.lennartmeinhardt.math.argb.dim2.point;

import de.lennartmeinhardt.argb.ArgbColors;
import de.lennartmeinhardt.math.dim2.point.MutablePoint2D;
import de.lennartmeinhardt.math.dim2.point.Point2D;

/**
 * A mutable {@link ArgbPoint2D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public class MutableArgbPoint2D extends MutablePoint2D implements ArgbPoint2D {
	
	// the color
	private int argb;
	
	
	/**
	 * Create a new {@link MutableArgbPoint2D}.
	 */
	public MutableArgbPoint2D() {
		this(0, 0, 0);
	}
	
	/**
	 * Create a new {@link MutableArgbPoint2D} with given color-point.
	 * 
	 * @param point the base point
	 */
	public MutableArgbPoint2D(ArgbPoint2D point) {
		this(point, point.getArgb());
	}
	
	/**
	 * Create a new {@link MutableArgbPoint2D} with given point and color.
	 * 
	 * @param point the base point
	 * @param argb the color
	 */
	public MutableArgbPoint2D(Point2D point, int argb) {
		this(point.getX(), point.getY(), argb);
	}
	
	/**
	 * Create a new {@link MutableArgbPoint2D} with given coordinates and color.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param argb the color
	 */
	public MutableArgbPoint2D(double x, double y, int argb) {
		super(x, y);
		setArgb(argb);
	}
	
	
	@Override public int getArgb() {
		return argb;
	}
	
	/**
	 * Set the argb color value.
	 * 
	 * @param argb the color
	 */
	public void setArgb(int argb) {
		this.argb = argb;
	}
	
	/**
	 * Set the coordinates and color.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 * @param argb the new argb color
	 */
	public void set(double x, double y, int argb) {
		setX(x);
		setY(y);
		setArgb(argb);
	}
	
	/**
	 * Set the point and color.
	 * 
	 * @param point the point coordinates to set
	 * @param argb the color to set
	 */
	public void set(Point2D point, int argb) {
		set(point.getX(), point.getY(), argb);
	}
	
	/**
	 * Set the color point.
	 * 
	 * @param point the point to set
	 */
	public void set(ArgbPoint2D point) {
		set(point.getX(), point.getY(), point.getArgb());
	}
	
	@Override public String toString() {
		return String.format("(%f, %f, %s)", getX(), getY(), ArgbColors.toString(getArgb()));
	}
}

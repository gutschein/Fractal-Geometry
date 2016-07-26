package de.lennartmeinhardt.math.dim2.point;

/**
 * A basic mutable {@link Point2D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public class MutablePoint2D implements Point2D {

	// the coordinates
	private double x, y;
	
	
	/**
	 * Create a new {@link MutablePoint2D}.
	 */
	public MutablePoint2D() {
		this(0, 0);
	}
	
	/**
	 * Create a new {@link MutablePoint2D} with given point to copy coordinates from.
	 * 
	 * @param point the point
	 */
	public MutablePoint2D(Point2D point) {
		this(point.getX(), point.getY());
	}
	
	/**
	 * Create a new {@link MutablePoint2D} with given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public MutablePoint2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	@Override public double getX() {
		return x;
	}
	
	@Override public double getY() {
		return y;
	}
	
	/**
	 * Set the x coordinate
	 * 
	 * @param x the new x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Set the y coordinate
	 * 
	 * @param y the new y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Set both coordinates at once.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void set(double x, double y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Set the coordinates to a given point.
	 * 
	 * @param point the point to copy coordinates from
	 */
	public void set(Point2D point) {
		set(point.getX(), point.getY());
	}
	
	@Override public String toString() {
		return String.format("(%f, %f)", getX(), getY());
	}
}

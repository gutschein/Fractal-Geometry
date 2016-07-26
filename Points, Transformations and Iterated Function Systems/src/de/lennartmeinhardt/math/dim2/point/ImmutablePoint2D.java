package de.lennartmeinhardt.math.dim2.point;

/**
 * A basic immutable {@link Point2D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public final class ImmutablePoint2D implements Point2D {
	
	// the coordinates
	private final double x, y;
	
	
	/**
	 * Create a new {@link ImmutablePoint2D} with given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public ImmutablePoint2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	@Override public double getX() {
		return x;
	}
	
	@Override public double getY() {
		return y;
	}
	
	@Override public String toString() {
		return String.format("(%f, %f)", getX(), getY());
	}
}

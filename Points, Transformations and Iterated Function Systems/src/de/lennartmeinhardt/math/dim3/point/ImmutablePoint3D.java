package de.lennartmeinhardt.math.dim3.point;

import de.lennartmeinhardt.math.dim2.point.ImmutablePoint2D;

/**
 * A basic immutable {@link Point3D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public final class ImmutablePoint3D implements Point3D {
	
	// the coordinates
	private final double x, y, z;
	
	/**
	 * Create a new {@link ImmutablePoint2D} with given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public ImmutablePoint3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override public double getX() {
		return x;
	}
	
	@Override public double getY() {
		return y;
	}
	
	@Override public double getZ() {
		return z;
	}
	
	@Override public String toString() {
		return String.format("(%f, %f, %f)", getX(), getY(), getZ());
	}
}

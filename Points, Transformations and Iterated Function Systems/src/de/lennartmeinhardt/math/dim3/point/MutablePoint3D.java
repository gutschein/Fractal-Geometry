package de.lennartmeinhardt.math.dim3.point;

/**
 * A basic mutable {@link Point3D} implementation.
 * 
 * @author Lennart Meinhardt
 */
public class MutablePoint3D implements Point3D {

	// the coordinates
	private double x, y, z;
	
	
	/**
	 * Create a new {@link MutablePoint3D}.
	 */
	public MutablePoint3D() {
		this(0, 0, 0);
	}
	
	/**
	 * Create a new {@link MutablePoint3D} with given point to copy coordinates from.
	 * 
	 * @param point the point
	 */
	public MutablePoint3D(Point3D point) {
		this(point.getX(), point.getY(), point.getZ());
	}
	
	/**
	 * Create a new {@link MutablePoint3D} with given coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public MutablePoint3D(double x, double y, double z) {
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
	 * Set the z coordinate
	 * 
	 * @param z the new z coordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Set all coordinates at once.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 * @param z the new z coordinate
	 */
	public void set(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/**
	 * Set the coordinates to a given point.
	 * 
	 * @param point the point to copy coordinates from
	 */
	public void set(Point3D point) {
		set(point.getX(), point.getY(), point.getZ());
	}
	
	@Override public String toString() {
		return String.format("(%f, %f, %f)", getX(), getY(), getZ());
	}
}

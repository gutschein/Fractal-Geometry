package de.lennartmeinhardt.math.dim3.transform;

import de.lennartmeinhardt.math.dim3.point.Point3D;

/**
 * A {@link PointTransformation3D} transforms three-dimensional points to three-dimensional points.
 * 
 * @author Lennart Meinhardt
 */
public interface PointTransformation3D {
	
	/**
	 * Get the x coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @return the transformed x coordinate
	 */
	double transformX(double x, double y, double z);

	/**
	 * Get the y coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @return the transformed y coordinate
	 */
	double transformY(double x, double y, double z);

	/**
	 * Get the z coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @return the transformed z coordinate
	 */
	double transformZ(double x, double y, double z);
	
	
	/**
	 * Get the x coordinate of the transformed point.
	 * 
	 * @param point the point to transform
	 * @return the transformed x coordinate
	 */
	default double transformX(Point3D point) {
		return transformX(point.getX(), point.getY(), point.getZ());
	}
	
	/**
	 * Get the y coordinate of the transformed point.
	 * 
	 * @param point the point to transform
	 * @return the transformed y coordinate
	 */
	default double transformY(Point3D point) {
		return transformY(point.getX(), point.getY(), point.getZ());
	}

	/**
	 * Get the z coordinate of the transformed point.
	 * 
	 * @param point the point to transform
	 * @return the transformed z coordinate
	 */
	default double transformZ(Point3D point) {
		return transformZ(point.getX(), point.getY(), point.getZ());
	}
}

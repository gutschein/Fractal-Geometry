package de.lennartmeinhardt.math.dim2.transform;

import de.lennartmeinhardt.math.dim2.point.Point2D;

/**
 * A {@link PointTransformation2D} transforms two-dimensional points to two-dimensional points.
 * 
 * @author Lennart Meinhardt
 */
public interface PointTransformation2D {
	
	/**
	 * Get the x coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the transformed x coordinate
	 */
	double transformX(double x, double y);
	
	/**
	 * Get the y coordinate of the transformed coordinates.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the transformed y coordinate
	 */
	double transformY(double x, double y);
	
	
	/**
	 * Get the x coordinate of the transformed point.
	 * 
	 * @param point the point to transform
	 * @return the transformed x coordinate
	 */
	default double transformX(Point2D point) {
		return transformX(point.getX(), point.getY());
	}
	
	/**
	 * Get the y coordinate of the transformed point.
	 * 
	 * @param point the point to transform
	 * @return the transformed y coordinate
	 */
	default double transformY(Point2D point) {
		return transformY(point.getX(), point.getY());
	}
}

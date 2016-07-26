package de.lennartmeinhardt.math.argb.dim2.transform;

import de.lennartmeinhardt.math.argb.dim2.point.ArgbPoint2D;

/**
 * An {@link ArgbPointTransformation2D} transforms tuples of 2D points and colors to 2D points and colors.
 * 
 * @author Lennart Meinhardt
 */
public interface ArgbPointTransformation2D extends ToArgbTransformation2D {
	
	/**
	 * Transform the x coordinate.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param argb the argb color
	 * @return the transformed x coordinate
	 */
	double transformX(double x, double y, int argb);
	
	/**
	 * Transform the y coordinate.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param argb the argb color
	 * @return the transformed y coordinate
	 */
	double transformY(double x, double y, int argb);

	
	/**
	 * Transforms the x coordinate.
	 * 
	 * @param point the point to transform
	 * @return the transformed x coordinate
	 */
	default double transformX(ArgbPoint2D point) {
		return transformX(point.getX(), point.getY(), point.getArgb());
	}
	
	/**
	 * Transforms the y coordinate.
	 * 
	 * @param point the point to transform
	 * @return the transformed y coordinate
	 */
	default double transformY(ArgbPoint2D point) {
		return transformY(point.getX(), point.getY(), point.getArgb());
	}
}

package de.lennartmeinhardt.math.argb.dim2.transform;

import de.lennartmeinhardt.math.argb.dim2.point.ArgbPoint2D;

/**
 * Maps {@link ArgbPoint2D}s to argb values.
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface ToArgbTransformation2D extends ToArgbTransformation<ArgbPoint2D> {
	
	/**
	 * Transforms an {@link ArgbPoint2D} to an argb color. 
	 * 
	 * @param x the source x coordinate
	 * @param y the source y coordinate
	 * @param argb the source argb color
	 * @return the transformed argb color
	 */
	int transformArgb(double x, double y, int argb);

	
	@Override default int transformArgb(ArgbPoint2D point) {
		return transformArgb(point.getX(), point.getY(), point.getArgb());
	}
}

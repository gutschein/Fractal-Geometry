package de.lennartmeinhardt.math.argb.dim2.transform;

import de.lennartmeinhardt.argb.ArgbColors;
import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;

/**
 * A simple implementation of a {@link ArgbPointTransformation2D} that transforms colors by interpolating them with fixed ratio and color.
 * 
 * @author Lennart Meinhardt
 */
public class InterpolateArgbPointTransformation2D extends BaseArgbPointTransformation2D {
	
	/**
	 * Create a new {@link InterpolateArgbPointTransformation2D} with given point transformation, color and interpolation ratio.
	 * A higher ratio transforms colors closer to the given color, a lower ratio has less effect when transforming colors.
	 * 
	 * @param pointTransformation the object that transforms points
	 * @param argb the argb used for interpolating
	 * @param ratio the interpolation ratio
	 */
	public InterpolateArgbPointTransformation2D(PointTransformation2D pointTransformation, int argb, double ratio) {
		super(pointTransformation, (x, y, c) -> ArgbColors.interpolate(c, argb, ratio));
	}
	
	/**
	 * Create a new {@link InterpolateArgbPointTransformation2D} with given point transformatoin and color.
	 * Argb colors are interpolated with ratio 0.5.
	 * 
	 * @param pointTransformation the object that transforms points
	 * @param argb the argb used for interpolating
	 */
	public InterpolateArgbPointTransformation2D(PointTransformation2D pointTransformation, int argb) {
		this(pointTransformation, argb, .5);
	}
}

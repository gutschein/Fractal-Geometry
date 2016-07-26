package de.lennartmeinhardt.math.argb.dim2.transform;

import java.util.Objects;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;

/**
 * A simple {@link ArgbPointTransformation2D} implementation that transforms points independently of colors.
 * 
 * @author Lennart Meinhardt
 */
public class BaseArgbPointTransformation2D implements ArgbPointTransformation2D {
	
	// the transformations
	private final PointTransformation2D pointTransformation;
	private final ToArgbTransformation2D argbTransformation;
	
	
	/**
	 * Create a new {@link ArgbPointTransformation2D} with given point and argb transformations.
	 * 
	 * @param pointTransformation the point transformation
	 * @param argbTransformation the argb transformation
	 */
	public BaseArgbPointTransformation2D(PointTransformation2D pointTransformation, ToArgbTransformation2D argbTransformation) {
		this.pointTransformation = Objects.requireNonNull(pointTransformation);
		this.argbTransformation = Objects.requireNonNull(argbTransformation);
	}
	
	
	@Override public int transformArgb(double x, double y, int argb) {
		return argbTransformation.transformArgb(x, y, argb);
	}
	
	@Override public double transformX(double x, double y, int argb) {
		return pointTransformation.transformX(x, y);
	}
	
	@Override public double transformY(double x, double y, int argb) {
		return pointTransformation.transformY(x, y);
	}
}

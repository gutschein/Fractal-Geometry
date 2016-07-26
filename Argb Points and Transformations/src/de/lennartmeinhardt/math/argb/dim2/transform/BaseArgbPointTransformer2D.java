package de.lennartmeinhardt.math.argb.dim2.transform;

import de.lennartmeinhardt.math.Transformer;
import de.lennartmeinhardt.math.argb.dim2.point.ArgbPoint2D;
import de.lennartmeinhardt.math.argb.dim2.point.MutableArgbPoint2D;

/**
 * A basic implementation of a transformation that can transform points and argb values. 
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformation acceptable for transforming
 */
public class BaseArgbPointTransformer2D
implements Transformer<MutableArgbPoint2D, ArgbPoint2D, ArgbPointTransformation2D> {

	// the current point
	private final MutableArgbPoint2D argbPoint = new MutableArgbPoint2D();

	
	@Override public void reset(ArgbPoint2D startPoint) {
		if(startPoint != null)
			this.argbPoint.set(startPoint);
	}
	
	@Override public MutableArgbPoint2D get() {
		return argbPoint;
	}
	
	@Override public void transform(ArgbPointTransformation2D transformation) {
		double xNew = transformation.transformX(argbPoint);
		double yNew = transformation.transformY(argbPoint);
		int argbNew = transformation.transformArgb(argbPoint);
		argbPoint.set(xNew, yNew, argbNew);
	}
}

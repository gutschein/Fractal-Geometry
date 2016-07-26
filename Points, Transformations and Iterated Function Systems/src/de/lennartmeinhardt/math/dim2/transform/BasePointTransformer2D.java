package de.lennartmeinhardt.math.dim2.transform;

import de.lennartmeinhardt.math.Transformer;
import de.lennartmeinhardt.math.dim2.point.MutablePoint2D;
import de.lennartmeinhardt.math.dim2.point.Point2D;

/**
 * A simple {@link Transformer} that transforms two-dimensional points.
 * 
 * @author Lennart Meinhardt
 */
public class BasePointTransformer2D
implements Transformer<MutablePoint2D, Point2D, PointTransformation2D> {

	// the point
	private final MutablePoint2D point = new MutablePoint2D();
	
	
	@Override public void reset(Point2D startPoint) {
		if(startPoint != null)
			this.point.set(startPoint);
	}
	
	@Override public MutablePoint2D get() {
		return point;
	}
	
	@Override public void transform(PointTransformation2D transformation) {
		double xNew = transformation.transformX(point);
		double yNew = transformation.transformY(point);
		point.set(xNew, yNew);
	}
}

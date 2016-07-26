package de.lennartmeinhardt.math.dim3.transform;

import de.lennartmeinhardt.math.Transformer;
import de.lennartmeinhardt.math.dim3.point.MutablePoint3D;
import de.lennartmeinhardt.math.dim3.point.Point3D;

/**
 * A simple {@link Transformer} that transforms three-dimensional points.
 * 
 * @author Lennart Meinhardt
 */
public class BasePointTransformer3D
implements Transformer<MutablePoint3D, Point3D, PointTransformation3D> {

	// the point
	private final MutablePoint3D point = new MutablePoint3D();

	
	@Override public void reset(Point3D startPoint) {
		if(startPoint != null)
			this.point.set(startPoint);
	}
	
	@Override public MutablePoint3D get() {
		return point;
	}
	
	@Override public void transform(PointTransformation3D transformation) {
		double xNew = transformation.transformX(point);
		double yNew = transformation.transformY(point);
		double zNew = transformation.transformZ(point);
		point.set(xNew, yNew, zNew);
	}
}

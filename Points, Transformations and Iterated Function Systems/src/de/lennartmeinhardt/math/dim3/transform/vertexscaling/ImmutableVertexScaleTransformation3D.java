package de.lennartmeinhardt.math.dim3.transform.vertexscaling;

import de.lennartmeinhardt.math.dim3.point.ImmutablePoint3D;
import de.lennartmeinhardt.math.dim3.point.Point3D;

public final class ImmutableVertexScaleTransformation3D implements VertexScalingTransformation3D {
	
	private final Point3D vertex;
	private final double factor;

	public ImmutableVertexScaleTransformation3D(double vertX, double vertY, double vertZ, double factor) {
		this.vertex = new ImmutablePoint3D(vertX, vertY, vertZ);
		this.factor = factor;
	}
	
	public ImmutableVertexScaleTransformation3D(Point3D point, double factor) {
		this(point.getX(), point.getY(), point.getZ(), factor);
	}
	
	@Override public double getScaling() {
		return factor;
	}
	
	@Override public Point3D getVertex() {
		return vertex;
	}
}

package de.lennartmeinhardt.math.dim2.transform.vertexscaling;

import de.lennartmeinhardt.math.dim2.point.ImmutablePoint2D;
import de.lennartmeinhardt.math.dim2.point.Point2D;

public final class ImmutableVertexScaleTransformation2D implements VertexScalingTransformation2D {
	
	private final Point2D vertex;
	private final double factor;

	public ImmutableVertexScaleTransformation2D(double vertX, double vertY, double factor) {
		this.vertex = new ImmutablePoint2D(vertX, vertY);
		this.factor = factor;
	}
	
	public ImmutableVertexScaleTransformation2D(Point2D point, double factor) {
		this(point.getX(), point.getY(), factor);
	}
	
	@Override public double getScaling() {
		return factor;
	}
	
	@Override public Point2D getVertex() {
		return vertex;
	}
}

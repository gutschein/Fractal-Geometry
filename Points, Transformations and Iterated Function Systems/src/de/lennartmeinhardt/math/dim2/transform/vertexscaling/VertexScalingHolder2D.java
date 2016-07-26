package de.lennartmeinhardt.math.dim2.transform.vertexscaling;

import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface VertexScalingHolder2D extends VertexScalingHolder<Point2D> {
	
	default double getVertexX() {
		return getVertex().getX();
	}

	default double getVertexY() {
		return getVertex().getY();
	}
}

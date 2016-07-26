package de.lennartmeinhardt.math.dim3.transform.vertexscaling;

import de.lennartmeinhardt.math.dim3.point.Point3D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface VertexScalingHolder3D extends VertexScalingHolder<Point3D> {
	
	default double getVertexX() {
		return getVertex().getX();
	}

	default double getVertexY() {
		return getVertex().getY();
	}
	
	default double getVertexZ() {
		return getVertex().getZ();
	}
}

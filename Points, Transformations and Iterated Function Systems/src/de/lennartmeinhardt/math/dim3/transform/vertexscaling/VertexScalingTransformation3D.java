package de.lennartmeinhardt.math.dim3.transform.vertexscaling;

import de.lennartmeinhardt.math.dim3.transform.PointTransformation3D;

public interface VertexScalingTransformation3D
extends PointTransformation3D, AdapterAffineToVertexScale3D {
	
	@Override default double transformX(double x, double y, double z) {
		return getScaling() * x + (1 - getScaling()) * getVertexX();
	}
	
	@Override default double transformY(double x, double y, double z) {
		return getScaling() * y + (1 - getScaling()) * getVertexY();
	}
	
	@Override default double transformZ(double x, double y, double z) {
		return getScaling() * z + (1 - getScaling() * getVertexZ());
	}
}

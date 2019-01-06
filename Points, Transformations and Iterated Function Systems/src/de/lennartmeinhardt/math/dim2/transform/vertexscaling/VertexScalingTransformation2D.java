package de.lennartmeinhardt.math.dim2.transform.vertexscaling;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.dim2.transform.affine.AdapterProjectiveToAffineTransformation2D;

public interface VertexScalingTransformation2D
extends PointTransformation2D, AdapterAffineToVertexScalingTransformation2D, AdapterProjectiveToAffineTransformation2D {
	
	@Override default double transformX(double x, double y) {
		return getScaling() * x + (1 - getScaling()) * getVertexX();
	}
	
	@Override default double transformY(double x, double y) {
		return getScaling() * y + (1 - getScaling()) * getVertexY();
	}
}

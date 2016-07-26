package de.lennartmeinhardt.math.dim2.transform.vertexscaling;

import de.lennartmeinhardt.math.dim2.transform.affine.AffineHolder2D;

public interface AdapterAffineToVertexScale2D extends AffineHolder2D, VertexScalingHolder2D {

	@Override default double getMxx() {
		return getScaling();
	}

	@Override default double getMxy() {
		return 0;
	}

	@Override default double getTx() {
		return (1 - getScaling()) * getVertexX();
	}

	@Override default double getMyx() {
		return 0;
	}

	@Override default double getMyy() {
		return getScaling();
	}

	@Override default double getTy() {
		return (1 - getScaling()) * getVertexY();
	}

}

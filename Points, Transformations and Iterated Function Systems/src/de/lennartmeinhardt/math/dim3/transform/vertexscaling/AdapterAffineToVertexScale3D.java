package de.lennartmeinhardt.math.dim3.transform.vertexscaling;

import de.lennartmeinhardt.math.dim3.transform.affine.AffineHolder3D;

public interface AdapterAffineToVertexScale3D extends AffineHolder3D, VertexScalingHolder3D {

	@Override default double getMxx() {
		return getScaling();
	}

	@Override default double getMxy() {
		return 0;
	}
	
	@Override default double getMxz() {
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
	
	@Override default double getMyz() {
		return 0;
	}

	@Override default double getTy() {
		return (1 - getScaling()) * getVertexY();
	}
	
	@Override default double getMzx() {
		return 0;
	}
	
	@Override default double getMzy() {
		return 0;
	}
	
	@Override default double getMzz() {
		return getScaling();
	}
	
	@Override default double getTz() {
		return (1 - getScaling()) * getVertexZ();
	}
}

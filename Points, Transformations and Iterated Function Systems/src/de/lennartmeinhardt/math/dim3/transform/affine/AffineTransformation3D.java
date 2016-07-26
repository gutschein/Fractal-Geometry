package de.lennartmeinhardt.math.dim3.transform.affine;

import de.lennartmeinhardt.math.dim3.transform.PointTransformation3D;

public interface AffineTransformation3D
extends AffineHolder3D, PointTransformation3D {
	
	@Override default double transformX(double x, double y, double z) {
		return getMxx() * x + getMxy() * y + getMxz() * z + getTx();
	}
	
	@Override default double transformY(double x, double y, double z) {
		return getMyx() * x + getMyy() * y + getMyz() * z + getTy();
	}
	
	@Override default double transformZ(double x, double y, double z) {
		return getMzx() * x + getMzy() * y + getMzz() * z + getTz();
	}
}

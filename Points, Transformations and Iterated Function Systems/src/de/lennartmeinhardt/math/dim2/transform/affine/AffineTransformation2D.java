package de.lennartmeinhardt.math.dim2.transform.affine;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;

public interface AffineTransformation2D
extends PointTransformation2D, AdapterProjectiveToAffine2D, AdapterScaleRotateTranslateToAffine2D {
	
	@Override default double transformX(double x, double y) {
		return getMxx() * x + getMxy() * y + getTx();
	}
	
	@Override default double transformY(double x, double y) {
		return getMyx() * x + getMyy() * y + getTy();
	}
}

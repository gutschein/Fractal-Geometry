package de.lennartmeinhardt.math.dim2.transform.affine;

import de.lennartmeinhardt.math.dim2.transform.srt.ScaleRotateTranslateHolder2D;
import de.lennartmeinhardt.math.util.MathUtil;

public interface AdapterScaleRotateTranslateToAffine2D extends ScaleRotateTranslateHolder2D, AffineHolder2D {

	@Override default double getRotateX() {
		return MathUtil.arccosRad(getMxx(), getMyx());
	}
	
	@Override default double getRotateY() {
		return MathUtil.arccosRad(getMxy(), getMyy());
	}
	
	@Override default double getScaleX() {
		return MathUtil.getVectorNorm2(getMxx(), getMyx());
	}
	
	@Override default double getScaleY() {
		return MathUtil.getVectorNorm2(getMxy(), getMyy());
	}
	
	@Override default double getTranslateX() {
		return getTx();
	}
	
	@Override default double getTranslateY() {
		return getTy();
	}
}

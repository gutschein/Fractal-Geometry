package de.lennartmeinhardt.math.dim2.transform.srt;

import de.lennartmeinhardt.math.dim2.transform.affine.AffineTransformation2D;

public interface AdapterAffineToScaleRotateTranslateTransformation2D extends AffineTransformation2D, ScaleRotateTranslateHolder2D {
	
	@Override default double getMxx() {
		return Math.cos(getRotateX()) * getScaleX();
	}
	
	@Override default double getMxy() {
		return Math.cos(getRotateY()) * getScaleY();
	}
	
	@Override default double getTx() {
		return getTranslateX();
	}
	
	@Override default double getMyx() {
		return Math.sin(getRotateX()) * getScaleX();
	}
	
	@Override default double getMyy() {
		return Math.sin(getRotateY()) * getScaleY();
	}
	
	@Override default double getTy() {
		return getTranslateY();
	}
}

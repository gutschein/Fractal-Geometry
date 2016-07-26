package de.lennartmeinhardt.math.dim2.transform.srt;

public final class ImmutableScaleRotateTranslateTransformation2D implements ScaleRotateTranslateTransformation2D {
	
	private final double	scaleX, scaleY,
							rotateX, rotateY,
							translateX, translateY;

	public ImmutableScaleRotateTranslateTransformation2D(
			double scaleX, double scaleY,
			double rotateX, double rotateY,
			double translateX, double translateY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.rotateX = rotateX;
		this.rotateY = rotateY;
		this.translateX = translateX;
		this.translateY = translateY;
	}
	
	public ImmutableScaleRotateTranslateTransformation2D(ScaleRotateTranslateHolder2D holder) {
		this(
				holder.getScaleX(), holder.getScaleY(),
				holder.getRotateX(), holder.getRotateY(),
				holder.getTranslateX(), holder.getTranslateY());
	}
	
	public ImmutableScaleRotateTranslateTransformation2D(double[] m) {
		this(m[0], m[1], m[2], m[3], m[4], m[5]);
	}
	
	
	@Override public double getScaleX() {
		return scaleX;
	}
	
	@Override public double getScaleY() {
		return scaleY;
	}
	
	@Override public double getRotateX() {
		return rotateX;
	}
	
	@Override public double getRotateY() {
		return rotateY;
	}
	
	@Override public double getTranslateX() {
		return translateX;
	}
	
	@Override public double getTranslateY() {
		return translateY;
	}
}

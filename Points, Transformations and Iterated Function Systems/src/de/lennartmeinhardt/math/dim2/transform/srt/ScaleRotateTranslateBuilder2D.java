package de.lennartmeinhardt.math.dim2.transform.srt;

public class ScaleRotateTranslateBuilder2D implements MutableScaleRotateTranslateHolder2D {
	
	private double scaleX;
	private double scaleY;
	private double rotateX;
	private double rotateY;
	private double translateX;
	private double translateY;
	
	public ScaleRotateTranslateBuilder2D() {
		setToIdentity();
	}
	
	public ScaleRotateTranslateBuilder2D(ScaleRotateTranslateHolder2D holder) {
		initFromScaleRotateTranslateHolder2D(holder);
	}
	
	public ScaleRotateTranslateBuilder2D(
			double scaleX, double scaleY,
			double rotateX, double rotateY,
			double translateX, double translateY) {
		setAll(scaleX, scaleY, rotateX, rotateY, translateX, translateY);
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
	
	@Override public MutableScaleRotateTranslateHolder2D setScaleX(double scaleX) {
		this.scaleX = scaleX;
		return this;
	}
	
	@Override public MutableScaleRotateTranslateHolder2D setScaleY(double scaleY) {
		this.scaleY = scaleY;
		return this;
	}
	
	@Override public MutableScaleRotateTranslateHolder2D setRotateX(double rotateX) {
		this.rotateX = rotateX;
		return this;
	}
	
	@Override public MutableScaleRotateTranslateHolder2D setRotateY(double rotateY) {
		this.rotateY = rotateY;
		return this;
	}
	
	@Override public MutableScaleRotateTranslateHolder2D setTranslateX(double translateX) {
		this.translateX = translateX;
		return this;
	}
	
	@Override public MutableScaleRotateTranslateHolder2D setTranslateY(double translateY) {
		this.translateY = translateY;
		return this;
	}
}

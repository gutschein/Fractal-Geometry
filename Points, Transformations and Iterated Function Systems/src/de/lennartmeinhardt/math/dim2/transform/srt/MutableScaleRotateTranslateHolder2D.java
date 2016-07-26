package de.lennartmeinhardt.math.dim2.transform.srt;

import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface MutableScaleRotateTranslateHolder2D extends ScaleRotateTranslateHolder2D {

	MutableScaleRotateTranslateHolder2D setScaleX(double scaleX);
	MutableScaleRotateTranslateHolder2D setScaleY(double scaleY);

	MutableScaleRotateTranslateHolder2D setRotateX(double rotateX);
	MutableScaleRotateTranslateHolder2D setRotateY(double rotateY);

	MutableScaleRotateTranslateHolder2D setTranslateX(double translateX);
	MutableScaleRotateTranslateHolder2D setTranslateY(double translateY);
	
	default MutableScaleRotateTranslateHolder2D setTranslations(double translateX, double translateY) {
		return setTranslateX(translateX).setTranslateY(translateY);
	}
	
	default MutableScaleRotateTranslateHolder2D setScalings(double scaleX, double scaleY) {
		return setScaleX(scaleX).setScaleY(scaleY);
	}
	
	default MutableScaleRotateTranslateHolder2D setTransformX(double scaleX, double rotateX, double translateX) {
		return setScaleX(scaleX).setRotateX(rotateX).setTranslateX(translateX);
	}
	
	default MutableScaleRotateTranslateHolder2D setTransformY(double scaleY, double rotateY, double translateY) {
		return setScaleY(scaleY).setRotateY(rotateY).setTranslateY(translateY);
	}
	
	default MutableScaleRotateTranslateHolder2D setAll(double scaleX, double scaleY, double rotateX, double rotateY, double translateX, double translateY) {
		return setTransformX(scaleX, rotateX, translateX).setTransformY(scaleY, rotateY, translateY);
	}
	
	default MutableScaleRotateTranslateHolder2D initFromVertexHolder(VertexScalingHolder<? extends Point2D> vertexScaleHolder) {
		double scale = vertexScaleHolder.getScaling();
		Point2D point = vertexScaleHolder.getVertex();
		
		setCommonRotation(0);
		setCommonScaling(scale);
		setTranslation(point);
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D initFromScaleRotateTranslateHolder2D(ScaleRotateTranslateHolder2D srtHolder) {
		setScaleX(srtHolder.getScaleX());
		setScaleX(srtHolder.getScaleX());
		setRotateX(srtHolder.getRotateX());
		setRotateY(srtHolder.getRotateY());
		setTranslateX(srtHolder.getTranslateX());
		setTranslateY(srtHolder.getTranslateY());
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D setCommonTranslation(double t) {
		setTranslations(t, t);
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D setLinearPart(double scaleX, double scaleY, double rotateX, double rotateY) {
		setScaleX(scaleX);
		setScaleY(scaleY);
		setRotateX(rotateX);
		setRotateY(rotateY);
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D setCommonScaling(double scale) {
		setScalings(scale, scale);
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D setRotations(double rotateX, double rotateY) {
		return setRotateX(rotateX).setRotateY(rotateY);
	}
	
	default MutableScaleRotateTranslateHolder2D setCommonRotation(double rotation) {
		return setRotations(rotation, rotation);
	}
	
	default MutableScaleRotateTranslateHolder2D setToIdentity() {
		setCommonScaling(1);
		setCommonRotation(0);
		setCommonTranslation(0);
		return this;
	}
	
	default MutableScaleRotateTranslateHolder2D setTranslation(Point2D point) {
		setTranslations(point.getX(), point.getY());
		return this;
	}
	
	default <S> S buildFromScaleRotateTranslateHolder2D(CreatorFromScaleRotateTranslateHolder2D<? extends S> creator) {
		return creator.get(this);
	}
	
	default <S> S buildFromMarixValues(CreatorFromMatrixValues<? extends S> creator) {
		return creator.get(getScaleX(), getScaleY(), getRotateX(), getRotateY(), getTranslateX(), getTranslateY());
	}
	
	
	
	public static interface CreatorFromScaleRotateTranslateHolder2D <S> {
		S get(ScaleRotateTranslateHolder2D source);
	}
	
	public static interface CreatorFromMatrixValues <S> {
		S get(double scaleX, double scaleY, double rotateX, double rotateY, double translateX, double translateY);
	}
}

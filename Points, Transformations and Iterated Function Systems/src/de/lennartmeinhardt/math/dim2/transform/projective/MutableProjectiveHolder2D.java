package de.lennartmeinhardt.math.dim2.transform.projective;

import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.dim2.transform.affine.AffineHolder2D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface MutableProjectiveHolder2D extends ProjectiveHolder2D {
	
	MutableProjectiveHolder2D setXNominatorScaleX(double nominatorMxx);
	MutableProjectiveHolder2D setXNominatorScaleY(double nominatorMxy);
	MutableProjectiveHolder2D setXNominatorTranslation(double nominatorTx);
	
	MutableProjectiveHolder2D setYNominatorScaleX(double nominatorMyx);
	MutableProjectiveHolder2D setYNominatorScaleY(double nominatorMyy);
	MutableProjectiveHolder2D setYNominatorTranslation(double nominatorTy);
	
	MutableProjectiveHolder2D setDenominatorScaleX(double denominatorScaleByX);
	MutableProjectiveHolder2D setDenominatorScaleY(double denominatorScaleByY);
	MutableProjectiveHolder2D setDenominatorTranslation(double denominatorTranslation);
	
	
	default MutableProjectiveHolder2D setXNominatorAffine(double nominatorMxx, double nominatorMxy, double nominatorTx) {
		setXNominatorScaleX(nominatorMxx);
		setXNominatorScaleY(nominatorMxy);
		setXNominatorTranslation(nominatorTx);
		return this;
	}
	
	default MutableProjectiveHolder2D setYNominatorAffine(double nominatorMyx, double nominatorMyy, double nominatorTy) {
		setYNominatorScaleX(nominatorMyx);
		setYNominatorScaleY(nominatorMyy);
		setYNominatorTranslation(nominatorTy);
		return this;
	}
	
	default MutableProjectiveHolder2D setDenominatorAffine(double denominatorScaleByX, double denominatorScaleByY, double denominatorTranslation) {
		setDenominatorScaleX(denominatorScaleByX);
		setDenominatorScaleY(denominatorScaleByY);
		setDenominatorTranslation(denominatorTranslation);
		return this;
	}
	
	default MutableProjectiveHolder2D setMatrix(double nominatorMxx, double nominatorMxy, double nominatorTx, double nominatorMyx, double nominatorMyy, double nominatorTy, double denominatorScaleByX, double denominatorScaleByY, double denominatorTranslation) {
		setXNominatorAffine(nominatorMxx, nominatorMxy, nominatorTx);
		setYNominatorAffine(nominatorMyx, nominatorMyy, nominatorTy);
		setDenominatorAffine(denominatorScaleByX, denominatorScaleByY, denominatorTranslation);
		return this;
	}
	
	default MutableProjectiveHolder2D initFromVertexHolder(VertexScalingHolder<? extends Point2D> vertexScaleHolder) {
		double scale = vertexScaleHolder.getScaling();
		Point2D point = vertexScaleHolder.getVertex();

		setNominatorCommonShear(0);
		setNominatorCommonScaling(scale);
		setNominatorTranslations(point.getX(), point.getY());
		setDenominatorAffine(0, 0, 1);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorTranslations(double tx, double ty) {
		setXNominatorTranslation(tx);
		setYNominatorTranslation(ty);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorScalings(double mxx, double myy) {
		setXNominatorScaleX(mxx);
		setYNominatorScaleY(myy);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorTranslations(Point2D point) {
		setNominatorTranslations(point.getX(), point.getY());
		return this;
	}
	
	default MutableProjectiveHolder2D initFromAffineHolder2D(AffineHolder2D holder) {
		setXNominatorAffine(holder.getMxx(), holder.getMxy(), holder.getTx());
		setYNominatorAffine(holder.getMyx(), holder.getMyy(), holder.getTy());
		setDenominatorAffine(0, 0, 1);
		return this;
	}
	
	default MutableProjectiveHolder2D initFromProjectiveHolder2D(ProjectiveHolder2D holder) {
		setXNominatorAffine(holder.getXNominatorScaleX(), holder.getXNominatorScaleY(), holder.getXNominatorTranslation());
		setYNominatorAffine(holder.getYNominatorScaleX(), holder.getYNominatorScaleY(), holder.getYNominatorTranslation());
		setDenominatorAffine(holder.getDenominatorScaleX(), holder.getDenominatorScaleY(), holder.getDenominatorTranslation());
		return this;
	}
	
	default MutableProjectiveHolder2D postConcat(ProjectiveHolder2D post) {
		ProjectiveHolder2D pre = this;
		double nominatorMxx = 	post.getXNominatorScaleX() * pre.getXNominatorScaleX() + 		post.getXNominatorScaleY() * pre.getYNominatorScaleX() + 		post.getXNominatorTranslation() * pre.getDenominatorScaleX();
		double nominatorMxy = 	post.getXNominatorScaleX() * pre.getXNominatorScaleY() + 		post.getXNominatorScaleY() * pre.getYNominatorScaleY() + 		post.getXNominatorTranslation() * pre.getDenominatorScaleY();
		double nominatorTx = 	post.getXNominatorScaleX() * pre.getXNominatorTranslation() + 	post.getXNominatorScaleY() * pre.getYNominatorTranslation() + 	post.getXNominatorTranslation() * pre.getDenominatorTranslation();
		setXNominatorAffine(nominatorMxx, nominatorMxy, nominatorTx);

		double nominatorMyx = 	post.getYNominatorScaleX() * pre.getXNominatorScaleX() + 		post.getYNominatorScaleY() * pre.getYNominatorScaleX() + 		post.getYNominatorTranslation() * pre.getDenominatorScaleX();
		double nominatorMyy = 	post.getYNominatorScaleX() * pre.getXNominatorScaleY() + 		post.getYNominatorScaleY() * pre.getYNominatorScaleY() + 		post.getYNominatorTranslation() * pre.getDenominatorScaleY();
		double nominatorTy = 	post.getYNominatorScaleX() * pre.getXNominatorTranslation() + 	post.getYNominatorScaleY() * pre.getYNominatorTranslation() + 	post.getYNominatorTranslation() * pre.getDenominatorTranslation();
		setYNominatorAffine(nominatorMyx, nominatorMyy, nominatorTy);

		double denominatorScaleByX = 	post.getDenominatorScaleX() * pre.getXNominatorScaleX() + 		post.getDenominatorScaleY() * pre.getYNominatorScaleX() + 		post.getDenominatorTranslation() * pre.getDenominatorScaleX();
		double denominatorScaleByY = 	post.getDenominatorScaleX() * pre.getXNominatorScaleY() + 		post.getDenominatorScaleY() * pre.getYNominatorScaleY() + 		post.getDenominatorTranslation() * pre.getDenominatorScaleY();
		double denominatorTranslation = post.getDenominatorScaleX() * pre.getXNominatorTranslation() + 	post.getDenominatorScaleY() * pre.getYNominatorTranslation() + 	post.getDenominatorTranslation() * pre.getDenominatorTranslation();
		setDenominatorAffine(denominatorScaleByX, denominatorScaleByY, denominatorTranslation);
		
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorCommonTranslation(double t) {
		setNominatorTranslations(t, t);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorLinearPart(double mxx, double mxy, double myx, double myy) {
		setXNominatorScaleX(mxx);
		setXNominatorScaleY(mxy);
		setYNominatorScaleX(myx);
		setYNominatorScaleY(myy);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorShear(double mxy, double myx) {
		setXNominatorScaleY(mxy);
		setYNominatorScaleX(myx);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorCommonShear(double shear) {
		setNominatorShear(shear, shear);
		return this;
	}
	
	default MutableProjectiveHolder2D setNominatorCommonScaling(double scale) {
		setNominatorScalings(scale, scale);
		return this;
	}
	
	default MutableProjectiveHolder2D setToIdentity() {
		setNominatorCommonScaling(1);
		setNominatorCommonShear(0);
		setNominatorCommonTranslation(0);
		setDenominatorAffine(0, 0, 1);
		return this;
	}
	
	default <P> P build(CreatorFromProjectiveHolder2D<? extends P> creator) {
		return creator.get(this);
	}
	
	default <P> P build(CreatorFromMatrixValues<? extends P> creator) {
		return creator.get(
				getXNominatorScaleX(), getXNominatorScaleY(), getXNominatorTranslation(),
				getYNominatorScaleX(), getYNominatorScaleY(), getYNominatorTranslation(),
				getDenominatorScaleX(), getDenominatorScaleY(), getDenominatorTranslation());
	}
	
	
	public static interface CreatorFromProjectiveHolder2D <P> {
		P get(ProjectiveHolder2D source);
	}
	
	public static interface CreatorFromMatrixValues <P> {
		P get(
				double nominatorMxx, double nominatorMxy, double nominatorTx,
				double nominatorMyx, double nominatorMyy, double nominatorTy,
				double denominatorSx, double denominatorSy, double denominatorT);
	}
}

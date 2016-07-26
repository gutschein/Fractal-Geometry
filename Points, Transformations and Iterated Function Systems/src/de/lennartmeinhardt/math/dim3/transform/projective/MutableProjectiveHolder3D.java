package de.lennartmeinhardt.math.dim3.transform.projective;

import de.lennartmeinhardt.math.dim3.point.Point3D;
import de.lennartmeinhardt.math.dim3.transform.affine.AffineHolder3D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface MutableProjectiveHolder3D extends ProjectiveHolder3D {
	
	MutableProjectiveHolder3D setXNominatorScaleX(double nominatorMxx);
	MutableProjectiveHolder3D setXNominatorScaleY(double nominatorMxy);
	MutableProjectiveHolder3D setXNominatorScaleZ(double nominatorMxz);
	MutableProjectiveHolder3D setXNominatorTranslation(double nominatorTx);
	
	MutableProjectiveHolder3D setYNominatorScaleX(double nominatorMyx);
	MutableProjectiveHolder3D setYNominatorScaleY(double nominatorMyy);
	MutableProjectiveHolder3D setYNominatorScaleZ(double nominatorMyz);
	MutableProjectiveHolder3D setYNominatorTranslation(double nominatorTy);
	
	MutableProjectiveHolder3D setZNominatorScaleX(double nominatorMzx);
	MutableProjectiveHolder3D setZNominatorScaleY(double nominatorMzy);
	MutableProjectiveHolder3D setZNominatorScaleZ(double nominatorMzz);
	MutableProjectiveHolder3D setZNominatorTranslation(double nominatorTz);
	
	MutableProjectiveHolder3D setDenominatorScaleX(double denominatorScaleByX);
	MutableProjectiveHolder3D setDenominatorScaleY(double denominatorScaleByY);
	MutableProjectiveHolder3D setDenominatorScaleZ(double denominatorScaleByZ);
	MutableProjectiveHolder3D setDenominatorTranslation(double denominatorTranslation);
	
	
	default MutableProjectiveHolder3D setXNominatorAffine(
			double nominatorMxx, double nominatorMxy, double nominatorMxz, double nominatorTx) {
		setXNominatorScaleX(nominatorMxx);
		setXNominatorScaleY(nominatorMxy);
		setXNominatorScaleZ(nominatorMxz);
		setXNominatorTranslation(nominatorTx);
		return this;
	}
	
	default MutableProjectiveHolder3D setYNominatorAffine(
			double nominatorMyx, double nominatorMyy, double nominatorMyz, double nominatorTy) {
		setYNominatorScaleX(nominatorMyx);
		setYNominatorScaleY(nominatorMyy);
		setYNominatorScaleZ(nominatorMyz);
		setYNominatorTranslation(nominatorTy);
		return this;
	}
	
	default MutableProjectiveHolder3D setZNominatorAffine(
			double nominatorMzx, double nominatorMzy, double nominatorMzz, double nominatorTz) {
		setZNominatorScaleX(nominatorMzx);
		setZNominatorScaleY(nominatorMzy);
		setZNominatorScaleZ(nominatorMzz);
		setZNominatorTranslation(nominatorTz);
		return this;
	}
	
	default MutableProjectiveHolder3D setDenominatorAffine(
			double denominatorScaleByX, double denominatorScaleByY, double denominatorScaleByZ, double denominatorTranslation) {
		setDenominatorScaleX(denominatorScaleByX);
		setDenominatorScaleY(denominatorScaleByY);
		setDenominatorScaleZ(denominatorScaleByZ);
		setDenominatorTranslation(denominatorTranslation);
		return this;
	}
	
	default MutableProjectiveHolder3D setMatrix(
			double nominatorMxx, double nominatorMxy, double nominatorMxz, double nominatorTx,
			double nominatorMyx, double nominatorMyy, double nominatorMyz, double nominatorTy,
			double nominatorMzx, double nominatorMzy, double nominatorMzz, double nominatorTz,
			double denominatorScaleByX, double denominatorScaleByY, double denominatorScaleByZ, double denominatorTranslation) {
		setXNominatorAffine(nominatorMxx, nominatorMxy, nominatorMxz, nominatorTx);
		setYNominatorAffine(nominatorMyx, nominatorMyy, nominatorMyz, nominatorTy);
		setZNominatorAffine(nominatorMzx, nominatorMzy, nominatorMzz, nominatorTz);
		setDenominatorAffine(denominatorScaleByX, denominatorScaleByY, denominatorScaleByZ, denominatorTranslation);
		return this;
	}
	
	default MutableProjectiveHolder3D initFromVertexHolder(VertexScalingHolder<? extends Point3D> vertexScaleHolder) {
		double scale = vertexScaleHolder.getScaling();
		Point3D point = vertexScaleHolder.getVertex();

		setNominatorCommonShearXY(0);
		setNominatorCommonShearYZ(0);
		setNominatorCommonShearZX(0);
		setNominatorCommonScaling(scale);
		setNominatorTranslation(point);
		setDenominatorAffine(0, 0, 0, 1);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorTranslation(Point3D point) {
		setNominatorTranslations(point.getX(), point.getY(), point.getZ());
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorTranslations(double tx, double ty, double tz) {
		setXNominatorTranslation(tx);
		setYNominatorTranslation(ty);
		setZNominatorTranslation(tz);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorScalings(double mxx, double myy, double mzz) {
		setXNominatorScaleX(mxx);
		setYNominatorScaleY(myy);
		setZNominatorScaleZ(mzz);
		return this;
	}
	
	default MutableProjectiveHolder3D initFromAffineHolder3D(AffineHolder3D holder) {
		setXNominatorAffine(holder.getMxx(), holder.getMxy(), holder.getMxz(), holder.getTx());
		setYNominatorAffine(holder.getMyx(), holder.getMyy(), holder.getMyz(), holder.getTy());
		setYNominatorAffine(holder.getMzx(), holder.getMzy(), holder.getMzz(), holder.getTz());
		setDenominatorAffine(0, 0, 0, 1);
		return this;
	}
	
	default MutableProjectiveHolder3D initFromProjectiveHolder3D(ProjectiveHolder3D holder) {
		setXNominatorAffine(holder.getXNominatorScaleX(), holder.getXNominatorScaleY(), holder.getXNominatorScaleZ(), holder.getXNominatorTranslation());
		setYNominatorAffine(holder.getYNominatorScaleX(), holder.getYNominatorScaleY(), holder.getYNominatorScaleZ(), holder.getYNominatorTranslation());
		setZNominatorAffine(holder.getZNominatorScaleX(), holder.getZNominatorScaleY(), holder.getZNominatorScaleZ(), holder.getZNominatorTranslation());
		setDenominatorAffine(0, 0, 0, 1);
		return this;
	}
	
	default MutableProjectiveHolder3D postConcat(ProjectiveHolder3D post) {
		ProjectiveHolder3D pre = this;
		double nominatorMxx = 	post.getXNominatorScaleX() * pre.getXNominatorScaleX() + 		post.getXNominatorScaleY() * pre.getYNominatorScaleX() + 		post.getXNominatorScaleZ() * pre.getZNominatorScaleX() + 		post.getXNominatorTranslation() * pre.getDenominatorScaleX();
		double nominatorMxy = 	post.getXNominatorScaleX() * pre.getXNominatorScaleY() + 		post.getXNominatorScaleY() * pre.getYNominatorScaleY() + 		post.getXNominatorScaleZ() * pre.getZNominatorScaleY() + 		post.getXNominatorTranslation() * pre.getDenominatorScaleY();
		double nominatorMxz = 	post.getXNominatorScaleX() * pre.getXNominatorScaleZ() + 		post.getXNominatorScaleY() * pre.getYNominatorScaleZ() + 		post.getXNominatorScaleZ() * pre.getZNominatorScaleZ() + 		post.getXNominatorTranslation() * pre.getDenominatorScaleZ();
		double nominatorTx = 	post.getXNominatorScaleX() * pre.getXNominatorTranslation() + 	post.getXNominatorScaleY() * pre.getYNominatorTranslation() + 	post.getXNominatorScaleZ() * pre.getZNominatorTranslation() + 	post.getXNominatorTranslation() * pre.getDenominatorTranslation();
		setXNominatorAffine(nominatorMxx, nominatorMxy, nominatorMxz, nominatorTx);

		double nominatorMyx = 	post.getYNominatorScaleX() * pre.getXNominatorScaleX() + 		post.getYNominatorScaleY() * pre.getYNominatorScaleX() + 		post.getYNominatorScaleZ() * pre.getZNominatorScaleX() + 		post.getYNominatorTranslation() * pre.getDenominatorScaleX();
		double nominatorMyy = 	post.getYNominatorScaleX() * pre.getXNominatorScaleY() + 		post.getYNominatorScaleY() * pre.getYNominatorScaleY() + 		post.getYNominatorScaleZ() * pre.getZNominatorScaleY() + 		post.getYNominatorTranslation() * pre.getDenominatorScaleY();
		double nominatorMyz = 	post.getYNominatorScaleX() * pre.getXNominatorScaleZ() + 		post.getYNominatorScaleY() * pre.getYNominatorScaleZ() + 		post.getYNominatorScaleZ() * pre.getZNominatorScaleZ() + 		post.getYNominatorTranslation() * pre.getDenominatorScaleZ();
		double nominatorTy = 	post.getYNominatorScaleX() * pre.getXNominatorTranslation() + 	post.getYNominatorScaleY() * pre.getYNominatorTranslation() + 	post.getYNominatorScaleZ() * pre.getZNominatorTranslation() + 	post.getYNominatorTranslation() * pre.getDenominatorTranslation();
		setYNominatorAffine(nominatorMyx, nominatorMyy, nominatorMyz, nominatorTy);
		
		double nominatorMzx = 	post.getZNominatorScaleX() * pre.getXNominatorScaleX() + 		post.getZNominatorScaleY() * pre.getYNominatorScaleX() + 		post.getZNominatorScaleZ() * pre.getZNominatorScaleX() + 		post.getZNominatorTranslation() * pre.getDenominatorScaleX();
		double nominatorMzy = 	post.getZNominatorScaleX() * pre.getXNominatorScaleY() + 		post.getZNominatorScaleY() * pre.getYNominatorScaleY() + 		post.getZNominatorScaleZ() * pre.getZNominatorScaleY() + 		post.getZNominatorTranslation() * pre.getDenominatorScaleY();
		double nominatorMzz = 	post.getZNominatorScaleX() * pre.getXNominatorScaleZ() + 		post.getZNominatorScaleY() * pre.getYNominatorScaleZ() + 		post.getZNominatorScaleZ() * pre.getZNominatorScaleZ() + 		post.getZNominatorTranslation() * pre.getDenominatorScaleZ();
		double nominatorTz = 	post.getZNominatorScaleX() * pre.getXNominatorTranslation() + 	post.getZNominatorScaleY() * pre.getYNominatorTranslation() + 	post.getZNominatorScaleZ() * pre.getZNominatorTranslation() + 	post.getZNominatorTranslation() * pre.getDenominatorTranslation();
		setZNominatorAffine(nominatorMzx, nominatorMzy, nominatorMzz, nominatorTz);

		double denominatorScaleByX = 	post.getDenominatorScaleX() * pre.getXNominatorScaleX() + 		post.getDenominatorScaleY() * pre.getYNominatorScaleX() + 		post.getDenominatorScaleZ() * pre.getZNominatorScaleX() + 		post.getDenominatorTranslation() * pre.getDenominatorScaleX();
		double denominatorScaleByY = 	post.getDenominatorScaleX() * pre.getXNominatorScaleY() + 		post.getDenominatorScaleY() * pre.getYNominatorScaleY() + 		post.getDenominatorScaleZ() * pre.getZNominatorScaleY() + 		post.getDenominatorTranslation() * pre.getDenominatorScaleY();
		double denominatorScaleByZ = 	post.getDenominatorScaleX() * pre.getXNominatorScaleZ() + 		post.getDenominatorScaleY() * pre.getYNominatorScaleZ() + 		post.getDenominatorScaleZ() * pre.getZNominatorScaleZ() + 		post.getDenominatorTranslation() * pre.getDenominatorScaleZ();
		double denominatorTranslation = post.getDenominatorScaleX() * pre.getXNominatorTranslation() + 	post.getDenominatorScaleY() * pre.getYNominatorTranslation() + 	post.getDenominatorScaleZ() * pre.getZNominatorTranslation() + 	post.getDenominatorTranslation() * pre.getDenominatorTranslation();
		setDenominatorAffine(denominatorScaleByX, denominatorScaleByY, denominatorScaleByZ, denominatorTranslation);
		
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorCommonTranslation(double t) {
		setNominatorTranslations(t, t, t);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorLinearPart(
			double mxx, double mxy, double mxz,
			double myx, double myy, double myz,
			double mzx, double mzy, double mzz) {
		setXNominatorScaleX(mxx);
		setXNominatorScaleY(mxy);
		setXNominatorScaleZ(mxz);
		setYNominatorScaleX(myx);
		setYNominatorScaleY(myy);
		setYNominatorScaleZ(myz);
		setZNominatorScaleX(mzx);
		setZNominatorScaleY(mzy);
		setZNominatorScaleZ(mzz);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorShearXY(double mxy, double myx) {
		setXNominatorScaleY(mxy);
		setYNominatorScaleX(myx);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorShearYZ(double myz, double mzy) {
		setYNominatorScaleZ(myz);
		setZNominatorScaleY(mzy);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorShearZX(double mzx, double mxz) {
		setZNominatorScaleX(mzx);
		setXNominatorScaleZ(mxz);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorCommonShearXY(double shear) {
		setNominatorShearXY(shear, shear);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorCommonShearYZ(double shear) {
		setNominatorShearYZ(shear, shear);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorCommonShearZX(double shear) {
		setNominatorShearZX(shear, shear);
		return this;
	}
	
	default MutableProjectiveHolder3D setNominatorCommonScaling(double scale) {
		setNominatorScalings(scale, scale, scale);
		return this;
	}
	
	default MutableProjectiveHolder3D setToIdentity() {
		setNominatorCommonScaling(1);
		setNominatorCommonShearXY(0);
		setNominatorCommonShearYZ(0);
		setNominatorCommonShearZX(0);
		setNominatorCommonTranslation(0);
		setDenominatorAffine(0, 0, 0, 1);
		return this;
	}
	
	default <P> P build(CreatorFromProjectiveHolder3D<? extends P> creator) {
		return creator.get(this);
	}
	
	default <P> P build(CreatorFromMatrixValues<? extends P> creator) {
		return creator.get(
				getXNominatorScaleX(), getXNominatorScaleY(), getXNominatorScaleZ(), getXNominatorTranslation(),
				getYNominatorScaleX(), getYNominatorScaleY(), getYNominatorScaleZ(), getYNominatorTranslation(),
				getZNominatorScaleX(), getZNominatorScaleY(), getZNominatorScaleZ(), getZNominatorTranslation(),
				getDenominatorScaleX(), getDenominatorScaleY(), getDenominatorScaleZ(), getDenominatorTranslation());
	}
	
	
	public static interface CreatorFromProjectiveHolder3D <P> {
		P get(ProjectiveHolder3D source);
	}
	
	public static interface CreatorFromMatrixValues <P> {
		P get(
				double nominatorMxx, double nominatorMxy, double nominatorMxz, double nominatorTx,
				double nominatorMyx, double nominatorMyy, double nominatorMyz, double nominatorTy,
				double nominatorMzx, double nominatorMzy, double nominatorMzz, double nominatorTz,
				double denominatorSx, double denominatorSy, double denominatorSz, double denominatorT);
	}
}

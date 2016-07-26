package de.lennartmeinhardt.math.dim3.transform.affine;

import de.lennartmeinhardt.math.dim3.point.Point3D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

public interface MutableAffineHolder3D extends AffineHolder3D {

	MutableAffineHolder3D setMxx(double mxx);
	MutableAffineHolder3D setMxy(double mxy);
	MutableAffineHolder3D setMxz(double mxz);
	MutableAffineHolder3D setTx(double tx);
	
	MutableAffineHolder3D setMyx(double myx);
	MutableAffineHolder3D setMyy(double myy);
	MutableAffineHolder3D setMyz(double myz);
	MutableAffineHolder3D setTy(double ty);
	
	MutableAffineHolder3D setMzx(double mzx);
	MutableAffineHolder3D setMzy(double mzy);
	MutableAffineHolder3D setMzz(double mzz);
	MutableAffineHolder3D setTz(double tz);
	
	default MutableAffineHolder3D setTranslations(double tx, double ty, double tz) {
		setTx(tx);
		setTy(ty);
		setTz(tz);
		return this;
	}
	
	default MutableAffineHolder3D setTranslation(Point3D point) {
		setTranslations(point.getX(), point.getY(), point.getZ());
		return this;
	}
	
	default MutableAffineHolder3D setScalings(double mxx, double myy, double mzz) {
		setMxx(mxx);
		setMyy(myy);
		setMzz(mzz);
		return this;
	}
	
	default MutableAffineHolder3D setTransformX(double mxx, double mxy, double mxz, double tx) {
		setMxx(mxx);
		setMxy(mxy);
		setMxz(mxz);
		setTx(tx);
		return this;
	}
	
	default MutableAffineHolder3D setTransformY(double myx, double myy, double myz, double ty) {
		setMyx(myx);
		setMyy(myy);
		setMyz(myz);
		setTy(ty);
		return this;
	}
	
	default MutableAffineHolder3D setTransformZ(double mzx, double mzy, double mzz, double tz) {
		setMzx(mzx);
		setMzy(mzy);
		setMzz(mzz);
		setTz(tz);
		return this;
	}
	
	default MutableAffineHolder3D setMatrix(
			double mxx, double mxy, double mxz, double tx,
			double myx, double myy, double myz, double ty,
			double mzx, double mzy, double mzz, double tz) {
		setTransformX(mxx, mxy, mxz, tx);
		setTransformY(myx, myy, myz, ty);
		setTransformZ(mzx, mzy, mzz, tz);
		return this;
	}
	
	default MutableAffineHolder3D initFromVertexHolder(VertexScalingHolder<? extends Point3D> vertexScaleHolder) {
		double scale = vertexScaleHolder.getScaling();
		Point3D point = vertexScaleHolder.getVertex();

		setCommonShearXY(0);
		setCommonShearYZ(0);
		setCommonShearXZ(0);
		setCommonScaling(scale);
		setTranslation(point);
		return this;
	}
	
	default MutableAffineHolder3D initFromAffineHolder3D(AffineHolder3D affineHolder) {
		setMxx(affineHolder.getMxx());
		setMxy(affineHolder.getMxy());
		setMxz(affineHolder.getMxz());
		setTx(affineHolder.getTx());
		setMyx(affineHolder.getMyx());
		setMyy(affineHolder.getMyy());
		setMyz(affineHolder.getMyz());
		setTy(affineHolder.getTy());
		setMzx(affineHolder.getMzx());
		setMzy(affineHolder.getMzy());
		setMzz(affineHolder.getMzz());
		setTz(affineHolder.getTz());
		return this;
	}
	
	default MutableAffineHolder3D postConcat(AffineHolder3D that) {
		double mxx = that.getMxx() * this.getMxx() + that.getMxy() * this.getMyx() + that.getMxz() * this.getMzx();
		double mxy = that.getMxx() * this.getMxy() + that.getMxy() * this.getMyy() + that.getMxz() * this.getMzy();
		double mxz = that.getMxx() * this.getMxz() + that.getMxy() * this.getMyz() + that.getMxz() * this.getMzz();

		double myx = that.getMyx() * this.getMxx() + that.getMyy() * this.getMyx() + that.getMyz() * this.getMzx();
		double myy = that.getMyx() * this.getMxy() + that.getMyy() * this.getMyy() + that.getMyz() * this.getMzy();
		double myz = that.getMyx() * this.getMxz() + that.getMyy() * this.getMyz() + that.getMyz() * this.getMzz();

		double mzx = that.getMzx() * this.getMxx() + that.getMzy() * this.getMyx() + that.getMzz() * this.getMzx();
		double mzy = that.getMzx() * this.getMxy() + that.getMzy() * this.getMyy() + that.getMzz() * this.getMzy();
		double mzz = that.getMzx() * this.getMxz() + that.getMzy() * this.getMyz() + that.getMzz() * this.getMzz();
		
		double tx = that.getMxx() * this.getTx() + that.getMxy() * this.getTy() + that.getMxz() * this.getTz() + that.getTx();
		double ty = that.getMyx() * this.getTx() + that.getMyy() * this.getTy() + that.getMyz() * this.getTz() + that.getTy();
		double tz = that.getMyx() * this.getTx() + that.getMyy() * this.getTy() + that.getMzz() * this.getTz() + that.getTy();
		
		setMatrix(mxx, mxy, mxz, tx, myx, myy, myz, ty, mzx, mzy, mzz, tz);
		return this;
	}
	
	default MutableAffineHolder3D setCommonTranslation(double t) {
		setTranslations(t, t, t);
		return this;
	}
	
	default MutableAffineHolder3D setLinearPart(
			double mxx, double mxy, double mxz,
			double myx, double myy, double myz,
			double mzx, double mzy, double mzz) {
		setMxx(mxx);
		setMxy(mxy);
		setMxz(mxz);
		setMyx(myx);
		setMyy(myy);
		setMyz(myz);
		setMzx(mzx);
		setMzy(mzy);
		setMzz(mzz);
		return this;
	}
	
	default MutableAffineHolder3D setShearXY(double mxy, double myx) {
		setMxy(mxy);
		setMyx(myx);
		return this;
	}
	
	default MutableAffineHolder3D setShearYZ(double myz, double mzy) {
		setMyz(myz);
		setMzy(mzy);
		return this;
	}
	
	default MutableAffineHolder3D setShearXZ(double mxz, double mzx) {
		setMxz(mxz);
		setMzx(mzx);
		return this;
	}
	
	default MutableAffineHolder3D setCommonShearXY(double shear) {
		setShearXY(shear, shear);
		return this;
	}
	
	default MutableAffineHolder3D setCommonShearYZ(double shear) {
		setShearYZ(shear, shear);
		return this;
	}
	
	default MutableAffineHolder3D setCommonShearXZ(double shear) {
		setShearXZ(shear, shear);
		return this;
	}
	
	default MutableAffineHolder3D setCommonScaling(double scale) {
		setScalings(scale, scale, scale);
		return this;
	}
	
	default MutableAffineHolder3D setToIdentity() {
		setCommonScaling(1);
		setCommonShearXY(0);
		setCommonShearXZ(0);
		setCommonShearYZ(0);
		setCommonTranslation(0);
		return this;
	}
	
	default <A> A buildFromAffineHolder3D(CreatorFromAffineHolder3D<? extends A> creator) {
		return creator.get(this);
	}
	
	default <A> A buildFromMarixValues(CreatorFromMatrixValues<? extends A> creator) {
		return creator.get(
				getMxx(), getMxy(), getMxz(), getTx(),
				getMyx(), getMyy(), getMyz(), getTy(),
				getMzx(), getMzy(), getMzz(), getTz());
	}
	
	
	
	public static interface CreatorFromAffineHolder3D <A> {
		A get(AffineHolder3D source);
	}
	
	public static interface CreatorFromMatrixValues <A> {
		A get(
				double mxx, double mxy, double mxz, double tx,
				double myx, double myy, double myz, double ty,
				double mzx, double mzy, double mzz, double tz);
	}
}

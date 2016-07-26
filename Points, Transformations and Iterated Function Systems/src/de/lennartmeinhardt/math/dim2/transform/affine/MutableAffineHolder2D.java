package de.lennartmeinhardt.math.dim2.transform.affine;

import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.transform.VertexScalingHolder;

/**
 * A mutable {@link AffineHolder2D} to use with fluent interface.
 * 
 * @author Lennart Meinhardt
 */
public interface MutableAffineHolder2D extends AffineHolder2D {

	MutableAffineHolder2D setMxx(double mxx);
	MutableAffineHolder2D setMxy(double mxy);
	MutableAffineHolder2D setTx(double tx);
	
	MutableAffineHolder2D setMyx(double myx);
	MutableAffineHolder2D setMyy(double myy);
	MutableAffineHolder2D setTy(double ty);
	
	default MutableAffineHolder2D setTranslations(double tx, double ty) {
		setTx(tx);
		setTy(ty);
		return this;
	}
	
	default MutableAffineHolder2D setScalings(double mxx, double myy) {
		setMxx(mxx);
		setMyy(myy);
		return this;
	}
	
	default MutableAffineHolder2D setTransformX(double mxx, double mxy, double tx) {
		setMxx(mxx);
		setMxy(mxy);
		setTx(tx);
		return this;
	}
	
	default MutableAffineHolder2D setTransformY(double myx, double myy, double ty) {
		setMyx(myx);
		setMyy(myy);
		setTy(ty);
		return this;
	}
	
	default MutableAffineHolder2D setMatrix(double mxx, double mxy, double tx, double myx, double myy, double ty) {
		setLinearPart(mxx, mxy, myx, myy);
		setTranslations(tx, ty);
		return this;
	}
	
	default MutableAffineHolder2D initFromVertexHolder(VertexScalingHolder<? extends Point2D> vertexScaleHolder) {
		double scale = vertexScaleHolder.getScaling();
		Point2D point = vertexScaleHolder.getVertex();
		
		setCommonShear(0);
		setCommonScaling(scale);
		setTranslation(point);
		return this;
	}
	
	default MutableAffineHolder2D initFromAffineHolder2D(AffineHolder2D affineHolder) {
		setMxx(affineHolder.getMxx());
		setMxy(affineHolder.getMxy());
		setTx(affineHolder.getTx());
		setMyx(affineHolder.getMyx());
		setMyy(affineHolder.getMyy());
		setTy(affineHolder.getTy());
		return this;
	}
	
	default MutableAffineHolder2D postConcat(AffineHolder2D that) {
		double mxx = that.getMxx() * this.getMxx() + that.getMxy() * this.getMyx();
		double mxy = that.getMxx() * this.getMxy() + that.getMxy() * this.getMyy();
		double myx = that.getMyx() * this.getMxx() + that.getMyy() * this.getMyx();
		double myy = that.getMyx() * this.getMxy() + that.getMyy() * this.getMyy();

		double tx = that.getMxx() * this.getTx() + that.getMxy() * this.getTy() + that.getTx();
		double ty = that.getMyx() * this.getTx() + that.getMyy() * this.getTy() + that.getTy();
		
		setMatrix(mxx, mxy, tx, myx, myy, ty);
		return this;
	}
	
	default MutableAffineHolder2D setCommonTranslation(double t) {
		setTranslations(t, t);
		return this;
	}
	
	default MutableAffineHolder2D setLinearPart(double mxx, double mxy, double myx, double myy) {
		setMxx(mxx);
		setMxy(mxy);
		setMyx(myx);
		setMyy(myy);
		return this;
	}
	
	default MutableAffineHolder2D setShear(double mxy, double myx) {
		setMxy(mxy);
		setMyx(myx);
		return this;
	}
	
	default MutableAffineHolder2D setCommonShear(double shear) {
		setShear(shear, shear);
		return this;
	}
	
	default MutableAffineHolder2D setCommonScaling(double scale) {
		setScalings(scale, scale);
		return this;
	}
	
	default MutableAffineHolder2D setToIdentity() {
		setCommonScaling(1);
		setCommonShear(0);
		setCommonTranslation(0);
		return this;
	}
	
	default MutableAffineHolder2D setTranslation(Point2D point) {
		setTranslations(point.getX(), point.getY());
		return this;
	}
	
	default <A> A buildFromAffineHolder2D(CreatorFromAffineHolder2D<? extends A> creator) {
		return creator.get(this);
	}
	
	default <A> A buildFromMarixValues(CreatorFromMatrixValues<? extends A> creator) {
		return creator.get(getMxx(), getMxy(), getTx(), getMyx(), getMyy(), getTy());
	}
	
	
	
	public static interface CreatorFromAffineHolder2D <A> {
		A get(AffineHolder2D source);
	}
	
	public static interface CreatorFromMatrixValues <A> {
		A get(double mxx, double mxy, double tx, double myx, double myy, double ty);
	}
}

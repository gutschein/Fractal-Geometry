package de.lennartmeinhardt.math.dim2.transform.affine;

public class AffineBuilder2D implements MutableAffineHolder2D {
	
	private double mxx;
	private double mxy;
	private double tx;
	private double myx;
	private double myy;
	private double ty;
	
	public AffineBuilder2D() {
		setToIdentity();
	}
	
	public AffineBuilder2D(AffineHolder2D holder) {
		initFromAffineHolder2D(holder);
	}
	
	public AffineBuilder2D(
			double mxx, double mxy, double tx,
			double myx, double myy, double ty) {
		setMatrix(mxx, mxy, tx, myx, myy, ty);
	}
	
	
	@Override public double getMxx() {
		return mxx;
	}
	
	@Override public double getMxy() {
		return mxy;
	}
	
	@Override public double getTx() {
		return tx;
	}
	
	@Override public double getMyx() {
		return myx;
	}
	
	@Override public double getMyy() {
		return myy;
	}
	
	@Override public double getTy() {
		return ty;
	}
	
	@Override public MutableAffineHolder2D setMxx(double mxx) {
		this.mxx = mxx;
		return this;
	}
	
	@Override public MutableAffineHolder2D setMxy(double mxy) {
		this.mxy = mxy;
		return this;
	}
	
	@Override public MutableAffineHolder2D setTx(double tx) {
		this.tx = tx;
		return this;
	}
	
	@Override public MutableAffineHolder2D setMyx(double myx) {
		this.myx = myx;
		return this;
	}
	
	@Override public MutableAffineHolder2D setMyy(double myy) {
		this.myy = myy;
		return this;
	}
	
	@Override public MutableAffineHolder2D setTy(double ty) {
		this.ty = ty;
		return this;
	}
}

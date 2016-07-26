package de.lennartmeinhardt.math.dim3.transform.affine;

public class AffineBuilder3D implements MutableAffineHolder3D {
	
	private double mxx;
	private double mxy;
	private double mxz;
	private double tx;
	
	private double myx;
	private double myy;
	private double myz;
	private double ty;
	
	private double mzx;
	private double mzy;
	private double mzz;
	private double tz;
	
	public AffineBuilder3D() {
		setToIdentity();
	}
	
	public AffineBuilder3D(AffineHolder3D holder) {
		initFromAffineHolder3D(holder);
	}
	
	public AffineBuilder3D(
			double mxx, double mxy, double mxz, double tx,
			double myx, double myy, double myz, double ty,
			double mzx, double mzy, double mzz, double tz) {
		setMatrix(mxx, mxy, mxz, tx, myx, myy, myz, ty, mzx, mzy, mzz, tz);
	}


	@Override public double getMxx() {
		return mxx;
	}
	
	@Override public double getMxy() {
		return mxy;
	}
	
	@Override public double getMxz() {
		return mxz;
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
	
	@Override public double getMyz() {
		return myz;
	}
	
	@Override public double getTy() {
		return ty;
	}
	
	@Override public double getMzx() {
		return mzx;
	}
	
	@Override public double getMzy() {
		return mzy;
	}
	
	@Override public double getMzz() {
		return mzz;
	}
	
	@Override public double getTz() {
		return tz;
	}
	
	@Override public MutableAffineHolder3D setMxx(double mxx) {
		this.mxx = mxx;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMxy(double mxy) {
		this.mxy = mxy;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMxz(double mxz) {
		this.mxz = mxz;
		return this;
	}
	
	@Override public MutableAffineHolder3D setTx(double tx) {
		this.tx = tx;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMyx(double myx) {
		this.myx = myx;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMyy(double myy) {
		this.myy = myy;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMyz(double myz) {
		this.myz = myz;
		return this;
	}
	
	@Override public MutableAffineHolder3D setTy(double ty) {
		this.ty = ty;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMzx(double mzx) {
		this.mzx = mzx;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMzy(double mzy) {
		this.mzy = mzy;
		return this;
	}
	
	@Override public MutableAffineHolder3D setMzz(double mzz) {
		this.mzz = mzz;
		return this;
	}
	
	@Override public MutableAffineHolder3D setTz(double tz) {
		this.tz = tz;
		return this;
	}
}

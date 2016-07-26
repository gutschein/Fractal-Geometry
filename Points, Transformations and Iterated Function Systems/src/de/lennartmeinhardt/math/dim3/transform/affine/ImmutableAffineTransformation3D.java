package de.lennartmeinhardt.math.dim3.transform.affine;

public final class ImmutableAffineTransformation3D implements AffineTransformation3D {
	
	private final double	mxx,	mxy,	mxz,	tx,
							myx,	myy,	myz,	ty,
							mzx,	mzy,	mzz,	tz;

	public ImmutableAffineTransformation3D(	double mxx, double mxy, double mxz, double tx,
										double myx, double myy, double myz, double ty,
										double mzx, double mzy, double mzz, double tz) {
		this.mxx = mxx;
		this.mxy = mxy;
		this.mxz = mxz;
		this.tx = tx;
		this.myx = myx;
		this.myy = myy;
		this.myz = myz;
		this.ty = ty;
		this.mzx = mzx;
		this.mzy = mzy;
		this.mzz = mzz;
		this.tz = tz;
	}
	
	public ImmutableAffineTransformation3D(AffineHolder3D holder) {
		this(
				holder.getMxx(), holder.getMxy(), holder.getMxz(), holder.getTx(),
				holder.getMyx(), holder.getMyy(), holder.getMyz(), holder.getTy(),
				holder.getMzx(), holder.getMzy(), holder.getMzz(), holder.getTz());
	}
	
	@Override public final double getMxx() {
		return mxx;
	}
	
	@Override public final double getMyy() {
		return myy;
	}
	
	@Override public final double getMzz() {
		return mzz;
	}
	
	@Override public final double getMxy() {
		return mxy;
	}
	
	@Override public final double getMxz() {
		return mxz;
	}
	
	@Override public final double getMyx() {
		return myx;
	}
	
	@Override public final double getMyz() {
		return myz;
	}
	
	@Override public final double getMzx() {
		return mzx;
	}
	
	@Override public final double getMzy() {
		return mzy;
	}
	
	@Override public final double getTx() {
		return tx;
	}
	
	@Override public final double getTy() {
		return ty;
	}
	
	@Override public final double getTz() {
		return tz;
	}
}

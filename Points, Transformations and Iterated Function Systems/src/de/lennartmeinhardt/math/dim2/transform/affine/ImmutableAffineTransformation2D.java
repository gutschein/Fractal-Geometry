package de.lennartmeinhardt.math.dim2.transform.affine;

public final class ImmutableAffineTransformation2D implements AffineTransformation2D {
	
	private final double	mxx,	mxy,	tx,
							myx,	myy,	ty;

	public ImmutableAffineTransformation2D(
			final double mxx, final double mxy, final double tx,
			final double myx, final double myy, final double ty) {
		this.mxx = mxx;
		this.mxy = mxy;
		this.tx = tx;
		this.myx = myx;
		this.myy = myy;
		this.ty = ty;
	}
	
	public ImmutableAffineTransformation2D(AffineHolder2D holder) {
		this(
				holder.getMxx(), holder.getMxy(), holder.getTx(),
				holder.getMyx(), holder.getMyy(), holder.getTy());
	}
	
	public ImmutableAffineTransformation2D(double[] m) {
		this(m[0], m[1], m[2], m[3], m[4], m[5]);
	}
	
	@Override public double getMxx() {
		return mxx;
	}
	
	@Override public double getMyy() {
		return myy;
	}
	
	@Override public double getMxy() {
		return mxy;
	}
	
	@Override public double getMyx() {
		return myx;
	}
	
	@Override public double getTx() {
		return tx;
	}
	
	@Override public double getTy() {
		return ty;
	}
}

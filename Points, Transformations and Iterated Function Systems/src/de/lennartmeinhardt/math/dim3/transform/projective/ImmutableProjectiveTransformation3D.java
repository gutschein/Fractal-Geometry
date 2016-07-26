package de.lennartmeinhardt.math.dim3.transform.projective;

public final class ImmutableProjectiveTransformation3D implements ProjectiveTransformation3D {
	
	private final double	nominatorMxx, nominatorMxy, nominatorMxz, nominatorTx,
							nominatorMyx, nominatorMyy, nominatorMyz, nominatorTy,
							nominatorMzx, nominatorMzy, nominatorMzz, nominatorTz,
							denominatorScaleByX, denominatorScaleByY, denominatorScaleByZ, denominatorTranslation;
	
	public ImmutableProjectiveTransformation3D(double nominatorMxx, double nominatorMxy, double nominatorMxz,
			double nominatorTx, double nominatorMyx, double nominatorMyy, double nominatorMyz,
			double nominatorTy, double nominatorMzx, double nominatorMzy, double nominatorMzz,
			double nominatorTz, double denominatorScaleByX, double denominatorScaleByY,
			double denominatorScaleByZ, double denominatorTranslation) {
		this.nominatorMxx = nominatorMxx;
		this.nominatorMxy = nominatorMxy;
		this.nominatorMxz = nominatorMxz;
		this.nominatorTx = nominatorTx;
		this.nominatorMyx = nominatorMyx;
		this.nominatorMyy = nominatorMyy;
		this.nominatorMyz = nominatorMyz;
		this.nominatorTy = nominatorTy;
		this.nominatorMzx = nominatorMzx;
		this.nominatorMzy = nominatorMzy;
		this.nominatorMzz = nominatorMzz;
		this.nominatorTz = nominatorTz;
		this.denominatorScaleByX = denominatorScaleByX;
		this.denominatorScaleByY = denominatorScaleByY;
		this.denominatorScaleByZ = denominatorScaleByZ;
		this.denominatorTranslation = denominatorTranslation;
	}

	@Override public double getXNominatorScaleX() {
		return nominatorMxx;
	}

	@Override public double getXNominatorScaleY() {
		return nominatorMxy;
	}
	
	@Override public double getXNominatorScaleZ() {
		return nominatorMxz;
	}
	
	@Override public double getXNominatorTranslation() {
		return nominatorTx;
	}
	
	@Override public double getYNominatorScaleX() {
		return nominatorMyx;
	}
	
	@Override public double getYNominatorScaleY() {
		return nominatorMyy;
	}
	
	@Override public double getYNominatorScaleZ() {
		return nominatorMyz;
	}
	
	@Override public double getYNominatorTranslation() {
		return nominatorTy;
	}
	
	@Override public double getZNominatorScaleX() {
		return nominatorMzx;
	}
	
	@Override public double getZNominatorScaleY() {
		return nominatorMzy;
	}
	
	@Override public double getZNominatorScaleZ() {
		return nominatorMzz;
	}
	
	@Override public double getZNominatorTranslation() {
		return nominatorTz;
	}
	
	@Override public double getDenominatorScaleX() {
		return denominatorScaleByX;
	}
	
	@Override public double getDenominatorScaleY() {
		return denominatorScaleByY;
	}
	
	@Override public double getDenominatorScaleZ() {
		return denominatorScaleByZ;
	}
	
	@Override public double getDenominatorTranslation() {
		return denominatorTranslation;
	}
}

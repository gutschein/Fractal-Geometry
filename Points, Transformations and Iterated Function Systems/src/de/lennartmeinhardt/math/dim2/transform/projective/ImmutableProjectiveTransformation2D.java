package de.lennartmeinhardt.math.dim2.transform.projective;

public final class ImmutableProjectiveTransformation2D implements ProjectiveTransformation2D {

	private final double	nominatorMxx, nominatorMxy, nominatorTx,
							nominatorMyx, nominatorMyy, nominatorTy,
							denominatorScaleByX, denominatorScaleByY, denominatorTranslation;
	
	public ImmutableProjectiveTransformation2D(double nominatorMxx, double nominatorMxy, double nominatorTx,
			double nominatorMyx, double nominatorMyy, double nominatorTy, double denominatorScaleByX,
			double denominatorScaleByY, double denominatorTranslation) {
		this.nominatorMxx = nominatorMxx;
		this.nominatorMxy = nominatorMxy;
		this.nominatorTx = nominatorTx;
		this.nominatorMyx = nominatorMyx;
		this.nominatorMyy = nominatorMyy;
		this.nominatorTy = nominatorTy;
		this.denominatorScaleByX = denominatorScaleByX;
		this.denominatorScaleByY = denominatorScaleByY;
		this.denominatorTranslation = denominatorTranslation;
	}

	@Override public double getXNominatorScaleX() {
		return nominatorMxx;
	}

	@Override public double getXNominatorScaleY() {
		return nominatorMxy;
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

	@Override public double getYNominatorTranslation() {
		return nominatorTy;
	}

	@Override public double getDenominatorScaleX() {
		return denominatorScaleByX;
	}

	@Override public double getDenominatorScaleY() {
		return denominatorScaleByY;
	}

	@Override public double getDenominatorTranslation() {
		return denominatorTranslation;
	}
}

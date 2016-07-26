package de.lennartmeinhardt.math.dim2.transform.projective;

public class ProjectiveBuilder2D implements MutableProjectiveHolder2D {
	
	private double nominatorMxx;
	private double nominatorMxy;
	private double nominatorTx;
	private double nominatorMyx;
	private double nominatorMyy;
	private double nominatorTy;
	private double denominatorScaleByX;
	private double denominatorScaleByY;
	private double denominatorTranslation;
	
	
	public ProjectiveBuilder2D() {
		setToIdentity();
	}
	
	public ProjectiveBuilder2D(ProjectiveHolder2D holder) {
		initFromProjectiveHolder2D(holder);
	}
	
	public ProjectiveBuilder2D(
			double nominatorMxx, double nominatorMxy, double nominatorTx,
			double nominatorMyx, double nominatorMyy, double nominatorTy,
			double denominatorScaleByX, double denominatorScaleByY, double denominatorTranslation) {
		
		setMatrix(
				nominatorMxx, nominatorMxy, nominatorTx,
				nominatorMyx, nominatorMyy, nominatorTy,
				denominatorScaleByX, denominatorScaleByY, denominatorTranslation);
	}
	
	@Override public MutableProjectiveHolder2D setXNominatorScaleX(double nominatorMxx) {
		this.nominatorMxx = nominatorMxx;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setXNominatorScaleY(double nominatorMxy) {
		this.nominatorMxy = nominatorMxy;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setXNominatorTranslation(double nominatorTx) {
		this.nominatorTx = nominatorTx;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setYNominatorScaleX(double nominatorMyx) {
		this.nominatorMyx = nominatorMyx;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setYNominatorScaleY(double nominatorMyy) {
		this.nominatorMyy = nominatorMyy;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setYNominatorTranslation(double nominatorTy) {
		this.nominatorTy = nominatorTy;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setDenominatorScaleX(double denominatorScaleByX) {
		this.denominatorScaleByX = denominatorScaleByX;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setDenominatorScaleY(double denominatorScaleByY) {
		this.denominatorScaleByY = denominatorScaleByY;
		return this;
	}
	
	@Override public MutableProjectiveHolder2D setDenominatorTranslation(double denominatorTranslation) {
		this.denominatorTranslation = denominatorTranslation;
		return this;
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

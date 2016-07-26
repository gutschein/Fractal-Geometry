package de.lennartmeinhardt.math.dim3.transform.projective;

public class ProjectiveBuilder3D implements MutableProjectiveHolder3D {
	
	private double nominatorMxx;
	private double nominatorMxy;
	private double nominatorMxz;
	private double nominatorTx;
	private double nominatorMyx;
	private double nominatorMyy;
	private double nominatorMyz;
	private double nominatorTy;
	private double nominatorMzx;
	private double nominatorMzy;
	private double nominatorMzz;
	private double nominatorTz;
	private double denominatorScaleByX;
	private double denominatorScaleByY;
	private double denominatorScaleByZ;
	private double denominatorTranslation;
	
	
	public ProjectiveBuilder3D() {
		setToIdentity();
	}
	
	public ProjectiveBuilder3D(ProjectiveHolder3D holder) {
		initFromProjectiveHolder3D(holder);
	}
	
	public ProjectiveBuilder3D(
			double nominatorMxx, double nominatorMxy, double nominatorMxz, double nominatorTx,
			double nominatorMyx, double nominatorMyy, double nominatorMyz, double nominatorTy,
			double nominatorMzx, double nominatorMzy, double nominatorMzz, double nominatorTz,
			double denominatorScaleByX, double denominatorScaleByY, double denominatorScaleByZ, double denominatorTranslation) {
		
		setMatrix(
				nominatorMxx, nominatorMxy, nominatorMxz, nominatorTx,
				nominatorMyx, nominatorMyy, nominatorMyz, nominatorTy,
				nominatorMzx, nominatorMzy, nominatorMzz, nominatorTz,
				denominatorScaleByX, denominatorScaleByY, denominatorScaleByZ, denominatorTranslation);
	}

	
	@Override public MutableProjectiveHolder3D setXNominatorScaleX(double nominatorMxx) {
		this.nominatorMxx = nominatorMxx;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setXNominatorScaleY(double nominatorMxy) {
		this.nominatorMxy = nominatorMxy;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setXNominatorScaleZ(double nominatorMxz) {
		this.nominatorMxz = nominatorMxz;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setXNominatorTranslation(double nominatorTx) {
		this.nominatorTx = nominatorTx;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setYNominatorScaleX(double nominatorMyx) {
		this.nominatorMyx = nominatorMyx;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setYNominatorScaleY(double nominatorMyy) {
		this.nominatorMyy = nominatorMyy;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setYNominatorScaleZ(double nominatorMyz) {
		this.nominatorMyz = nominatorMyz;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setYNominatorTranslation(double nominatorTy) {
		this.nominatorTy = nominatorTy;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setZNominatorScaleX(double nominatorMzx) {
		this.nominatorMzx = nominatorMzx;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setZNominatorScaleY(double nominatorMzy) {
		this.nominatorMzy = nominatorMzy;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setZNominatorScaleZ(double nominatorMzz) {
		this.nominatorMzz = nominatorMzz;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setZNominatorTranslation(double nominatorTz) {
		this.nominatorTz = nominatorTz;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setDenominatorScaleX(double denominatorScaleByX) {
		this.denominatorScaleByX = denominatorScaleByX;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setDenominatorScaleY(double denominatorScaleByY) {
		this.denominatorScaleByY = denominatorScaleByY;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setDenominatorScaleZ(double denominatorScaleByZ) {
		this.denominatorScaleByZ = denominatorScaleByZ;
		return this;
	}
	
	@Override public MutableProjectiveHolder3D setDenominatorTranslation(double denominatorTranslation) {
		this.denominatorTranslation = denominatorTranslation;
		return this;
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

package de.lennartmeinhardt.math.dim3.transform.affine;

public interface AffineHolder3D {
	
	double getMxx();
	double getMyy();
	double getMzz();
	double getMxy();
	
	double getMxz();
	double getMyx();
	double getMyz();
	double getMzx();
	
	double getMzy();
	double getTx();
	double getTy();
	double getTz();
}

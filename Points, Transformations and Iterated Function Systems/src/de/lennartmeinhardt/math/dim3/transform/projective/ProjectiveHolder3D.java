package de.lennartmeinhardt.math.dim3.transform.projective;

public interface ProjectiveHolder3D {
	
	double getXNominatorScaleX();
	double getXNominatorScaleY();
	double getXNominatorScaleZ();
	double getXNominatorTranslation();
	
	double getYNominatorScaleX();
	double getYNominatorScaleY();
	double getYNominatorScaleZ();
	double getYNominatorTranslation();
	
	double getZNominatorScaleX();
	double getZNominatorScaleY();
	double getZNominatorScaleZ();
	double getZNominatorTranslation();

	double getDenominatorScaleX();
	double getDenominatorScaleY();
	double getDenominatorScaleZ();
	double getDenominatorTranslation();
}

package de.lennartmeinhardt.math.dim2.transform.projective;

public interface ProjectiveHolder2D {
	
	double getXNominatorScaleX();
	double getXNominatorScaleY();
	double getXNominatorTranslation();
	
	double getYNominatorScaleX();
	double getYNominatorScaleY();
	double getYNominatorTranslation();

	double getDenominatorScaleX();
	double getDenominatorScaleY();
	double getDenominatorTranslation();
}

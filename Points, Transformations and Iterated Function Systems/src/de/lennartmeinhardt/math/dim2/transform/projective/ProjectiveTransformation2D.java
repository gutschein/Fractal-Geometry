package de.lennartmeinhardt.math.dim2.transform.projective;

import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;

public interface ProjectiveTransformation2D
extends PointTransformation2D, ProjectiveHolder2D {

	default double transformNominatorX(double x, double y) {
		return getXNominatorScaleX() * x + getXNominatorScaleY() * y + getXNominatorTranslation();
	}
	
	default double transformNominatorY(double x, double y) {
		return getYNominatorScaleX() * x + getYNominatorScaleY() * y + getYNominatorTranslation();
	}
	
	default double transformDenominator(double x, double y) {
		return getDenominatorScaleX() * x + getDenominatorScaleY() * y + getDenominatorTranslation();
	}
	
	@Override default double transformX(double x, double y) {
		return transformNominatorX(x, y) / transformDenominator(x, y);
	}
	
	@Override default double transformY(double x, double y) {
		return transformNominatorY(x, y) / transformDenominator(x, y);
	}
}

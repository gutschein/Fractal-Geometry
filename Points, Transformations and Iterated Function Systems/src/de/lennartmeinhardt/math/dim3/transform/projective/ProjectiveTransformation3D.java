package de.lennartmeinhardt.math.dim3.transform.projective;

import de.lennartmeinhardt.math.dim3.transform.PointTransformation3D;

public interface ProjectiveTransformation3D
extends PointTransformation3D, ProjectiveHolder3D {

	default double transformNominatorX(double x, double y, double z) {
		return getXNominatorScaleX() * x + getXNominatorScaleY() * y + getXNominatorScaleZ() * z + getXNominatorTranslation();
	}
	
	default double transformNominatorY(double x, double y, double z) {
		return getYNominatorScaleX() * x + getYNominatorScaleY() * y + getYNominatorScaleZ() * z + getYNominatorTranslation();
	}
	
	default double transformNominatorZ(double x, double y, double z) {
		return getZNominatorScaleX() * x + getZNominatorScaleY() * y + getZNominatorScaleZ() * z + getZNominatorTranslation();
	}
	
	default double transformDenominator(double x, double y, double z) {
		return getDenominatorScaleX() * x + getDenominatorScaleY() * y + getDenominatorScaleZ() * z + getDenominatorTranslation();
	}
	
	@Override default double transformX(double x, double y, double z) {
		return transformNominatorX(x, y, z) / transformDenominator(x, y, z);
	}
	
	@Override default double transformY(double x, double y, double z) {
		return transformNominatorY(x, y, z) / transformDenominator(x, y, z);
	}
	
	@Override default double transformZ(double x, double y, double z) {
		return transformNominatorZ(x, y, z) / transformDenominator(x, y, z);
	}
}

package de.lennartmeinhardt.math.dim2.transform.affine;

import de.lennartmeinhardt.math.dim2.transform.projective.ProjectiveTransformation2D;

public interface AdapterProjectiveToAffineTransformation2D extends ProjectiveTransformation2D, AffineHolder2D {

	@Override default double getXNominatorScaleX() {
		return getMxx();
	}

	@Override default double getXNominatorScaleY() {
		return getMxy();
	}

	@Override default double getXNominatorTranslation() {
		return getTx();
	}

	@Override default double getYNominatorScaleX() {
		return getMyx();
	}

	@Override default double getYNominatorScaleY() {
		return getMyy();
	}

	@Override default double getYNominatorTranslation() {
		return getTy();
	}

	@Override default double getDenominatorScaleX() {
		return 0;
	}
	
	@Override default double getDenominatorScaleY() {
		return 0;
	}
	
	@Override default double getDenominatorTranslation() {
		return 1;
	}
}

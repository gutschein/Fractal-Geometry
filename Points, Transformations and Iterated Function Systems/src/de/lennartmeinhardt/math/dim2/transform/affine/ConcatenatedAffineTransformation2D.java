package de.lennartmeinhardt.math.dim2.transform.affine;

class ConcatenatedAffineTransformation2D implements AffineTransformation2D {

	private final AffineTransformation2D outer;
	private final AffineTransformation2D inner;
	
	public ConcatenatedAffineTransformation2D(AffineTransformation2D outer, AffineTransformation2D inner) {
		this.outer = outer;
		this.inner = inner;
	}

	@Override
	public double getMxx() {
		return outer.getMxx() * inner.getMxx()
				+ outer.getMxy() * inner.getMyx();
	}

	@Override
	public double getMxy() {
		return outer.getMxx() * inner.getMxy()
				+ outer.getMxy() * inner.getMyy();
	}

	@Override
	public double getMyx() {
		return outer.getMyx() * inner.getMxx()
				+ outer.getMyy() * inner.getMyx();
	}

	@Override
	public double getMyy() {
		return outer.getMyx() * inner.getMxy()
				+ outer.getMyy() * inner.getMyy();
	}

	@Override
	public double getTx() {
		return outer.getMxx() * inner.getTx()
				+ outer.getMxy() * inner.getTy()
				+ outer.getTx();
	}

	@Override
	public double getTy() {
		return outer.getMyx() * inner.getTx()
				+ outer.getMyy() * inner.getTy()
				+ outer.getTy();
	}
	
}

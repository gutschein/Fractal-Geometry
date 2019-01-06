package de.lennartmeinhardt.math.dim2.transform;

class ConcatenatedPointTransformation2D implements PointTransformation2D {

	private final PointTransformation2D inner;
	private final PointTransformation2D outer;
	
	public ConcatenatedPointTransformation2D(PointTransformation2D outer, PointTransformation2D inner) {
		this.inner = inner;
		this.outer = outer;
	}
	
	@Override
	public double transformX(double x, double y) {
		double innerTransformedX = inner.transformX(x, y);
		double innerTransformedY = inner.transformY(x, y);
		return outer.transformX(innerTransformedX, innerTransformedY);
	}
	
	@Override
	public double transformY(double x, double y) {
		double innerTransformedX = inner.transformX(x, y);
		double innerTransformedY = inner.transformY(x, y);
		return outer.transformY(innerTransformedX, innerTransformedY);
	}
	
}

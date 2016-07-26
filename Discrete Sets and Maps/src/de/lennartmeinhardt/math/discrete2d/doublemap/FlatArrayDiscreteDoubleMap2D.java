package de.lennartmeinhardt.math.discrete2d.doublemap;

import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A basic mutable and operable discrete double map. Values are stored row-wise in a single double array.
 * 
 * @author Lennart Meinhardt
 */
public class FlatArrayDiscreteDoubleMap2D implements MutableDiscreteDoubleMap2D, OperableDiscreteDoubleMap2D<DiscreteRectangle> {

	// the values stored row-wise
	private final double[] valuesRowWise;

	// the coordinates of the bounds' bottom left anchor
	private final int left, bottom;
	// the bounds' dimensions
	private final int width, height;
	private final DiscreteRectangle range = new Range();
	

	/**
	 * Create a new {@link FlatArrayDiscreteDoubleMap2D} with given size, having the origin as bottom left anchor.
	 * 
	 * @param size the set's size
	 */
	public FlatArrayDiscreteDoubleMap2D(int size) {
		this(size, size);
	}

	/**
	 * Create a new {@link FlatArrayDiscreteDoubleMap2D} with given dimensions, having the origin as bottom left anchor.
	 * 
	 * @param width the bounds' width
	 * @param height the bounds' height
	 */
	public FlatArrayDiscreteDoubleMap2D(int width, int height) {
		this(0, 0, width, height);
	}

	/**
	 * Create a new {@link FlatArrayDiscreteDoubleMap2D} with given bounds.
	 * 
	 * @param left the left anchor
	 * @param bottom the bottom anchor
	 * @param width the bounds' width
	 * @param height the bounds' height
	 */
	public FlatArrayDiscreteDoubleMap2D(int left, int bottom, int width, int height) {
		this.width = width;
		this.height = height;
		this.left = left;
		this.bottom = bottom;
		valuesRowWise = createArray(width, height);
	}

	/**
	 * Create a new {@link FlatArrayDiscreteDoubleMap2D} with given bounds.
	 * 
	 * @param bounds the bounds to use
	 */
	public FlatArrayDiscreteDoubleMap2D(DiscreteRectangle range) {
		this(range.getLeft(), range.getBottom(), range.getWidth(), range.getHeight());
	}
	
	
	@Override public double getValueAt(int x, int y) {
		if(range.containsPointAt(x, y))
			return getValueAtUnchecked(x, y);
		else
			return 0;
	}

	// get the value without checking if the point is within the bounds
	private double getValueAtUnchecked(int x, int y) {
		int index = getIndexUnchecked(x, y);
		return valuesRowWise[index];
	}
	
	@Override public DiscreteRectangle getRange() {
		return range;
	}
	
	@Override public boolean setToConstantValue(double value) {
		for(int i = 0; i < valuesRowWise.length; i++)
			valuesRowWise[i] = value;
		return true;
	}

	@Override public boolean setValueAt(int x, int y, double value) {
		if(range.containsPointAt(x, y)) {
			int index = getIndexUnchecked(x, y);
			valuesRowWise[index] = value;
			return true;
		} else
			return false;
	}

	/**
	 * Get this map's raw data, i.e. the row-wise values array.
	 * 
	 * @return the values array
	 */
	public double[] getRawData() {
		return valuesRowWise;
	}

	// get the value-array index for given point
	private int getIndexUnchecked(int x, int y) {
		return (x - left) + (y - bottom) * width;
	}

	// create the values array
	private static double[] createArray(int width, int height) {
		int arraySize = arraySize(width, height);
		return new double[arraySize];
	}

	// get the value array size necessary for storing all values
	private static int arraySize(int width, int height) {
		return width * height;
	}
	
	@Override public FlatArrayDiscreteDoubleMap2D clone() {
		FlatArrayDiscreteDoubleMap2D copy = new FlatArrayDiscreteDoubleMap2D(getRange());
		double[] raw = valuesRowWise;
		double[] copyRaw = copy.valuesRowWise;
		System.arraycopy(raw, 0, copyRaw, 0, raw.length);
		return copy;
	}
	
	
	private class Range implements DiscreteRectangle {
		@Override public int getLeft() { return left; }
		@Override public int getBottom() { return bottom; }
		@Override public int getRight() { return left + width - 1; }
		@Override public int getTop() { return bottom + height - 1; }
	}
}

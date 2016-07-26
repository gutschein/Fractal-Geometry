package de.lennartmeinhardt.math.discrete2d.set.shapes;

/**
 * A simple immutable {@link DiscreteRectangle} implementation.
 * 
 * @author Lennart Meinhardt
 */
public class BaseRectangle implements DiscreteRectangle {

	// the bounds
	private final int left;
	private final int bottom;
	private final int right;
	private final int top;
	
	
	/**
	 * Create a new {@link BaseRectangle} with given bounds.
	 * 
	 * @param left the left bounds
	 * @param bottom the bottom bounds
	 * @param right the right bounds
	 * @param top the top bounds
	 */
	public BaseRectangle(int left, int bottom, int right, int top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}
	
	/**
	 * Create a new {@link BaseRectangle} with given bounds.
	 * 
	 * @param rectangle the bounds
	 */
	public BaseRectangle(DiscreteRectangle rectangle) {
		this(rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop());
	}

	
	@Override public int getLeft() {
		return left;
	}

	@Override public int getBottom() {
		return bottom;
	}

	@Override public int getRight() {
		return right;
	}

	@Override public int getTop() {
		return top;
	}
}

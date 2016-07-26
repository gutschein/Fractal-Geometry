package de.lennartmeinhardt.math.discrete2d.set.shapes;

/**
 * A rectangle with same width and height.
 * 
 * @author Lennart Meinhardt
 */
public class BaseSquare extends BaseRectangle {
	
	/**
	 * Create a new {@link BaseSquare} with given size.
	 * 
	 * @param size the size
	 */
	public BaseSquare(int size) {
		this(0, 0, size);
	}

	/**
	 * Create a new {@link BaseSquare} with given bottom left anchor and size.
	 * 
	 * @param left the left anchor
	 * @param bottom the bottom anchor
	 * @param size the size
	 */
	public BaseSquare(int left, int bottom, int size) {
		super(left, bottom, size + left - 1, size + bottom - 1);
	}
}

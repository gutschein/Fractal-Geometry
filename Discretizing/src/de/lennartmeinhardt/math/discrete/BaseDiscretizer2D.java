package de.lennartmeinhardt.math.discrete;

/**
 * A simple {@link Discretizer2D} implementation consisting of two discretizers for the dimensions.
 * 
 * @author Lennart Meinhardt
 */
public class BaseDiscretizer2D implements Discretizer2D {

	// the x and y discretizers to use
	private final Discretizer discretizerX, discretizerY;
	
	
	/**
	 * Create a new {@link BaseDiscretizer2D} with given x and y discretizers to use.
	 * 
	 * @param discretizerX the x discretizer to use
	 * @param discretizerY the y discretizer to use
	 */
	public BaseDiscretizer2D(Discretizer discretizerX, Discretizer discretizerY) {
		this.discretizerX = discretizerX;
		this.discretizerY = discretizerY;
	}
	
	/**
	 * Create a new {@link BaseDiscretizer2D} using the same discretizer for both dimensions.
	 * 
	 * @param discretizer the discretizer to use
	 */
	public BaseDiscretizer2D(Discretizer discretizer) {
		this(discretizer, discretizer);
	}
	
	
	@Override public double deDiscretizeX(int absoluteX) {
		return discretizerX.deDiscretize(absoluteX);
	}
	
	@Override public double deDiscretizeY(int absoluteY) {
		return discretizerY.deDiscretize(absoluteY);
	}
	
	@Override public int discretizeX(double relativeX) {
		return discretizerX.discretize(relativeX);
	}
	
	@Override public int discretizeY(double relativeY) {
		return discretizerY.discretize(relativeY);
	}
}

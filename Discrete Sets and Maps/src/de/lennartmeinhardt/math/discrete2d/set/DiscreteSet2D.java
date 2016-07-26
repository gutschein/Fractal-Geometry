package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A set of discrete two-dimensional points, i.e. integer tuples (x, y).
 * 
 * @author Lennart Meinhardt
 */
@FunctionalInterface
public interface DiscreteSet2D {

	/**
	 * Check if the given discrete point is contained in this set.
	 * 
	 * @param x the point's x coordinate
	 * @param y the point's y coordinate
	 * @return true if the point is contained in this set
	 */
	boolean containsPointAt(int x, int y);
	
	
	/**
	 * Get this set's complement. It contains exactly the points not contained in this set.
	 * 
	 * @return this set's complement 
	 */
	default DiscreteSet2D complement() {
		return (x, y) -> ! this.containsPointAt(x, y);
	}
	
	/**
	 * Get the ntersection of this set with another one.
	 * The intersection contains exactly the points that are contained in this and the other set.
	 * 
	 * @param other the set to intersect with
	 * @return intersection set
	 */
	default DiscreteSet2D intersection(DiscreteSet2D other) {
		return (x, y) -> this.containsPointAt(x, y) && other.containsPointAt(x, y);
	}
	
	/**
	 * Get the union of this set with another one.
	 * The union contains exactly the points that are contained in this, the other, or both sets.
	 * 
	 * @param other the other union set
	 * @return union set
	 */
	default DiscreteSet2D union(DiscreteSet2D other) {
		return (x, y) -> this.containsPointAt(x, y) || other.containsPointAt(x, y);
	}
	
	/**
	 * Get the difference of this set with another.
	 * It contains exactly the points of this set that aren't contained in the other one.
	 * 
	 * @param other the other set
	 * @return difference set
	 */
	default DiscreteSet2D difference(DiscreteSet2D other) {
		return (x, y) -> this.containsPointAt(x, y) && ! other.containsPointAt(x, y);
	}
	
	/**
	 * Get the symmetric difference of this set and another.
	 * It contains exactly the points contained in either of this or the other set, but not in both.
	 * 
	 * @param other the other set
	 * @return symmetric difference set
	 */
	default DiscreteSet2D symmetricDifference(DiscreteSet2D other) {
		return (x, y) -> this.containsPointAt(x, y) ^ other.containsPointAt(x, y);
	}
	
	/**
	 * Get the pre-image of this set under the given transformation.
	 * 
	 * @param transformation the transformation
	 * @return the pre-image of this set
	 */
	default DiscreteSet2D preImage(DiscreteTransformation2D transformation) {
		return (x, y) -> {
			int xTransformed = transformation.transformX(x, y);
			int yTransformed = transformation.transformY(x, y);
			return this.containsPointAt(xTransformed, yTransformed);
		};
	}
	
	/**
	 * Make this set operable, using the given {@link OperableDiscreteSet2D} as operating set.
	 * 
	 * @param range the set that handles operations
	 * @return operable version of this set
	 */
	default OperableDiscreteSet2D asOperable(OperableDiscreteSet2D range) {
		return new WrapperOperableDiscreteSet2D(this, range);
	}
	
	/**
	 * Make this set bounded by a rectangle, and operable. Uses the given {@link DiscreteRectangle} as bounds.
	 * 
	 * @param bounds the new bounds
	 * @return rectangular bounded version of this set
	 */
	default RectangularBoundsDiscreteSet2D asRectangularBounded(DiscreteRectangle bounds) {
		return new WrapperRectangularBoundsDiscreteSet2D(this, bounds);
	}
	

	// default sets containing no and all elements
	final DiscreteSet2D EMPTY = (x, y) -> false;
	final DiscreteSet2D ALL_CONTAINED = (x, y) -> true;
}

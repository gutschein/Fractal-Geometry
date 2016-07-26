package de.lennartmeinhardt.math.dim2.point;

import de.lennartmeinhardt.math.point.Point;

/**
 * A two-dimensional point.
 * 
 * @author Lennart Meinhardt
 */
public interface Point2D extends Point {
	
	/**
	 * Get the x coordinate.
	 * 
	 * @return the x coordinate
	 */
	double getX();
	
	/**
	 * Get the y coordinate.
	 * 
	 * @return the y coordinate
	 */
	double getY();
	
	
	@Override default double getCoordinate(int index) {
		if(index == 0)
			return getX();
		else if(index == 1)
			return getY();
		else throw new IndexOutOfBoundsException();
	}
	
	@Override default int getDimension() {
		return 2;
	}
}

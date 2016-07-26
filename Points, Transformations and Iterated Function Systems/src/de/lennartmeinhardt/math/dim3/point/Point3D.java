package de.lennartmeinhardt.math.dim3.point;

import de.lennartmeinhardt.math.point.Point;

/**
 * A three-dimensional point.
 * 
 * @author Lennart Meinhardt
 */
public interface Point3D extends Point {
	
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
	
	/**
	 * Get the z coordinate.
	 * 
	 * @return the z coordinate
	 */
	double getZ();
	
	
	@Override default double getCoordinate(int index) {
		if(index == 0)
			return getX();
		else if(index == 1)
			return getY();
		else if(index == 2)
			return getZ();
		else throw new IndexOutOfBoundsException();
	}
	
	@Override default int getDimension() {
		return 3;
	}
}

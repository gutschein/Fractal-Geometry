package de.lennartmeinhardt.math.transform;

import de.lennartmeinhardt.math.point.Point;

/**
 * This objects holds a vertex and a scaling factor.
 * 
 * @author Lennart Meinhardt
 *
 * @param <P> the type of vertex
 */
public interface VertexScalingHolder <P extends Point> {

	/**
	 * Get the scaling.
	 * 
	 * @return the scaling
	 */
	double getScaling();
	
	/**
	 * Get the vertex.
	 * 
	 * @return the vertex
	 */
	P getVertex();
}

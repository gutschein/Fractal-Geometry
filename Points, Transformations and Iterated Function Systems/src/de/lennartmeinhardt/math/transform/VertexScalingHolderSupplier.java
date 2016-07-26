package de.lennartmeinhardt.math.transform;

import de.lennartmeinhardt.math.point.Point;

/**
 * A supplier of {@link VertexScalingHolder}s from given vertex and scale data.
 * 
 * @author Lennart Meinhardt
 *
 * @param <P> the vertex type
 * @param <T> the type of generated {@link VertexScalingHolder}
 */
@FunctionalInterface
public interface VertexScalingHolderSupplier <P extends Point, T extends VertexScalingHolder<P>> {
	
	/**
	 * Get a {@link VertexScalingHolder} with given vertex and scaling data.
	 * 
	 * @param vertex the vertex
	 * @param scale the scaling
	 * @return a {@link VertexScalingHolder}
	 */
	T get(P vertex, double scale);
}

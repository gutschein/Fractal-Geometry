package de.lennartmeinhardt.math.transform;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.lennartmeinhardt.math.point.Point;

/**
 * Provides methods for creating lists of {@link VertexScalingHolder}s of given points and scalings.
 * 
 * @author Lennart Meinhardt
 */
public class VertexScalingHolders {
	
	/**
	 * Create a {@link List} of {@link VertexScalingHolder}s with given points and common scaling.
	 * 
	 * @param points the array of points
	 * @param commonScaling the holders' common scaling
	 * @param supplier the {@link VertexScalingHolder} supplier
	 * @return list of {@link VertexScalingHolder}s
	 */
	public static <P extends Point, T extends VertexScalingHolder<P>> List<T> ofPointsCommonScaling(P[] points, double commonScaling, VertexScalingHolderSupplier<P, T> supplier) {
		Objects.requireNonNull(points);
		Objects.requireNonNull(supplier);
		return IntStream.range(0, points.length).mapToObj(i -> supplier.get(points[i], commonScaling)).collect(Collectors.toList());
	}
	
	/**
	 * Create a {@link List} of {@link VertexScalingHolder}s with given points and scalings.
	 * 
	 * @param points the array of points
	 * @param scalings the array of scalings
	 * @param creator the {@link VertexScalingHolder} supplier
	 * @return list of {@link VertexScalingHolder}s
	 */
	public static <P extends Point, T extends VertexScalingHolder<P>> List<T> ofPointsAndScalings(P[] points, double[] scalings, VertexScalingHolderSupplier<P, T> creator) {
		Objects.requireNonNull(points);
		Objects.requireNonNull(scalings);
		Objects.requireNonNull(creator);
		return IntStream.range(0, points.length).mapToObj(i -> creator.get(points[i], scalings[i])).collect(Collectors.toList());
	}
	
	/**
	 * Create a {@link List} of {@link VertexScalingHolder}s with given points and scalings.
	 * 
	 * @param points the list of points
	 * @param commonScaling the holders' common scaling
	 * @param creator the {@link VertexScalingHolder} supplier
	 * @return list of {@link VertexScalingHolder}s
	 */
	public static <P extends Point, T extends VertexScalingHolder<P>> List<T> ofPoints(List<P> points, double commonScaling, VertexScalingHolderSupplier<P, T> creator) {
		Objects.requireNonNull(points);
		Objects.requireNonNull(creator);
		return IntStream.range(0, points.size()).mapToObj(i -> creator.get(points.get(i), commonScaling)).collect(Collectors.toList());
	}

	/**
	 * Create a {@link List} of {@link VertexScalingHolder}s with given points and scalings.
	 * 
	 * @param points the list of points
	 * @param scalings the array of scalings
	 * @param creator the {@link VertexScalingHolder} supplier
	 * @return list of {@link VertexScalingHolder}s
	 */
	public static <P extends Point, T extends VertexScalingHolder<P>> List<T> ofPoints(List<P> points, double[] scalings, VertexScalingHolderSupplier<P, T> creator) {
		Objects.requireNonNull(points);
		Objects.requireNonNull(scalings);
		Objects.requireNonNull(creator);
		return IntStream.range(0, points.size()).mapToObj(i -> creator.get(points.get(i), scalings[i])).collect(Collectors.toList());
	}

	
	// no instances
	public VertexScalingHolders() {
	}
}

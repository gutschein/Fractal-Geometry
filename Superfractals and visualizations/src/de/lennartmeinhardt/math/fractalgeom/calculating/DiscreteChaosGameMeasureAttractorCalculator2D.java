package de.lennartmeinhardt.math.fractalgeom.calculating;

import java.util.function.IntConsumer;

import de.lennartmeinhardt.math.TransformerChaosGamePlayer;
import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.dim2.transform.BasePointTransformer2D;
import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;

/**
 * The {@link DiscreteChaosGameMeasureAttractorCalculator2D} computes the measure attractor of an IFS using the chaos game.
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteChaosGameMeasureAttractorCalculator2D {

	// chaos game player that transforms points
	private final TransformerChaosGamePlayer<Point2D, Point2D, PointTransformation2D> chaosGamePlayer;
	private final Discretizer2D discretizer;
	private final FlatArrayDiscreteIntMap2D frequencyMap;
	private int iterationsCount;

	
	/**
	 * Create a new {@link DiscreteChaosGameMeasureAttractorCalculator2D} with given IFS, bounds, and discretizer.
	 * 
	 * @param ifs the IFS to use
	 * @param bounds bounds to use for argb map
	 * @param discretizer the discretizer to use for storing points to the discrete map
	 */
	public DiscreteChaosGameMeasureAttractorCalculator2D(ChoiceGeneratorIteratedFunctionSystem<? extends PointTransformation2D> ifs, DiscreteRectangle bounds, Discretizer2D discretizer) {
		BasePointTransformer2D pointTransformer = new BasePointTransformer2D();
		chaosGamePlayer = new TransformerChaosGamePlayer<>(pointTransformer, ifs);
		this.frequencyMap = new FlatArrayDiscreteIntMap2D(bounds);
		this.discretizer = discretizer;
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startPoint the start point to use for transforming
	 */
	public void reset(Point2D startPoint) {
		chaosGamePlayer.resetStartPoint(startPoint);
		frequencyMap.setToZero();
		iterationsCount = 0;
	}
	
	/**
	 * Calculate a number of iterations.
	 * In each iteration the last point is transformed and at the transformed point the frequency map value is increased.
	 * 
	 * @param iterations the iterations to run
	 */
	public void calculate(int iterations) {
		calculate(iterations, i -> {});
	}
	
	/**
	 * Calculate a number of iterations and operate on the current iteration number.
	 * In each iteration the last point is transformed and at the transformed point the frequency map value is increased.
	 * 
	 * @param iterations the iterations to run
	 * @param operation the operation to perform
	 */
	public void calculate(int iterations, IntConsumer operation) {
		for(int i = 0; i < iterations; i++) {
			operation.accept(i);
			chaosGamePlayer.next();
			Point2D lastPoint = chaosGamePlayer.getLastPoint();
			int xAbsolute = discretizer.discretizeX(lastPoint.getX());
			int yAbsolute = discretizer.discretizeY(lastPoint.getY());
			frequencyMap.incrementValueAt(xAbsolute, yAbsolute);
			iterationsCount++;
		}
	}
	
	/**
	 * Get the frequency map calculated.
	 * 
	 * @return the frequency map
	 */
	public FlatArrayDiscreteIntMap2D getFrequencyMap() {
		return frequencyMap;
	}
	
	/**
	 * Get the total number of iterations run.
	 * 
	 * @return the number of iterations
	 */
	public int getIterationsCount() {
		return iterationsCount;
	}
}

package de.lennartmeinhardt.math.fractalgeom.calculating;

import de.lennartmeinhardt.math.TransformerChaosGamePlayer;
import de.lennartmeinhardt.math.argb.dim2.point.ArgbPoint2D;
import de.lennartmeinhardt.math.argb.dim2.transform.ArgbPointTransformation2D;
import de.lennartmeinhardt.math.argb.dim2.transform.BaseArgbPointTransformer2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;

/**
 * The {@link DiscreteChaosGameIfsColoringCalculator2D} computes the set attractor of an IFS using the chaos game, and colorizes it using IFS coloring.
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteChaosGameIfsColoringCalculator2D {
	
	// chaos game player that transforms argb points
	private final TransformerChaosGamePlayer<ArgbPoint2D, ArgbPoint2D, ArgbPointTransformation2D> chaosGamePlayer;
	private final Discretizer2D discretizer;
	private final FlatArrayDiscreteIntMap2D argbMap;
	private int iterationsCount;
	
	
	/**
	 * Create a new {@link DiscreteChaosGameIfsColoringCalculator2D} with given IFS, bounds, and discretizer.
	 * 
	 * @param ifs the IFS to use
	 * @param bounds bounds to use for argb map
	 * @param discretizer the discretizer to use for storing points to the discrete map
	 */
	public DiscreteChaosGameIfsColoringCalculator2D(ChoiceGeneratorIteratedFunctionSystem<? extends ArgbPointTransformation2D> ifs, DiscreteRectangle bounds, Discretizer2D discretizer) {
		BaseArgbPointTransformer2D pointTransformer = new BaseArgbPointTransformer2D();
		this.chaosGamePlayer = new TransformerChaosGamePlayer<>(pointTransformer, ifs);
		this.argbMap = new FlatArrayDiscreteIntMap2D(bounds);
		this.discretizer = discretizer;
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startSet the start point to use for transforming
	 */
	public void reset(ArgbPoint2D startPoint) {
		chaosGamePlayer.resetStartPoint(startPoint);
		argbMap.setToZero();
		iterationsCount = 0;
	}
	
	/**
	 * Calculate a number of iterations.
	 * In each iteration the last argb point is transformed and at the transformed point the argb map value is set to the transformed color.
	 * 
	 * @param iterations the iterations to run
	 */
	public void calculate(int iterations) {
		for(int i = 0; i < iterations; i++) {
			chaosGamePlayer.next();
			ArgbPoint2D lastPoint = chaosGamePlayer.getLastPoint();
			int xAbsolute = discretizer.discretizeX(lastPoint.getX());
			int yAbsolute = discretizer.discretizeY(lastPoint.getY());
			argbMap.setValueAt(xAbsolute, yAbsolute, lastPoint.getArgb());
			iterationsCount++;
		}
	}
	
	/**
	 * Get the argb map calculated.
	 * 
	 * @return the argb map
	 */
	public FlatArrayDiscreteIntMap2D getArgbMap() {
		return argbMap;
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

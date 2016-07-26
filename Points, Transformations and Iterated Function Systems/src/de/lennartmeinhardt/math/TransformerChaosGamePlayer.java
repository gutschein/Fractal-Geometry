package de.lennartmeinhardt.math;

import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;

/**
 * This object plays the chaos game by transforming points.
 * In each iteration the point is transformed with an IFS's random transformation.
 * 
 * @author Lennart Meinhardt
 * 
 * @param <P> the type of object being transformed
 * @param <Q> the type of objects to reset
 * @param <T> the type of transformations that can be used
 */
public class TransformerChaosGamePlayer <P, Q, T> {
	
	// the object that transforms points
	private final Transformer<? extends P, ? super Q, ? super T> pointTransformer;
	// the IFS used to generate random choices, and to transform the point
	private final ChoiceGeneratorIteratedFunctionSystem<? extends T> ifs;
	
	// the last chosen transformation index
	private int lastChoice;
	
	
	/**
	 * Create a new {@link TransformerChaosGamePlayer} with given transformer and IFS.
	 * 
	 * @param pointTransformer the object that transforms points
	 * @param ifs the IFS to play the chaos game with
	 */
	public TransformerChaosGamePlayer(Transformer<? extends P, ? super Q, ? super T> pointTransformer, ChoiceGeneratorIteratedFunctionSystem<? extends T> ifs) {
		this.pointTransformer = pointTransformer;
		this.ifs = ifs;
	}
	
	
	/**
	 * Get the index of the last transformation choice.
	 * 
	 * @return the last chosen transformation index
	 */
	public int getLastChoice() {
		return lastChoice;
	}
	
	/**
	 * Get the last point computed by the chaos game.
	 * 
	 * @return the last chaos game point
	 */
	public P getLastPoint() {
		return pointTransformer.get();
	}
	
	/**
	 * Reset the chaos game start point.
	 * 
	 * @param startPoint the point to start the chaos game with
	 */
	public void resetStartPoint(Q startPoint) {
		pointTransformer.reset(startPoint);
		lastChoice = -1;
	}
	
	/**
	 * Perform another iteration. A random choice is generated and the point is transformed with the transformation of that index.
	 */
	public void next() {
		lastChoice = ifs.getRandomChoice();
		T transformation = ifs.getTransformation(lastChoice);
		pointTransformer.transform(transformation);
	}
}

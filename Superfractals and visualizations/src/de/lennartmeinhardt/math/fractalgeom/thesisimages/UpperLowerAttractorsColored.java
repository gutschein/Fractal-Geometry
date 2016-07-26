package de.lennartmeinhardt.math.fractalgeom.thesisimages;

import java.awt.image.BufferedImage;

import de.lennartmeinhardt.imaging.SimpleImageDisplay;
import de.lennartmeinhardt.math.argb.dim2.point.ArgbPoint2D;
import de.lennartmeinhardt.math.argb.dim2.point.MutableArgbPoint2D;
import de.lennartmeinhardt.math.argb.dim2.transform.ArgbPointTransformation2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete.ToUnitRectangleDiscretizer2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.OperableDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.fractalgeom.UpperLowerExamples;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscreteChaosGameIfsColoringCalculator2D;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.util.BaseRandomRangeIntGenerator;
import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;

/**
 * Create the upper/lower colored set attractors image.
 * 
 * Calculation takes a few seconds.
 * 
 * @author Lennart Meinhardt
 */
public class UpperLowerAttractorsColored {

	private static final int resolution = 2500;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final int pointsToDraw = 10000000;
	private static final Discretizer2D discretizer = new ToUnitRectangleDiscretizer2D(bounds);
	private static final RangeRandomIntGenerator randomGenerator = BaseRandomRangeIntGenerator.INSTANCE;
	
	private static final ArgbPoint2D upperStartPoint = new MutableArgbPoint2D(.25, .5, UpperLowerExamples.upperLeftColor);
	private static final ArgbPoint2D lowerStartPoint = new MutableArgbPoint2D(.25, .5, UpperLowerExamples.lowerLeftColor);
	
	
	/**
	 * Create the upper/lower colored attractors argb map.
	 * 
	 * @return argb map depicting the colored upper and lower set attractors.
	 */
	private static OperableDiscreteIntMap2D<DiscreteRectangle> upperLowerAttractorsColored() {
		ChoiceGeneratorIteratedFunctionSystem<? extends ArgbPointTransformation2D> upperColoredEqualProbabilities = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(UpperLowerExamples.upperColored, randomGenerator);
		FlatArrayDiscreteIntMap2D upperImage = playChaosGame(upperColoredEqualProbabilities, upperStartPoint);
		
		ChoiceGeneratorIteratedFunctionSystem<? extends ArgbPointTransformation2D> lowerColoredEqualProbabilities = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(UpperLowerExamples.lowerColored, randomGenerator);
		FlatArrayDiscreteIntMap2D lowerImage = playChaosGame(lowerColoredEqualProbabilities, lowerStartPoint);
		
		DiscreteIntMap2D overlayImage = DiscreteIntMap2D.ifThenElse(upperImage.support()::containsPointAt, lowerImage, upperImage);
		return overlayImage.asOperable(bounds);
	}
	
	/**
	 * Create the colored set attractor of the given IFS using the chaos game.
	 * 
	 * @param ifs the IFS whose set attractor will be depicted
	 * @param startPoint the start point to use
	 * @return the argb map depiction of the colored set attractor
	 */
	private static FlatArrayDiscreteIntMap2D playChaosGame(ChoiceGeneratorIteratedFunctionSystem<? extends ArgbPointTransformation2D> ifs, ArgbPoint2D startPoint) {
		DiscreteChaosGameIfsColoringCalculator2D calculator = new DiscreteChaosGameIfsColoringCalculator2D(ifs, bounds, discretizer);
		calculator.reset(startPoint);
		calculator.calculate(pointsToDraw);
		return calculator.getArgbMap();
	}
	
	
	/**
	 * Create the image and displays it.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		upperLowerAttractorsColored().forEach((x, y, argb) -> img.setRGB(x, resolution - 1 - y, argb));
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Upper/lower attractors");
		display.setPreferredSaveFileName("upper_lower_attractors_colored.png");
		display.setVisible(true);
	}
}

package de.lennartmeinhardt.math.fractalgeom.thesisimages;

import java.awt.image.BufferedImage;

import de.lennartmeinhardt.imaging.Amplifiers;
import de.lennartmeinhardt.imaging.ArgbMapHelper;
import de.lennartmeinhardt.imaging.SimpleImageDisplay;
import de.lennartmeinhardt.math.discrete.BaseDiscretizer2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete.LinearRescaleDiscretizer;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.OperableDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.RectangularBoundsDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.fractalgeom.UpperLowerExamples;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscretePointwiseFrequencyCounter2D;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;
import de.lennartmeinhardt.math.util.BaseRandomRangeIntGenerator;
import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;
import de.lennartmeinhardt.util.TimingHelper;

/**
 * Creates the left/right point-wise frequency map image.
 * It is scaled to attain the value 1. No further amplification is necessary.
 * 
 * @author Lennart Meinhardt
 */
public class LeftRightPointWiseFrequency {
	
	private static final int resolution = 2500;
	private static final int iterationsToDraw = 100;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final Discretizer2D discretizer = new BaseDiscretizer2D(new LinearRescaleDiscretizer(resolution));
	
	private static final RangeRandomIntGenerator randomGenerator = new BaseRandomRangeIntGenerator();
	private static final ChoiceGeneratorIteratedFunctionSystem<? extends IteratedFunctionSystem<? extends DiscreteTransformation2D>> discreteSuperIfs;
	static {
		IteratedFunctionSystem<? extends IteratedFunctionSystem<DiscreteTransformation2D>> discreteSuperIfsNoProbabilities = UpperLowerExamples.getDiscreteSuperIfs(UpperLowerExamples.leftRightSuperIfs, discretizer);
		discreteSuperIfs = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(discreteSuperIfsNoProbabilities, randomGenerator);
	}
	
	
	/**
	 * Create the start set containing only the discretization of the point (.25, .5).
	 * It is the set attractor of the left IFS.
	 * 
	 * @return the left set attractor
	 */
	private static RectangularBoundsDiscreteSet2D createStartSet() {
		// the start set contains only the point (.25, .5)
		DiscreteSet2D singlePointSet = (x, y) -> x == resolution / 4 && y == resolution / 2;
		return singlePointSet.asRectangularBounded(bounds);
	}
	
	/**
	 * Create the left/right point-wise frequency map as argb map.
	 * 
	 * @return the left/right point-wise frequency map
	 */
	public static OperableDiscreteIntMap2D<DiscreteRectangle> upperLowerFrequency() {
		DiscretePointwiseFrequencyCounter2D calculator = new DiscretePointwiseFrequencyCounter2D(discreteSuperIfs, bounds);
		
		// do the calculation
		long t = TimingHelper.startCalculation();
		calculator.reset(createStartSet());
		calculator.calculate(iterationsToDraw, i -> System.out.println("current iteration: " + i));
		TimingHelper.stopCalculation(t);
		
		FlatArrayDiscreteIntMap2D countMap = calculator.getFrequencyMap();
		printMapStatistics(countMap);
		
		// convert the frequency map to an argb map
		DiscreteDoubleMap2D relativeMap = Amplifiers.scaleToMax(countMap);
		DiscreteIntMap2D colorMap = ArgbMapHelper.relativeToGrayscaleArgbMap(relativeMap);
		return colorMap.asOperable(bounds);
	}
	
	private static void printMapStatistics(OperableDiscreteIntMap2D<?> countMap) {
		double max = countMap.getMaximumValue();
		double maxNormed = max / iterationsToDraw;
		System.out.println("max value on overlay: " + max + "\n\tnormed by iterations count: " + maxNormed);
	}
	
	
	/**
	 * Create the image and displays it.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		upperLowerFrequency().forEach((x, y, argb) -> img.setRGB(x, resolution - 1 - y, argb));
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Left/right pointwise frequency");
		display.setPreferredSaveFileName("left_right_frequency.png");
		display.setVisible(true);
	}
}

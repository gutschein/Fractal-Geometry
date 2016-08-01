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
import de.lennartmeinhardt.math.discrete2d.set.RectangularBoundsDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.fractalgeom.UpperLowerExamples;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscreteDeterministicSetAttractorCalculator2D;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscretePointwiseFrequencyCounter2D;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;
import de.lennartmeinhardt.math.util.BaseRandomRangeIntGenerator;
import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;
import de.lennartmeinhardt.util.TimingHelper;

/**
 * Creates the upper/lower point-wise frequency map image.
 * It is scaled to attain the value 1 and then amplified using the default variants.
 * 
 * @author Lennart Meinhardt
 */
public class UpperLowerPointWiseFrequency {

	// iterations for calculating the start map
	private static final int iterationsToSkip = 30;
	private static final int iterationsToDraw = 10000;
	private static final int resolution = 2500;
	private static final double amplifyParam = 5;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final Discretizer2D discretizer = new BaseDiscretizer2D(new LinearRescaleDiscretizer(resolution));
	
	private static final RangeRandomIntGenerator randomGenerator = new BaseRandomRangeIntGenerator();
	private static final ChoiceGeneratorIteratedFunctionSystem<? extends IteratedFunctionSystem<? extends DiscreteTransformation2D>> discreteSuperIfs;
	static {
		IteratedFunctionSystem<? extends IteratedFunctionSystem<DiscreteTransformation2D>> discreteSuperIfsNoProbabilities = UpperLowerExamples.getDiscreteSuperIfs(UpperLowerExamples.upperLowerSuperIfs, discretizer);
		discreteSuperIfs = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(discreteSuperIfsNoProbabilities, randomGenerator);
	}
	
	
	/**
	 * Create the start set as upper set attractor approximation.
	 * 
	 * @return the upper set attractor approximation
	 */
	private static RectangularBoundsDiscreteSet2D createStartSet() {
		IteratedFunctionSystem<? extends DiscreteTransformation2D> discreteUpper = UpperLowerExamples.getDiscreteIfs(UpperLowerExamples.upper, discretizer);
		DiscreteDeterministicSetAttractorCalculator2D setAttractorCalculator = new DiscreteDeterministicSetAttractorCalculator2D(discreteUpper, bounds);
		setAttractorCalculator.reset(UpperLowerExamples.getDiscreteCircleSet(resolution));
		setAttractorCalculator.calculate(iterationsToSkip);
		return setAttractorCalculator.getDiscreteSet();
	}
	
	/**
	 * Create the upper/lower point-wise frequency map as argb map.
	 * 
	 * @return the upper/lower point-wise frequency map
	 */
	public static OperableDiscreteIntMap2D<DiscreteRectangle> upperLowerFrequency() {
		DiscretePointwiseFrequencyCounter2D calculator = new DiscretePointwiseFrequencyCounter2D(discreteSuperIfs, bounds);
		
		// do the calculation
		long t = TimingHelper.startCalculation();
		calculator.reset(createStartSet());
		System.out.println("start map was created. performing actual calculation ...");
		calculator.calculate(iterationsToDraw, i -> System.out.println("current iteration: " + i));
		TimingHelper.stopCalculation(t);
		
		FlatArrayDiscreteIntMap2D countMap = calculator.getFrequencyMap();
		printMapStatistics(countMap);
		
		// convert the frequency map to an argb map
		DiscreteDoubleMap2D relativeMap = Amplifiers.scaleToMax(countMap);
		relativeMap = Amplifiers.amplifyAllDefault(relativeMap, bounds, amplifyParam);
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
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Upper/lower pointwise frequency");
		display.setPreferredSaveFileName("upper_lower_frequency.png");
		display.setVisible(true);
	}
}

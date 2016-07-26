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
import de.lennartmeinhardt.math.discrete2d.doublemap.FlatArrayDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.OperableDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.fractalgeom.UpperLowerExamples;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscreteDeterministicMeasureAttractorCalculator2D;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.ProbabilityIteratedFunctionSystem;
import de.lennartmeinhardt.util.TimingHelper;

/**
 * Create the upper/lower underlying measure attractor using deterministically.
 * A start map is transformed a fixed number of times.
 * It is scaled to attain the value 1 and then amplified using the default variants.
 * 
 * Calculation takes about a minute.
 * 
 * @author Lennart Meinhardt
 */
public class DeterministicUnderlyingMeasureAttractor {
	
	private static final int iterations = 30;
	private static final int resolution = 2500;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final double amplifyParam = 4;
	private static final OperableDiscreteDoubleMap2D<DiscreteRectangle> startMap = UpperLowerExamples.getDiscreteCircleDoubleMapNormed(resolution);

//	private static final Discretizer2D discretizer = new ToUnitRectangleDiscretizer2D(bounds);
	private static final Discretizer2D discretizer = new BaseDiscretizer2D(new LinearRescaleDiscretizer(resolution), new LinearRescaleDiscretizer(resolution));
	private static final ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D> discreteUnderlyingIfs = new EqualProbabilitiesIteratedFunctionSystem<>(UpperLowerExamples.getDiscreteIfs(UpperLowerExamples.underlyingIfs, discretizer));

	
	/**
	 * Create the upper/lower underlying measure attractor deterministically.
	 * The result is an argb map.
	 * 
	 * @return argb map depicting the underlying measure attractor
	 */
	public static OperableDiscreteIntMap2D<DiscreteRectangle> upperLowerMeasureAttractorDeterministic() {
		DiscreteDeterministicMeasureAttractorCalculator2D calculator = new DiscreteDeterministicMeasureAttractorCalculator2D(discreteUnderlyingIfs, bounds);
		
		// do calculation
		long t = TimingHelper.startCalculation();
		calculator.reset(startMap);
		calculator.calculate(iterations, i -> System.out.println("current iteration: " + i));
		FlatArrayDiscreteDoubleMap2D measureMap = calculator.getMeasureMap();
		TimingHelper.stopCalculation(t);
		
		printMapStatistics(measureMap);
		
		DiscreteDoubleMap2D relativeMap = Amplifiers.scaleToMax(measureMap);
		relativeMap = Amplifiers.amplifyAllDefault(relativeMap, bounds, amplifyParam);
		DiscreteIntMap2D colorMap = ArgbMapHelper.relativeToGrayscaleArgbMap(relativeMap);
		return colorMap.asOperable(bounds);
	}
	
	private static void printMapStatistics(OperableDiscreteDoubleMap2D<?> measureMap) {
		double max = measureMap.getMaximumValue();
		double integral = measureMap.getSum();
		System.out.println("max value on overlay: " + max);
		System.out.println("integral of overlay: " + integral);
	}
	
	
	/**
	 * Create the image and displays it.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		upperLowerMeasureAttractorDeterministic().forEach((x, y, argb) -> img.setRGB(x, resolution - 1 - y, argb));
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Deterministic underlying measure attractor");
		display.setPreferredSaveFileName("measure_attractor_deterministic.png");
		display.setVisible(true);
	}
}

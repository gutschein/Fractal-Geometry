package de.lennartmeinhardt.math.fractalgeom.thesisimages;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

import de.lennartmeinhardt.imaging.Amplifiers;
import de.lennartmeinhardt.imaging.ArgbMapHelper;
import de.lennartmeinhardt.imaging.SimpleImageDisplay;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete.ToUnitRectangleDiscretizer2D;
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
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscreteSuperMeasureProjectionCalculator2D;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.ProbabilityIteratedFunctionSystem;
import de.lennartmeinhardt.math.util.BaseRandomRangeIntGenerator;
import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;
import de.lennartmeinhardt.util.TimingHelper;

/**
 * Creates the upper/lower super-measure projection image.
 * The result should equal the other methods for creating the upper/lower underlying measure attractor, {@link DeterministicUnderlyingMeasureAttractor} and {@link ChaosGameUnderlyingMeasureAttractor}.
 * It is scaled to attain the value 1 and then amplified using the default variants.
 * 
 * @author Lennart Meinhardt
 */
public class UpperLowerSuperMeasureProjection {

	// iterations for calculating the start map
	private static final int iterationsToSkip = 30;
	private static final int iterationsToDraw = 10000;
	private static final int resolution = 2500;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final double amplifyParam = 5;
	private static final RangeRandomIntGenerator randomGenerator = new BaseRandomRangeIntGenerator();
	
	private static final Discretizer2D discretizer = new ToUnitRectangleDiscretizer2D(bounds);
	
	private static final ChoiceGeneratorIteratedFunctionSystem<? extends ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D>> discreteSuperIfs;
	static {
		List<ProbabilityIteratedFunctionSystem<DiscreteTransformation2D>> ifsList = UpperLowerExamples.upperLowerSuperIfs
				.stream()
				.map(ifs -> ifs.stream().map(discretizer::discretize).collect(Collectors.toList()))
				.map(list -> new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(list, randomGenerator))
				.collect(Collectors.toList());
		discreteSuperIfs = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(ifsList, randomGenerator);
	}

	
	/**
	 * Create the start map as upper measure attractor approximation.
	 * 
	 * @return the upper measure attractor approximation
	 */
	private static FlatArrayDiscreteDoubleMap2D createStartMap() {
		ProbabilityIteratedFunctionSystem<DiscreteTransformation2D> discreteProbabilityUpper = new EqualProbabilitiesIteratedFunctionSystem<>(UpperLowerExamples.getDiscreteIfs(UpperLowerExamples.upper, discretizer));
		DiscreteDeterministicMeasureAttractorCalculator2D upperMeasureAttractorCalculator = new DiscreteDeterministicMeasureAttractorCalculator2D(discreteProbabilityUpper, bounds);
		upperMeasureAttractorCalculator.reset(UpperLowerExamples.getDiscreteCircleDoubleMapNormed(resolution));
		upperMeasureAttractorCalculator.calculate(iterationsToSkip);
		return upperMeasureAttractorCalculator.getMeasureMap();
	}
	
	/**
	 * Create the upper/lower super-measure projection as argb map.
	 * 
	 * @return the upper/lower super-measure as argb map
	 */
	public static OperableDiscreteIntMap2D<DiscreteRectangle> createUpperLowerSuperMeasureProjection() {
		DiscreteSuperMeasureProjectionCalculator2D calculator = new DiscreteSuperMeasureProjectionCalculator2D(discreteSuperIfs, bounds);
		
		System.out.println("creating the start map");
		calculator.reset(createStartMap());

		// do the calculation
		long t = TimingHelper.startCalculation();
		System.out.println("start map was created. performing actual calculation ...");
		calculator.calculate(iterationsToDraw, i -> System.out.println("current iteration: " + i));
		TimingHelper.stopCalculation(t);
		
		FlatArrayDiscreteDoubleMap2D overlayMap = calculator.getOverlayMeasureMap();
		printMapStatistics(overlayMap);
		
		// convert the overlay map to an argb map
		DiscreteDoubleMap2D relativeMap = Amplifiers.scaleToMax(overlayMap);
		relativeMap = Amplifiers.amplifyAllDefault(relativeMap, bounds, amplifyParam);
		DiscreteIntMap2D colorMap = ArgbMapHelper.relativeToGrayscaleArgbMap(relativeMap);
		return colorMap.asOperable(bounds);
	}
	
	private static void printMapStatistics(OperableDiscreteDoubleMap2D<?> overlayMap) {
		double max = overlayMap.getMaximumValue();
		double maxNormed = max / iterationsToDraw;
		double integral = overlayMap.getSum();
		double integralNormed = integral / iterationsToDraw;
		System.out.println("max value on overlay: " + max + "\n\tnormed by iterations count: " + maxNormed);
		System.out.println("integral of overlay: " + integral + "\n\tnormed by iterations count: " + integralNormed);
	}
	
	
	/**
	 * Create the image and displays it.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		createUpperLowerSuperMeasureProjection().forEach((x, y, argb) -> img.setRGB(x, resolution - 1 - y, argb));
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Upper/lower super-measure projection");
		display.setPreferredSaveFileName("measure_projection.png");
		display.setVisible(true);
	}
}

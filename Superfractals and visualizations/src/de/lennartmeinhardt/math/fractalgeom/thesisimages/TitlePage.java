package de.lennartmeinhardt.math.fractalgeom.thesisimages;

import java.awt.image.BufferedImage;
import java.util.function.DoubleUnaryOperator;

import de.lennartmeinhardt.imaging.Amplifiers;
import de.lennartmeinhardt.imaging.ArgbMapHelper;
import de.lennartmeinhardt.imaging.SimpleImageDisplay;
import de.lennartmeinhardt.math.dim2.point.ImmutablePoint2D;
import de.lennartmeinhardt.math.dim2.point.Point2D;
import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete.ToUnitRectangleDiscretizer2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.OperableDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.fractalgeom.UpperLowerExamples;
import de.lennartmeinhardt.math.fractalgeom.calculating.DiscreteChaosGameMeasureAttractorCalculator2D;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.util.BaseRandomRangeIntGenerator;
import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;
import de.lennartmeinhardt.util.TimingHelper;

/**
 * Creates the title page image, i.e. the upper/lower underlying measure attractor.
 * It is scaled to attain the value 1 and then amplified using the power function.
 * 
 * @author Lennart Meinhardt
 */
public class TitlePage {
	
	private static final int iterationsToDraw = 500000000;
	private static final int resolution = 3000;
	private static final DiscreteRectangle bounds = new BaseSquare(resolution);
	private static final Point2D startPoint = new ImmutablePoint2D(.25, .5);
	private static final double amplifyParam = 5;
	private static final DoubleUnaryOperator amplifier = Amplifiers.amplifyMin(amplifyParam);
	private static final RangeRandomIntGenerator randomGenerator = new BaseRandomRangeIntGenerator();
	
	private static final Discretizer2D discretizer = new ToUnitRectangleDiscretizer2D(bounds);
	private static final ChoiceGeneratorIteratedFunctionSystem<? extends PointTransformation2D> underlyingIfs = new EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem<>(UpperLowerExamples.underlyingIfs, randomGenerator);

	
	/**
	 * Create the title page argb map.
	 * 
	 * @return the title page argb map
	 */
	public static OperableDiscreteIntMap2D<DiscreteRectangle> getTitlePageColorMap() {
		// prepare calculation
		DiscreteChaosGameMeasureAttractorCalculator2D calculator = new DiscreteChaosGameMeasureAttractorCalculator2D(underlyingIfs, bounds, discretizer);
		calculator.reset(startPoint);
		
		// start calculation
		long t = TimingHelper.startCalculation();
		calculator.calculate(iterationsToDraw, i -> {
			if(i % 10000000 == 0)
				System.out.println("current iteration: " + i);
		});
		FlatArrayDiscreteIntMap2D frequencyMap = calculator.getFrequencyMap();
		TimingHelper.stopCalculation(t);
		
		// print map statistics
		System.out.println("max value on count map: " + frequencyMap.getMaximumValue());
		System.out.println("sum on count map: " + frequencyMap.getSum());
		
		// scale the map so the value 1 is attained
		DiscreteDoubleMap2D relativeMap = Amplifiers.scaleToMax(frequencyMap);
		// amplify the map with 1 - (1-t)^p
		relativeMap = relativeMap.pushForward(amplifier);
		
		// create channel map
		DiscreteIntMap2D channelMap = ArgbMapHelper.relativeMapToChannelMap(relativeMap);
		channelMap = ArgbMapHelper.swapChannelMinMax(channelMap);
		// make 0 = white, 1 = black
		DiscreteIntMap2D colorMap = ArgbMapHelper.channelMapToArgb(channelMap);
		OperableDiscreteIntMap2D<DiscreteRectangle> colorMapForImage = colorMap.asOperable(bounds);
		return colorMapForImage;
	}
	
	
	/**
	 * Create the image and displays it.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		getTitlePageColorMap().forEach((x, y, argb) -> img.setRGB(x, resolution - 1 - y, argb));
		
		SimpleImageDisplay display = new SimpleImageDisplay(img, "Titlepage image");
		display.setPreferredSaveFileName("titlepage_image.png");
		display.setVisible(true);
	}
}

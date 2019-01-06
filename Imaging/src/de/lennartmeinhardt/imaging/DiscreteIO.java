package de.lennartmeinhardt.imaging;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

public class DiscreteIO {

	public static FlatArrayDiscreteIntMap2D readIntMapFromFile(Path path) throws IOException {
		byte[] bytes = Files.readAllBytes(path);
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		int width = byteBuffer.getInt();
		int height = byteBuffer.getInt();
		int left = byteBuffer.getInt();
		int bottom = byteBuffer.getInt();
		IntBuffer intBuffer = byteBuffer.asIntBuffer();
		int[] values = new int[width * height];
		intBuffer.get(values);
		return new FlatArrayDiscreteIntMap2D(left, bottom, width, height, values);
	}
	
	public static void saveIntMapToFile(Path path, FlatArrayDiscreteIntMap2D map) throws IOException {
		DiscreteRectangle bounds = map.getRange();
		int[] rawData = map.getRawData();
		int totalInts = 4 + rawData.length;
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES * totalInts);
		byteBuffer.putInt(bounds.getWidth());
		byteBuffer.putInt(bounds.getHeight());
		byteBuffer.putInt(bounds.getLeft());
		byteBuffer.putInt(bounds.getBottom());
		byteBuffer.asIntBuffer().put(map.getRawData());
		Files.write(path, byteBuffer.array());
	}
	
	public static void saveIntMapToFile(Path path, DiscreteIntMap2D map, DiscreteRectangle bounds) throws IOException {
		int width = bounds.getWidth(),
			height = bounds.getHeight(),
			left = bounds.getLeft(),
			right = bounds.getRight(),
			bottom = bounds.getBottom(),
			top = bounds.getTop();
		
		int totalInts = 4 + width * height;
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES * totalInts);
		byteBuffer.putInt(width);
		byteBuffer.putInt(height);
		byteBuffer.putInt(left);
		byteBuffer.putInt(bottom);
		for(int j = bottom; j <= top; j++)
			for(int i = left; i <= right; i++)
				byteBuffer.putInt(map.getValueAt(i, j));
		Files.write(path, byteBuffer.array());
	}
	
	
	private DiscreteIO() {
	}
}

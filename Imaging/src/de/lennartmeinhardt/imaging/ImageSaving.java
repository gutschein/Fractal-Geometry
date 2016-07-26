package de.lennartmeinhardt.imaging;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageSaving {
	
	/**
	 * Save an image as PNG file.
	 * 
	 * @param img the image to save
	 * @param outFile the file to save the image to
	 * @return true if saving was successful
	 */
	public static boolean savePngImage(BufferedImage img, File outFile) {
		try {
			boolean success = ImageIO.write(img, "png", outFile);
			System.out.println("Saving image to " + outFile + " was " + (success ? "" : "not ") + "successful.");
			return success;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Let the user choose a file to save to. A dialog will be shown until a valid file is selected, or until the dialog was cancelled.
	 * Asks before overwriting a file.
	 * 
	 * @param parent the calling window
	 * @param startFile the preferred file
	 * @return the selected file, or an empty optional if no valid file was selected
	 */
	public static Optional<File> getPngWriteFile(Component parent, File startFile) {
		File outFile = null;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
		fileChooser.setSelectedFile(startFile);
		
		// loop so the dialog will be re-opened when overwriting is not wanted
		while(true) {
			// check if the dialog was cancelled
			int result = fileChooser.showSaveDialog(parent);
			if(result == JFileChooser.CANCEL_OPTION) {
				return Optional.empty();
			}
			outFile = fileChooser.getSelectedFile();
			if(outFile != null && outFile.exists() && ! outFile.canWrite()) {
				continue;
			}
			// ask for overwriting
			if(outFile.exists()) {
				int resultOverwrite = JOptionPane.showConfirmDialog(parent, "Do you want to overwrite the file?\n" + outFile, "Overwrite file?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(resultOverwrite == JOptionPane.CANCEL_OPTION) {
					return Optional.empty();
				} else if(resultOverwrite == JOptionPane.YES_OPTION)
					break;
				else if(resultOverwrite == JOptionPane.NO_OPTION) {
					continue;
				}
			} else
				break;
		}
		
		// a valid file was selected and outfile is not null
		return Optional.of(outFile);
	}
	
	/**
	 * Gets an image to save to, and save the given image.
	 * 
	 * @param image the image to save
	 * @param startFile the preferred file to save to
	 */
	public static void getFileAndSaveImage(BufferedImage image, File startFile) {
		File file = getPngWriteFile(null, startFile).orElse(null);
		if(file != null)
			ImageSaving.savePngImage(image, file);
	}
	
	
	// no instances
	private ImageSaving() {
	}
}

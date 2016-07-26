package de.lennartmeinhardt.imaging;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Displays a {@link BufferedImage} in a window. The image can be saved.
 * 
 * @author Lennart Meinhardt
 */
@SuppressWarnings("serial")
public class SimpleImageDisplay extends JFrame {
	
	// default "allow enlargement" value
	private static final boolean DEF_ALLOW_ENLARGE = false;
	// default "keep aspect ratio" value
	private static final boolean DEF_KEEP_ASPECT_RATIO = true;
	
	// the image being displayed
	private BufferedImage image;
	
	// the default file to save to
	private File startFile;
	
	// keep the image's aspect ratio
	private boolean keepAspectRatio = DEF_KEEP_ASPECT_RATIO;
	// allow the image to be enlarged
	private boolean allowEnlarge = DEF_ALLOW_ENLARGE;

	
	/**
	 * Create a new {@link SimpleImageDisplay}.
	 */
	public SimpleImageDisplay() {
		this((BufferedImage) null);
	}
	
	/**
	 * Create a new {@link SimpleImageDisplay} with given title.
	 * 
	 * @param title the title
	 */
	public SimpleImageDisplay(String title) {
		this(null, title);
	}
	
	/**
	 * Create a new {@link SimpleImageDisplay} with given image.
	 * 
	 * @param image the image to display
	 */
	public SimpleImageDisplay(BufferedImage image) {
		this(image, "Image display");
	}
	
	/**
	 * Create a new {@link SimpleImageDisplay} with given image and title.
	 * 
	 * @param image the image to display
	 * @param title the title
	 */
	public SimpleImageDisplay(BufferedImage image, String title) {
		super(title);
		this.image = image;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		}
		
		// create layout and menu
		setLayout(new BorderLayout());
		ImagePanel imagePanel = new ImagePanel();
		add(imagePanel, BorderLayout.CENTER);
		setJMenuBar(createMenuBar());
		
		// q closed the app, s saves the image
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_Q)
					System.exit(0);
				if(e.getKeyCode() == KeyEvent.VK_S)
					saveImage();
			}
		});
		
		setHalfScreenSize();
		pack();
		centerOnScreen();
		repaint();
	}
	
	/**
	 * Set the image to display.
	 * 
	 * @param image the image to display
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		menuBar.add(createFileMenu());
		menuBar.add(createViewMenu());
		
		return menuBar;
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenuItem saveItem = new JMenuItem("Save ...");
		saveItem.addActionListener(e -> saveImage());
		saveItem.setMnemonic('S');
		fileMenu.add(saveItem);
		
		fileMenu.addSeparator();
		
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(e -> System.exit(0));
		quitItem.setMnemonic('Q');
		fileMenu.add(quitItem);
		
		return fileMenu;
	}
	
	private JMenu createViewMenu() {
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');
		
		JCheckBoxMenuItem keepAspectRatioItem = new JCheckBoxMenuItem("Keep aspect ratio", DEF_KEEP_ASPECT_RATIO);
		keepAspectRatioItem.addActionListener(e -> repaint());
		keepAspectRatioItem.addActionListener(e -> this.keepAspectRatio = keepAspectRatioItem.isSelected());
		keepAspectRatioItem.setMnemonic('R');
		viewMenu.add(keepAspectRatioItem);
		
		JCheckBoxMenuItem allowEnlargeItem = new JCheckBoxMenuItem("Allow enlargement", DEF_ALLOW_ENLARGE);
		allowEnlargeItem.addActionListener(e -> repaint());
		allowEnlargeItem.addActionListener(e -> this.allowEnlarge = allowEnlargeItem.isSelected());
		allowEnlargeItem.setMnemonic('E');
		viewMenu.add(allowEnlargeItem);
		
		return viewMenu;
	}
	
	/**
	 * Initiate image saving.
	 */
	public void saveImage() {
		if(image != null)
			ImageSaving.getFileAndSaveImage(image, startFile);
	}
	
	/**
	 * Set the file to save to.
	 * 
	 * @param startFile the preferred save file
	 */
	public void setPreferredSaveFile(File startFile) {
		this.startFile = startFile;
	}
	
	/**
	 * Set the name of the file to save to.
	 * 
	 * @param fileName the preferred save file name
	 */
	public void setPreferredSaveFileName(String fileName) {
		setPreferredSaveFile(new File(fileName));
	}
	
	/**
	 * Move the window to the center of the screen.
	 */
	public void centerOnScreen() {
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2
		);
	}
	
	/**
	 * Set this window's size to be half the screen size in each dimension.
	 */
	public void setHalfScreenSize() {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2));
	}
	
	
	/**
	 * The panel displays the image.
	 * The image will be drawn as large as possible, filling the panel and being shown all at once (no cropping).
	 * 
	 * @author Lennart Meinhardt
	 */
	private class ImagePanel extends JPanel {
		@Override public void paint(Graphics g) {
			if(image == null)
				return;
			
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			
			int availableWidth = getWidth();
			int availableHeight = getHeight();

			int maxWidth = Math.min(imageWidth, availableWidth);
			int maxHeight = Math.min(imageHeight, availableHeight);
			
			if(! allowEnlarge) {
				maxWidth = Math.min(maxWidth, imageWidth);
				maxHeight = Math.min(maxHeight, imageHeight);
			}
			
			if(keepAspectRatio) {
				double ratio = 1d * imageHeight / imageWidth;
				maxHeight = (int) Math.min(maxHeight, maxWidth * ratio);
				maxWidth = (int) (maxHeight / ratio);
			}
			
			int widthToDraw = maxWidth;
			int heightToDraw = maxHeight;

			int gapX = (availableWidth - widthToDraw) / 2;
			int gapY = (availableHeight - heightToDraw) / 2;
			g.clearRect(0, 0, availableWidth, availableHeight);
			g.drawImage(image, gapX, gapY, widthToDraw, heightToDraw, null);
		}
	}
}

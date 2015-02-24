package dang.tracker;



import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class CameraDialog extends JDialog {
	private	JLabel			imageLabel;
	private CameraImage 	image;			// Robot's Camera (hand-drawn blob) Image to be displayed

    public CameraDialog(Frame owner){
		super(owner,"Camera Output",false);
		setLocationRelativeTo(owner);
		setLocation(RobotTracker.MIN_WIDTH+80, 80);

		// Create a blank image to start
		image = new CameraImage();

		setLayout(new FlowLayout());
		setLocation(owner.getX()+owner.getWidth(),owner.getY());
		add(imageLabel = new JLabel());
		imageLabel.setIcon(new ImageIcon(CameraImage.getColorImaged(Color.BLACK)));
		JButton button = new JButton("   Save  ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCurrentImage();
			}
		});
		add(button);
		button = new JButton("  Reset ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetCurrentImage();
			}
		});
		add(button);
		setResizable(false);
		setSize(176,213);
	}

	// Sets the current output image on the image panel.
	public void setOutputImage(Image image) {
		imageLabel.setIcon(new ImageIcon(image));
	}

	// Show image trace
	public void showImageTrace(java.util.ArrayList<Integer> data) {
		image.addBlob(data);
		Image img = image.getImage();
		if (img != null)
			setOutputImage(img);
	}

	// Clear the image on debug output on the main window
	public void clearImage() {
		image.fullReset();
		setOutputImage(image.getImage());
	}

	// Set the image's color
	public void setImageColor(int r, int g, int b) {
		image.setColor(r,g,b);
	}

	// Resets the current image of the image panel to black.
	private void resetCurrentImage() {
		imageLabel.setIcon(new ImageIcon(CameraImage.getColorImaged(Color.BLACK)));
	}

	// Save the current image on the image panel.
	private void saveCurrentImage() {
		try {
			ImageIcon icon = (ImageIcon)imageLabel.getIcon();
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setDialogTitle("Save current camera image");
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new ImageFileFilter());
			int returnVal = chooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (chooser.getSelectedFile().exists()) {
					if (JOptionPane.showConfirmDialog(this, "Replace existing file?") != JOptionPane.YES_OPTION)
						return;
				}
				ImageIO.write((BufferedImage)icon.getImage(), new ImageFileFilter().getExtension(chooser.getSelectedFile()),chooser.getSelectedFile());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: Unable to save camera image to.", "Error Saving Camera Image", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Filter for saving the image from the image panel.
	private class ImageFileFilter extends javax.swing.filechooser.FileFilter {
		// Returns the description of the file filters.
		public String getDescription() {
			return "Image File (jpg; jpeg; png)";
		}

		// Determine whether the given file is allowed in the filter.
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);
			if (extension != null && (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
				return true;
			} else {
			    return false;
			}
		}
		// Return the extension of the given file.
		private String getExtension(File f) {
			String ext = null;
        	String s = f.getName();
        	int i = s.lastIndexOf('.');

    	   	if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
        	}
	        return ext;
		}
	}
}
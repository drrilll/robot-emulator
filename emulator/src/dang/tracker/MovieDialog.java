package dang.tracker;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class MovieDialog extends JDialog {

	private int 			framerate, quality, snapshotCount;
	private JButton 		okButton, cancelButton;
    private JSlider 		framerateSlider, qualitySlider;
    private JTextField 		framerateText, qualityText;
    protected JProgressBar 	progressBar;
    private String			workingDirectory;

	 public MovieDialog(Frame owner, int snapshotCount, int fr, String working){
		super(owner,"Create Movie", true);

		workingDirectory = working;

		setResizable(false);
		setSize(300, 200);

		this.snapshotCount = snapshotCount;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		framerate = fr;
		quality = 80;

		// Framerate settings
		JLabel label = new JLabel("Framerate (1 - 30 fps)");
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(label, constraints);
		add(label);

		framerateSlider = new JSlider(JSlider.HORIZONTAL, 1, 30, framerate);
		framerateSlider.setPaintLabels(false);
		framerateSlider.setPaintTicks(false);
		constraints.gridx = 1; constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(framerateSlider, constraints);
		add(framerateSlider);

		framerateText = new JTextField(4);
		framerateText.setText(String.valueOf(framerateSlider.getValue()));
		framerateText.setEditable(false);
		constraints.gridx=0; constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(framerateText, constraints);
		add(framerateText);

		// Quality settings
		label = new JLabel("Quality (0 - 100%)");
		constraints.gridx = 0; constraints.gridy = 2;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(label, constraints);
		add(label);

		qualitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, quality);
		qualitySlider.setPaintLabels(false);
		qualitySlider.setPaintTicks(false);
		constraints.gridx = 1; constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(qualitySlider, constraints);
		add(qualitySlider);

		qualityText = new JTextField(4);
		qualityText.setText(String.valueOf(qualitySlider.getValue()));
		qualityText.setEditable(false);
		constraints.gridx=0; constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(qualityText, constraints);
		add(qualityText);

		// OK and DISCARD buttons
		okButton = new JButton("SAVE");
		constraints.gridx = 1; constraints.gridy = 4;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(okButton, constraints);
		add(okButton);

		cancelButton = new JButton("DISCARD");
		constraints.gridx = 2; constraints.gridy = 4;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(cancelButton, constraints);
		add(cancelButton);

		// Progress bar
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		constraints.gridx = 0; constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(0, 10, 10, 10);
		layout.setConstraints(progressBar, constraints);
		add(progressBar);

		// ActionListeners
		framerateSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
		        framerateText.setText(String.valueOf(framerateSlider.getValue()));
			    framerate = (int)framerateSlider.getValue();
			}
		});

		qualitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
		        qualityText.setText(String.valueOf(qualitySlider.getValue()));
		        quality = (int)qualitySlider.getValue();
			}
		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				okButtonClicked();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
			}});
	 }

	 public void okButtonClicked(){
		// Disable controls
		okButton.setEnabled(false);
		cancelButton.setEnabled(false);
		framerateSlider.setEnabled(false);
		qualitySlider.setEnabled(false);

		FileDialog saveDialog = new FileDialog(this, "Save Movie", FileDialog.SAVE);
		saveDialog.setDirectory(workingDirectory);
		saveDialog.setFile("TrackerMovie.avi");
		saveDialog.setVisible(true);

     	String directory = saveDialog.getDirectory();
        String filename = saveDialog.getFile();

     	if (filename != null) {
     		if (createMovie(directory + filename, framerate, ((float)quality)/100)) {
             	JOptionPane.showMessageDialog(this, "Saved movie: " + directory + filename);
             	setVisible(false);
             	dispose();
             }
             else {
             	JOptionPane.showMessageDialog(this, "Error saving movie: " + directory + filename);
             	okButton.setEnabled(true);
        		cancelButton.setEnabled(true);
        		framerateSlider.setEnabled(true);
        		qualitySlider.setEnabled(true);
             }
         }
         else {
        	// Discard video
         	setVisible(false);
         	dispose();
         }
	 }

	 public boolean createMovie(String filename, int framerate, float quality){
		 AVIOutputStream out = null;

		 File file = new File(filename);
		 AVIOutputStream.VideoFormat format = AVIOutputStream.VideoFormat.JPG;
		 try {
			 out = new AVIOutputStream(file, format);
			 out.setVideoCompressionQuality(quality);

			 out.setTimeScale(1);
			 out.setFrameRate(framerate);

			 for (int snapshotNum = 0; snapshotNum < snapshotCount; snapshotNum++){
				 out.writeFrame((BufferedImage) ImageIO.read(new File(RobotTracker.INSTALL_DIRECTORY + RobotTracker.fs +
						 "Snapshots"+RobotTracker.fs+"TrackerImage" + (String.format("%05d", snapshotNum)) + ".png")));
				 progressBar.setValue((int)(100*(double)(snapshotNum+1)/(snapshotCount)));
				 progressBar.paint(progressBar.getGraphics());	// Force redraw of updated progress bar
			 }
		 }
		 catch (Exception e){
			 System.out.println("Error creating movie ... " + e.getMessage());
			 return false;
		 }
		 finally {
			 if (out != null) {
				 try {
					 out.close();
				 }
				 catch (Exception e){
					 System.out.println("Problem closing AVIOutputStream.");
					 return false;
				 }
			 }
		 }
		 return true;
	 }
}

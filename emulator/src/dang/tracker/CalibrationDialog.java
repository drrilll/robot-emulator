package dang.tracker;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class CalibrationDialog extends JDialog {

    private TrackingPanel trackingPanel;

    private JButton 	okButton, cancelButton;
    private JSlider 	sumSlider, diffSlider, frameRateSlider;
    private JTextField 	sumText, diffText, frameRateText;
    private JCheckBox 	tagsCheckBox;

    private int 		prevSum, prevDiff, prevFrameRate, sum, diff, frameRate;
    private boolean 	showTags, prevShowTags;

    public CalibrationDialog(Frame owner, String title, boolean modal, TrackingPanel tp){
		super(owner,title,modal);
		setResizable(false);
		setSize(300, 250);

		trackingPanel = tp;

		sum = trackingPanel.sum;
		diff = trackingPanel.diff;
		frameRate = trackingPanel.getFrameRate();
		showTags = trackingPanel.showTags;
		prevSum = sum;
		prevDiff = diff;
		prevShowTags = showTags;
		prevFrameRate = frameRate;

		// Set-up GUI
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		JLabel label = new JLabel("Frame Rate (1 - 15)");
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(label, constraints);
		add(label);

		frameRateSlider = new JSlider(JSlider.HORIZONTAL, 1, 15, frameRate);
		frameRateSlider.setPaintLabels(false);
		frameRateSlider.setPaintTicks(false);
		constraints.gridx = 1; constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(frameRateSlider, constraints);
		add(frameRateSlider);

		frameRateText = new JTextField(4);
		frameRateText.setText(String.valueOf(frameRateSlider.getValue()));
		frameRateText.setEditable(false);
		constraints.gridx=0; constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(frameRateText, constraints);
		add(frameRateText);

		label = new JLabel("Max RGB sum (0 - 600)");
		constraints.gridx = 0; constraints.gridy = 2;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(label, constraints);
		add(label);

		sumSlider = new JSlider(JSlider.HORIZONTAL, 0, 600, sum);
		sumSlider.setPaintLabels(false);
		sumSlider.setPaintTicks(false);
		constraints.gridx = 1; constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(sumSlider, constraints);
		add(sumSlider);

		sumText = new JTextField(4);
		sumText.setText(String.valueOf(sumSlider.getValue()));
		sumText.setEditable(false);
		constraints.gridx=0; constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(sumText, constraints);
		add(sumText);

		label = new JLabel("Max RGB difference (0 - 255)");
		constraints.gridx = 0; constraints.gridy = 4;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(label, constraints);
		add(label);

		diffSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, diff);
		diffSlider.setPaintLabels(false);
		diffSlider.setPaintTicks(false);
		constraints.gridx = 1; constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(diffSlider, constraints);
		add(diffSlider);

		diffText = new JTextField(4);
		diffText.setText(String.valueOf(diffSlider.getValue()));
		diffText.setEditable(false);
		constraints.gridx=0; constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(diffText, constraints);
		add(diffText);

		tagsCheckBox = new JCheckBox("Temporarily Hide Tags");
		tagsCheckBox.setSelected(!showTags);
		constraints.gridx = 0; constraints.gridy = 6;
		constraints.gridwidth = 2;
		layout.setConstraints(tagsCheckBox, constraints);
		add(tagsCheckBox);

		okButton = new JButton("OK");
		constraints.gridx = 1; constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(okButton, constraints);
		add(okButton);

		cancelButton = new JButton("CANCEL");
		constraints.gridx = 2; constraints.gridy = 7;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(cancelButton, constraints);
		add(cancelButton);

		frameRateSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        frameRateText.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        frameRate = (int)source.getValue();
			        trackingPanel.setFrameRate(frameRate);
				}
			}
		});

		sumSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        sumText.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        sum = (int)source.getValue();
			        trackingPanel.sum = sum;
				}
			}
		});

		diffSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        diffText.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        diff = (int)source.getValue();
			        trackingPanel.diff = diff;
				}
			}
		});

		tagsCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trackingPanel.showTags = !tagsCheckBox.isSelected();
			}});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				okButtonClicked();
			}});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				cancelButtonClicked();
			}});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				cancelButtonClicked();
			}});
    }

	private void okButtonClicked(){
		trackingPanel.showTags = prevShowTags;
		dispose();
	}

	private void cancelButtonClicked(){
		trackingPanel.sum = prevSum;
		trackingPanel.diff = prevDiff;
		trackingPanel.showTags = prevShowTags;
		trackingPanel.setFrameRate(prevFrameRate);
		dispose();
	}

}
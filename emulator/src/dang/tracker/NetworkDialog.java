package dang.tracker;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class NetworkDialog extends JDialog {
    private JButton 		okButton;
    private	JSpinner		idBox;
    private JTextField[]	addressFields = new JTextField[3];
    private JLabel[]		labels = new JLabel[6];
    private JTextField		xField;
    private JTextField		yField;
    private JLabel			pictureLabel;
    private JCheckBox		enableBox;

	private RobotTracker	robotTracker;


    public NetworkDialog(RobotTracker owner){
		super(owner,"Multi-Tracker Network Settings",true);

		setResizable(false);
		setSize(700, 320);

		robotTracker = owner;

		GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        setLayout(layout);


		// Add the enable Checkbox
		enableBox = new JCheckBox("Perform Networked Tracking");
        constraints.gridx = 0; constraints.gridy = 0;
        constraints.gridwidth = 3; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(enableBox, constraints);
        add(enableBox);

        // Add the labels along the left side
        labels[0] = new JLabel("Station ID:");
        constraints.gridx = 0; constraints.gridy = 1;
        constraints.gridwidth = 1; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1; constraints.weighty = 0;
        layout.setConstraints(labels[0], constraints);
        add(labels[0]);
		labels[1] = new JLabel("X Offset (pixels):");
        constraints.gridy = 2;
        layout.setConstraints(labels[1], constraints);
        add(labels[1]);
		labels[2] = new JLabel("Y Offset (pixels):");
        constraints.gridy = 3;
        layout.setConstraints(labels[2], constraints);
        add(labels[2]);
		labels[3] = new JLabel("IP Address For Station 1:");
        constraints.gridy = 4;
        layout.setConstraints(labels[3], constraints);
        add(labels[3]);
		labels[4] = new JLabel("IP Address For Station 2:");
        constraints.gridy = 6;
        layout.setConstraints(labels[4], constraints);
        add(labels[4]);
		labels[5] = new JLabel("IP Address For Station 3:");
        constraints.gridy = 8;
        layout.setConstraints(labels[5], constraints);
        add(labels[5]);

		// Add the ID listbox
		idBox = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        constraints.gridx = 1; constraints.gridy = 1;
        constraints.gridwidth = 1; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(idBox, constraints);
        add(idBox);

		// Add the folder text fields
		addressFields[0] = new JTextField("134.117.28.xxx");
        constraints.gridx = 0; constraints.gridy = 5;
        constraints.gridwidth = 2; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(addressFields[0], constraints);
        add(addressFields[0]);
		addressFields[1] = new JTextField("134.117.28.xxx");
        constraints.gridx = 0; constraints.gridy = 7;
        layout.setConstraints(addressFields[1], constraints);
        add(addressFields[1]);
		addressFields[2] = new JTextField("134.117.28.xxx");
        constraints.gridx = 0; constraints.gridy = 9;
        layout.setConstraints(addressFields[2], constraints);
        add(addressFields[2]);

		// Add the x/y text fields
		xField = new JTextField("0");
		xField.setEditable(false);
        constraints.gridx = 1; constraints.gridy = 2;
        constraints.gridwidth = 1; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(xField, constraints);
        add(xField);

		yField = new JTextField("0");
		yField.setEditable(false);
		constraints.gridx = 1; constraints.gridy = 3;
        layout.setConstraints(yField, constraints);
        add(yField);

		// Add the picture label
		pictureLabel = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID0.jpg"), JLabel.LEFT);
        constraints.gridx = 2; constraints.gridy = 1;
        constraints.gridwidth = 1; constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(pictureLabel, constraints);
        add(pictureLabel);

		// Add the OK button
        okButton = new JButton("OK");
        constraints.gridx = 2; constraints.gridy = 8;
        constraints.weightx = 1; constraints.weighty = 1;
        layout.setConstraints(okButton, constraints);
        add(okButton);


        // Set the default values
        enableBox.setSelected(robotTracker.combineMultipleTrackers);
        idBox.setValue(robotTracker.stationID);
        addressFields[0].setText(robotTracker.stationAddresses[0]);
        addressFields[1].setText(robotTracker.stationAddresses[1]);
        addressFields[2].setText(robotTracker.stationAddresses[2]);
        xField.setText(""+robotTracker.trackingPanel.xOffset);
        yField.setText(""+robotTracker.trackingPanel.yOffset);

		update();

        // Now handle the changes
		idBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				robotTracker.stationID = ((SpinnerNumberModel)idBox.getModel()).getNumber().intValue();
				if (enableBox.isSelected())
 					yField.setText(""+((3 - robotTracker.stationID) * 480));
 				else
 					yField.setText("0");
				update();
			}});

		// Listener for the enable Checkbox
		enableBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robotTracker.combineMultipleTrackers = enableBox.isSelected();
				if (enableBox.isSelected())
 					yField.setText(""+((3 - robotTracker.stationID) * 480));
 				else
 					yField.setText("0");
				update();
			}});


        okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				saveAndQuit();
			}});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				saveAndQuit();
			}});

	}

	private void update() {
		//idBox.setEnabled(true);
		addressFields[0].setEnabled(enableBox.isSelected());
        addressFields[1].setEnabled(enableBox.isSelected());
        addressFields[2].setEnabled(enableBox.isSelected());
        xField.setEnabled(enableBox.isSelected());
        yField.setEnabled(enableBox.isSelected());
        for (int i=1; i<6; i++) {
        	labels[i].setEnabled(enableBox.isSelected());
        }

        if (!enableBox.isSelected()) {
        	pictureLabel.setIcon(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\IDSingle.png"));
        	//pictureLabel.setEnabled(false);
        	return;
		}

		pictureLabel.setEnabled(true);
        if (robotTracker.stationID == 1)
        	pictureLabel.setIcon(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID1BL.png"));
        else if (robotTracker.stationID == 2)
        	pictureLabel.setIcon(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID2BL.png"));
        else
        	pictureLabel.setIcon(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID3BL.png"));
        if (enableBox.isSelected())
 			yField.setText(""+((3 - robotTracker.stationID) * 480));
 		else
 			yField.setText("0");
	}

	private void saveAndQuit() {
		robotTracker.stationAddresses[0] = addressFields[0].getText();
		robotTracker.stationAddresses[1] = addressFields[1].getText();
		robotTracker.stationAddresses[2] = addressFields[2].getText();
        robotTracker.trackingPanel.xOffset = Integer.parseInt(xField.getText());
        robotTracker.trackingPanel.yOffset = Integer.parseInt(yField.getText());
        robotTracker.adjustMultiTracking();

		// Save the settings
		((RobotTracker)this.getOwner()).saveConfigFile();
		setVisible(false);
	}
}
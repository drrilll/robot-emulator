package dang.tracker;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.Vector;
import java.io.File;

public class MapDialog extends JDialog {

	private RobotTracker	mainApp;
	private File			traceFile = null;

	private JPanel			resolutionPanel, errorPanel;
    private JRadioButton[]	errorButtons;
    private JRadioButton[]	resolutionButtons;
	private JList	dataSetList;
	private	JCheckBox		showDataSet;
	private	JCheckBox		grayscale, robotShape;
	private JLabel			sampleAmountLabel, distanceLabel, angleLabel, weightLabel;
	private JTextField		distanceErrorField;
	private JTextField		angleErrorField;
	private JSlider			weightSlider;
	private JButton			generateMapButton;
	private int				selectedDataSet;

	private	DocumentListener		angleFieldListener, distanceFieldListener;
	private ListSelectionListener	listListener;

    public MapDialog(RobotTracker owner){
		super(owner, "Map Settings", false);

		setLocationRelativeTo(owner);
		setLocation(TrackingPanel.WIDTH+TrackingPanel.MAP_BORDER_SIZE-20, TrackingPanel.MAP_BORDER_SIZE);
		mainApp = owner;
		setLayout(null);
		selectedDataSet = -1;


		errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.Y_AXIS));
		errorPanel.setSize(180, 150);
		errorPanel.setLocation(10, 10);
		errorPanel.setBorder(new TitledBorder("Error Settings"));
		add(errorPanel);
        ButtonGroup group = new ButtonGroup();
        errorButtons = new JRadioButton[5];
        errorButtons[0] = new JRadioButton("Raw Data", false);
        errorButtons[1]  = new JRadioButton("Fixed Error", false);
        errorButtons[2]  = new JRadioButton("Gaussian Angle Only", false);
        errorButtons[3] = new JRadioButton("Gaussian Distance Only", false);
        errorButtons[4] = new JRadioButton("Full Gaussian", true);
		for (int i=0; i<5; i++) {
			group.add(errorButtons[i]);
			errorPanel.add(errorButtons[i]);
			if (mainApp.errorSetting == i)
				errorButtons[i].setSelected(true);
			errorButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0; i<5; i++)
						if (e.getSource() == errorButtons[i])
							mainApp.errorSetting = i;
					update();
					mainApp.reDrawMap();
			}});
		}
		resolutionPanel = new JPanel();
		resolutionPanel.setLayout(new BoxLayout(resolutionPanel,BoxLayout.Y_AXIS));
		resolutionPanel.setSize(120, 150);
		resolutionPanel.setLocation(200, 10);
		resolutionPanel.setBorder(new TitledBorder("Resolution"));
		add(resolutionPanel);
        group = new ButtonGroup();
        resolutionButtons = new JRadioButton[5];
        resolutionButtons[0] = new JRadioButton("1x 6-sigma", false);
        resolutionButtons[1]  = new JRadioButton("2x 6-sigma", false);
        resolutionButtons[2]  = new JRadioButton("3x 6-sigma", false);
        resolutionButtons[3] = new JRadioButton("5x 6-sigma", false);
        resolutionButtons[4] = new JRadioButton("10x 6-sigma", true);
		for (int i=0; i<5; i++) {
			group.add(resolutionButtons[i]);
			resolutionPanel.add(resolutionButtons[i]);
			if (mainApp.resolution == i)
				resolutionButtons[i].setSelected(true);
			resolutionButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0; i<5; i++)
						if (e.getSource() == resolutionButtons[i])
							mainApp.resolution = i;
					mainApp.reDrawMap();
			}});
		}

		grayscale = new JCheckBox("Grayscale");
		grayscale.setLocation(200, 170);
		grayscale.setSize(100, 20);
		grayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map.COLOR = !grayscale.isSelected();
				mainApp.reColorMap();
		}});
		add(grayscale);

		robotShape = new JCheckBox("Incorporate Robot's Shape");
		robotShape.setLocation(10, 170);
		robotShape.setSize(180, 20);
		robotShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSet.incorporateShape = robotShape.isSelected();
				mainApp.reDrawMap();
		}});
		add(robotShape);


		JPanel aPanel = new JPanel();
		aPanel.setLayout(null);
		aPanel.setLocation(10, 200);
		aPanel.setSize(310, 200);
		aPanel.setBorder(new TitledBorder("Data Sets"));
		add(aPanel);


		JButton loadButton = new JButton("Load Trace File");
		loadButton.setLocation(10, 20);
		loadButton.setSize(130, 20);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLoadButton();
		}});
		aPanel.add(loadButton);

		dataSetList = new JList();
		dataSetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataSetList.addListSelectionListener(listListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedDataSet = dataSetList.getSelectedIndex();
				update();
			}});

		JScrollPane	sp = new JScrollPane(dataSetList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setLocation(10, 50);
		sp.setSize(130, 120);
		aPanel.add(sp);

		sampleAmountLabel = new JLabel("SIZE = 2378 samples");
		sampleAmountLabel.setLocation(10, 173);
		sampleAmountLabel.setSize(130, 20);
		aPanel.add(sampleAmountLabel);


		showDataSet = new JCheckBox("Show Data Set");
		showDataSet.setLocation(147, 20);
		showDataSet.setSize(150, 20);
		showDataSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainApp.dataSets[selectedDataSet].enabled = showDataSet.isSelected();
				update();
		}});
		aPanel.add(showDataSet);

		distanceLabel = new JLabel("Distance Error (%)");
		distanceLabel.setLocation(150, 50);
		distanceLabel.setSize(150, 20);
		aPanel.add(distanceLabel);

		distanceErrorField = new JTextField("10");
		distanceErrorField.setLocation(270, 52);
		distanceErrorField.setSize(30, 20);
		distanceErrorField.getDocument().addDocumentListener(distanceFieldListener =
		    new DocumentListener() {
		        public void changedUpdate(DocumentEvent theEvent) { makeChange(); }
		        public void insertUpdate(DocumentEvent theEvent) { makeChange(); }
		        public void removeUpdate(DocumentEvent theEvent) { makeChange(); }
		        private void makeChange() {
		        	if (distanceErrorField.getText().length() > 0)
		            	mainApp.dataSets[selectedDataSet].distanceError = Double.parseDouble(distanceErrorField.getText())/ 100.0;
		        }
		    }
		);
		aPanel.add(distanceErrorField);

		angleLabel = new JLabel("Angular Error (deg)");
		angleLabel.setLocation(150, 80);
		angleLabel.setSize(150, 20);
		aPanel.add(angleLabel);

		angleErrorField = new JTextField("30");
		angleErrorField.setLocation(270, 82);
		angleErrorField.setSize(30, 20);
		angleErrorField.getDocument().addDocumentListener(angleFieldListener =
		    new DocumentListener() {
		        public void changedUpdate(DocumentEvent theEvent) { makeChange(); }
		        public void insertUpdate(DocumentEvent theEvent) { makeChange(); }
		        public void removeUpdate(DocumentEvent theEvent) { makeChange(); }
		        private void makeChange() {
		        	if (angleErrorField.getText().length() > 0)
		            	mainApp.dataSets[selectedDataSet].angularResolution = Double.parseDouble(angleErrorField.getText());
		        }
		    }
		);
		aPanel.add(angleErrorField);

		weightLabel = new JLabel("Relative Weight (%)");
		weightLabel.setLocation(170, 120);
		weightLabel.setSize(150, 20);
		aPanel.add(weightLabel);

		weightSlider = new JSlider(0, 100, 50);
		weightSlider.setMinorTickSpacing(5);
		weightSlider.setMajorTickSpacing(25);
		weightSlider.setPaintTicks(true);
		weightSlider.setPaintLabels(true);
		weightSlider.setLocation(150, 140);
		weightSlider.setSize(150, 50);
		weightSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
			    if (!source.getValueIsAdjusting()) {
			    	if (selectedDataSet >= 0) {
			    		mainApp.dataSets[selectedDataSet].weight = weightSlider.getValue();
			    	}
					mainApp.update();
					mainApp.reWeightMap();
			    }
			}
		});
		aPanel.add(weightSlider);

		generateMapButton = new JButton("Generate Map");
		generateMapButton.setLocation(10, 410);
		generateMapButton.setSize(310, 30);
		generateMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        	 	mainApp.reDrawMap();
		}});
		this.add(generateMapButton);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


		setResizable(false);
		setSize(336,478);
		update();
	}

	public void update() {
		angleErrorField.getDocument().removeDocumentListener(angleFieldListener);
		distanceErrorField.getDocument().removeDocumentListener(distanceFieldListener);
		dataSetList.removeListSelectionListener(listListener);

		// Update the list with the latest data set labels
		String[] listItems = new String[mainApp.dataSets.length];
		for (int i=0; i< listItems.length; i++)
			listItems[i] = mainApp.dataSets[i].description;
		dataSetList.setListData((String[])listItems);
		dataSetList.setSelectedIndex(selectedDataSet);

		// Enable to disable the error setting panel accordingly
		resolutionPanel.setEnabled(mainApp.errorSetting > 1);
		for (int i=0; i<5; i++)
			resolutionButtons[i].setEnabled(mainApp.errorSetting > 1);

		// Enable to disable the resolution panel accordingly
		errorPanel.setEnabled(mainApp.dataSets.length > 0);
		generateMapButton.setEnabled(mainApp.dataSets.length > 0);
		for (int i=0; i<5; i++)
			errorButtons[i].setEnabled(mainApp.dataSets.length > 0);

		grayscale.setEnabled(mainApp.dataSets.length > 0);
		robotShape.setEnabled(mainApp.dataSets.length > 0);

		if (selectedDataSet >= 0) {
			showDataSet.setEnabled(true);
			showDataSet.setSelected(mainApp.dataSets[selectedDataSet].enabled);
			sampleAmountLabel.setText("SIZE = " + mainApp.dataSets[selectedDataSet].getDataSize() + " samples");
			sampleAmountLabel.setEnabled(true);
			distanceErrorField.setEnabled(true);
			distanceErrorField.setText(""+(int)(mainApp.dataSets[selectedDataSet].distanceError * 100));
			angleErrorField.setEnabled(true);
			angleErrorField.setText(""+mainApp.dataSets[selectedDataSet].angularResolution);
			weightSlider.setEnabled(true);
			weightSlider.setValue((int)mainApp.dataSets[selectedDataSet].weight);
			distanceLabel.setEnabled(true);
			angleLabel.setEnabled(true);
			weightLabel.setEnabled(true);
		}
		else {
			showDataSet.setEnabled(false);
			distanceErrorField.setEnabled(false);
			distanceErrorField.setText("");
			angleErrorField.setEnabled(false);
			angleErrorField.setText("");
			weightSlider.setEnabled(false);
			weightSlider.setValue(50);
			distanceLabel.setEnabled(false);
			angleLabel.setEnabled(false);
			weightLabel.setEnabled(false);
			sampleAmountLabel.setText("");
			sampleAmountLabel.setEnabled(false);
		}

		dataSetList.addListSelectionListener(listListener);
		angleErrorField.getDocument().addDocumentListener(angleFieldListener);
		distanceErrorField.getDocument().addDocumentListener(distanceFieldListener);
	}

	// Load up a trace file
	private void handleLoadButton() {
		// Open up a file chooser to get the trace file.  Assume trace file has
		// poses as well as exactly 2 sets of data (DIRRS ranges and SONAR ranges)
    	JFileChooser chooser = new JFileChooser(mainApp.workingDirectory);
    	TraceFileFilter filter = new TraceFileFilter();
    	chooser.setFileFilter(filter);
    	int returnVal = chooser.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
			traceFile = chooser.getSelectedFile();

			// Now open the files and read the data sets
			mainApp.dataSets = DataSet.loadSetsFromFile(traceFile, mainApp.trackingPanel.WIDTH, mainApp.trackingPanel.HEIGHT);

			if (mainApp.dataSets == null) {
				System.err.println("Error reading trace file");
				mainApp.dataSets = new DataSet[0];
			}
			else {
				update();
				mainApp.update();
				/*reDrawMap();*/
			}
    	}
	}


	public static void main(String args[]) {
		new MapDialog(null).setVisible(true);
	}
}
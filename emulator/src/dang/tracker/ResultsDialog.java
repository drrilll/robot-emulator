package dang.tracker;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Vector;

public class ResultsDialog extends JDialog {

    private RobotTracker	robotTracker;
    private TrackingPanel	trackingPanel;

    private JButton 		okButton;
    private	JTextField		rateField;
    private	JSlider			rateSlider;
    private JLabel[]		tagIDLabels;
    private JLabel[]		tagImages;
    private JLabel[]		tagLocLabels;
    private JLabel[]		tagAngleLabels;
    private JCheckBox		snapshotsCheckBox;
    private JRadioButton	bottomLeftOriginRadioButton;
    private JRadioButton	topLeftOriginRadioButton;


    public ResultsDialog(RobotTracker owner, TrackingPanel tp){
		super(owner,"Tracking Results",false);
		setLocationRelativeTo(owner);
		setLocation(10, RobotTracker.MIN_HEIGHT+10);
		setResizable(false);
		setSize(680, 240);

        trackingPanel = tp;
        robotTracker = owner;
        tagIDLabels = new JLabel[8];
        tagImages = new JLabel[8];
        tagLocLabels = new JLabel[8];
        tagAngleLabels = new JLabel[8];


		GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);

		// Common gridbag constraints
        constraints.gridwidth = 1; constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0; constraints.weighty = 0;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Add the first row of labels
		constraints.gridy = 0;
		for (int i=0; i<8; i++) {
			tagIDLabels[i] = new JLabel(String.valueOf(i), JLabel.CENTER);
			constraints.gridx = i+1;
        	layout.setConstraints(tagIDLabels[i], constraints);
        	add(tagIDLabels[i]);
		}

		// Add the 2nd row of labels
		constraints.gridy = 1;
        constraints.weightx = 0; constraints.weighty = 0;
		tagImages[0] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID0.png"), JLabel.LEFT);
		constraints.gridx = 1;
        layout.setConstraints(tagImages[0], constraints);
        add(tagImages[0]);

		tagImages[1] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID1.png"), JLabel.LEFT);
		constraints.gridx = 2;
        layout.setConstraints(tagImages[1], constraints);
        add(tagImages[1]);

		tagImages[2] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID2.png"), JLabel.LEFT);
		constraints.gridx = 3;
        layout.setConstraints(tagImages[2], constraints);
        add(tagImages[2]);

		tagImages[3] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID3.png"), JLabel.LEFT);
		constraints.gridx = 4;
        layout.setConstraints(tagImages[3], constraints);
        add(tagImages[3]);

		tagImages[4] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID4.png"), JLabel.LEFT);
		constraints.gridx = 5;
        layout.setConstraints(tagImages[4], constraints);
        add(tagImages[4]);

		tagImages[5] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID5.png"), JLabel.LEFT);
		constraints.gridx = 6;
        layout.setConstraints(tagImages[5], constraints);
        add(tagImages[5]);

        tagImages[6] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID6.png"), JLabel.LEFT);
		constraints.gridx = 7;
        layout.setConstraints(tagImages[6], constraints);
        add(tagImages[6]);

        tagImages[7] = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\ID7.png"), JLabel.LEFT);
		constraints.gridx = 8;
        layout.setConstraints(tagImages[7], constraints);
        add(tagImages[7]);


		for (int i=0; i<=7; i++) {
			tagImages[i].setSize(new Dimension(50, 50));
			tagImages[i].setPreferredSize(new Dimension(50, 50));
			tagImages[i].setMinimumSize(new Dimension(50, 50));
			tagImages[i].setMaximumSize(new Dimension(50, 50));
		}

		// Add the 3rd row of labels
		constraints.gridy = 2;
		for (int i=0; i<8; i++) {
			tagLocLabels[i] = new JLabel("------------");
        	constraints.gridx = i+1;
        	layout.setConstraints(tagLocLabels[i], constraints);
        	add(tagLocLabels[i]);
			tagLocLabels[i].setSize(new Dimension(60, 20));
			tagLocLabels[i].setPreferredSize(new Dimension(60, 20));
			tagLocLabels[i].setMinimumSize(new Dimension(60, 20));
			tagLocLabels[i].setMaximumSize(new Dimension(60, 20));
		}

		// Add the 4TH row of labels
		constraints.gridy = 3;
		for (int i=0; i<8; i++) {
			tagAngleLabels[i] = new JLabel("------------");
        	constraints.gridx = i+1;
        	layout.setConstraints(tagAngleLabels[i], constraints);
        	add(tagAngleLabels[i]);
			tagAngleLabels[i].setSize(new Dimension(60, 20));
			tagAngleLabels[i].setPreferredSize(new Dimension(60, 20));
			tagAngleLabels[i].setMinimumSize(new Dimension(60, 20));
			tagAngleLabels[i].setMaximumSize(new Dimension(60, 20));
		}

		// Add the left column of labels
		constraints.gridx = 0;
        constraints.weightx = 0; constraints.weighty = 0;
		JLabel label = new JLabel("Robot ID:");
        constraints.gridy = 0;
        layout.setConstraints(label, constraints);
        add(label);
		label = new JLabel("Tag:");
        constraints.gridy = 1;
        layout.setConstraints(label, constraints);
        add(label);
		label = new JLabel("Location:");
        constraints.gridy = 2;
        layout.setConstraints(label, constraints);
        add(label);
		label = new JLabel("Angle(deg):");
        constraints.gridy = 3;
        layout.setConstraints(label, constraints);
        add(label);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) { dispose(); }});
	}

	public void update(Pose[] poses, Pose[][] posesFromServers) {
		for (int i=0; i<8; i++) {
			if ((posesFromServers[0][i] == null) && (posesFromServers[1][i] == null) && (posesFromServers[2][i] == null)) {
				tagLocLabels[i].setText("------------");
        		tagAngleLabels[i].setText("------------");
        		tagLocLabels[i].setForeground(Color.LIGHT_GRAY);
				tagAngleLabels[i].setForeground(Color.LIGHT_GRAY);
			}
			else {
				Pose pose = null;
				for (int s=0; s<3; s++) {
					if (posesFromServers[s][i] != null)
						pose = posesFromServers[s][i];
				}
				if (pose != null) {
					tagLocLabels[i].setForeground(Color.RED);
					tagAngleLabels[i].setForeground(Color.RED);
					tagLocLabels[i].setText("(" + (pose.x) + "," + (pose.y) + ")");
	        		tagAngleLabels[i].setText("" + pose.angle);
				}
			}
		}
		for (int i=0; i<8; i++) {
			if (poses[i] != null) {
				tagLocLabels[i].setForeground(Color.BLACK);
				tagAngleLabels[i].setForeground(Color.BLACK);
				tagLocLabels[i].setText("(" + (poses[i].x) + "," + (poses[i].y) + ")");
        		tagAngleLabels[i].setText("" + poses[i].angle);
			}
		}
	}
	
	public void update(Pose[] poses) {
		for (int i=0; i<8; i++) {
				tagLocLabels[i].setText("------------");
        		tagAngleLabels[i].setText("------------");
        		tagLocLabels[i].setForeground(Color.LIGHT_GRAY);
				tagAngleLabels[i].setForeground(Color.LIGHT_GRAY);
			
		}
		for (int i=0; i<8; i++) {
			if (poses[i] != null) {
				tagLocLabels[i].setForeground(Color.BLACK);
				tagAngleLabels[i].setForeground(Color.BLACK);
				tagLocLabels[i].setText("(" + (poses[i].x) + "," + (poses[i].y) + ")");
        		tagAngleLabels[i].setText("" + poses[i].angle);
			}
		}
	}
}
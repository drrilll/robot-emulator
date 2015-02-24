package dang.tracker;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dang.program.Debug;
import dang.robot.Robot;

public class RobotSpeedDialog extends JDialog{
	JSlider robotSpeed;
	JTextField rSpeed;
	int speed, lower, upper;
	RobotTracker owner;
	Robot robot;
	
	public RobotSpeedDialog(final RobotTracker owner){
		super(owner,"Robot Speed",false);
		this.owner = owner;
		robot = owner.getRobot();
		setSize (330,150);
		JButton OK, cancel;
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		setLocation(owner.getX()+owner.getWidth(),owner.getY());
		
		lower = 1;
		upper = 100;
		speed = robot.getSpeed();
		
		//x label
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		JLabel label = new JLabel("Robot speed ("+lower+" - "+ upper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//x position
		robotSpeed = new JSlider(JSlider.HORIZONTAL, lower, upper,speed);
		constraints.gridx = 1; constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(robotSpeed, constraints);
		add(robotSpeed);

		//x text
		rSpeed = new JTextField(5);
		rSpeed.setText(String.valueOf(robotSpeed.getValue()));
		rSpeed.setEditable(false);
		constraints.gridx=0; constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(rSpeed, constraints);
		add(rSpeed);

		OK = new JButton("OK");
		constraints.gridx = 1; constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(OK, constraints);
		add(OK);

		cancel= new JButton("CANCEL");
		constraints.gridx = 2; constraints.gridy = 6;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(cancel, constraints);
		add(cancel);
		
		//listeners
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				OKButtonClicked();
			}
			
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonClicked();
			}});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				cancelButtonClicked();
			}});
		
		robotSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				rSpeed.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
					speed = source.getValue();
				}
			}
		});
		
		setVisible(true);
	}

	public void OKButtonClicked(){
		//Debug.debug("Setting speed at: " + speed, "speed");
		robot.setSpeed(speed);
		owner.update();
		dispose();
	}
	
	public void cancelButtonClicked(){
		dispose();
	}
}

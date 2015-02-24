package dang.tracker;



import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dang.program.Debug;
import dang.robot.Robot;
import dang.tracker.objects.Environment;

public class RobotPositionDialog extends JDialog {
	JSlider xpos, ypos, angle;
	JTextField xtext, ytext, atext;
	double oldx, oldy, oldangle;
	int x,y,a;
	int xupper, xlower, yupper, ylower;
	RobotTracker owner;
	Robot robot;
	JLabel message;
	boolean startPosition = false;
	boolean disable = true;
	public RobotPositionDialog(final RobotTracker owner, final boolean startPosition){
		super(owner,"Robot Position",false);
		this.startPosition = startPosition;
		this.owner = owner;
		robot = owner.getRobot();
		oldx = robot.getX();
		oldy = robot.getY();
		oldangle = robot.getAngle();
		disable = false;
		setSize (330,250);
		setLocation(owner.getX()+owner.getWidth(),owner.getY());

		JButton OK, cancel;
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		if(startPosition){
			x = robot.startX;
			y = 480-robot.startY;
			a = (int) phaseShift(Math.toDegrees(robot.startAngle));
		}else{
			x = (int)robot.getX();
			y = 480-(int)robot.getY();
			a = (int) phaseShift(Math.toDegrees(robot.getAngle()));
		}
		int offset = robot.getHeight()/2;
		int wallwidth = (int)Environment.WALLWIDTH;
		xlower = wallwidth+offset;
		xupper = (int)(Environment.LENGTH*3)-offset- wallwidth;

		//x label
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		JLabel label = new JLabel("Robot X ("+xlower+" - "+ xupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//x position
		if (x<xlower)x=xlower;
		if (x>xupper)x=xupper;
		xpos = new JSlider(JSlider.HORIZONTAL, xlower, xupper,x);
		constraints.gridx = 1; constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(xpos, constraints);
		add(xpos);

		//x text
		xtext = new JTextField(5);
		xtext.setText(String.valueOf(xpos.getValue()));
		xtext.setEditable(false);
		constraints.gridx=0; constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(xtext, constraints);
		add(xtext);

		//starts to get confusing here. Don't look too closely
		yupper = (int)(Environment.WIDTH*3)-offset- wallwidth/2;
		ylower = wallwidth+offset;
		//y = upper/2;

		//y label
		constraints.gridx = 0; constraints.gridy = 2;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel("Robot Y ("+ylower+" - "+ yupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//y position
		if (y<ylower)y=ylower;
		if (y>yupper)y=yupper;
		ypos = new JSlider(JSlider.HORIZONTAL, ylower, yupper,y);
		constraints.gridx = 1; constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(ypos, constraints);
		add(ypos);

		//y text
		ytext = new JTextField(5);
		ytext.setText(String.valueOf(ypos.getValue()));
		ytext.setEditable(false);
		constraints.gridx=0; constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(ytext, constraints);
		add(ytext);
		
		int lower = 0;
		int upper = 360;

		//angle label
		constraints.gridx = 0; constraints.gridy = 4;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel("Robot Angle ("+lower+" - "+ upper+  "):");
		layout.setConstraints(label, constraints);
		add(label);

		//angle position
		//Debug.debug("angle :" + a, "angle");
		angle = new JSlider(JSlider.HORIZONTAL, lower, upper,a);
		constraints.gridx = 1; constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(angle, constraints);
		add(angle);

		//angle text
		atext = new JTextField(5);
		atext.setText(String.valueOf(angle.getValue()));
		atext.setEditable(false);
		constraints.gridx=0; constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(atext, constraints);
		add(atext);
		
		//OK and Cancel buttons
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
		
		//slider listeners
		xpos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        xtext.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        x = (int)source.getValue();
			        updateRobotPosition();
				}
			}
		});
		
		ypos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        ytext.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        y = (int)source.getValue();
			        updateRobotPosition();
				}
			}
		});
		
		angle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        atext.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
			        a = (int)source.getValue();		        
			        updateRobotPosition();	        
				}
			}
		});
		
		setVisible(true);
	}
	
	private void OKButtonClicked(){
		Robot robot = owner.getRobot();
		if (startPosition){

			robot.startX = x;
			//y- axis is inverted. Guaranteed an uncaught bug somewhere
			//because of it
			robot.startY = 480 - y;
			//we have to do a phase shift, since the y axis is
			//inverted. Sigh.
			robot.startAngle = Math.toRadians(phaseShift(a));
			owner.update();

		}
		disable = true;
		dispose();

	}

	public void cancelButtonClicked(){
		if(!startPosition){
			robot.setX(oldx);
			robot.setY(oldy);
			robot.setAngle(oldangle);
			owner.trackingPanel.processImage();
		}
		dispose();
	}
	
	private double phaseShift(double angle){
		return 360 - angle;
	}

	public void mousePosition(Point point) {
		// TODO Auto-generated method stub
		int x = point.x;
		int y = point.y;
		if (x>xupper) x = xupper;
		if (x<xlower) x = xlower;
		if (y>yupper) y = yupper;
		if (y<ylower) y = ylower;

		xpos.setValue(x);
		ypos.setValue(y);
		xtext.setText(Integer.toString(x));
		ytext.setText(Integer.toString(y));
	}
	
	private void updateRobotPosition(){
		if ((!disable)&&(!startPosition)){
			robot.setX(x);
			robot.setY(480-y);
			robot.setAngle(Math.toRadians(phaseShift(a)));
			owner.trackingPanel.processImage();
		}
	}

}

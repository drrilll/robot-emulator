package dang.tracker;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
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

import dang.tracker.objects.Block;
import dang.tracker.objects.Environment;
import dang.tracker.objects.FlowerPot;
import dang.tracker.objects.Obstacle;

public class AddObstacleDialog extends JDialog {
	
	public static final int FLOWERPOT = -1;
	public static final int WALL = -2;
	public static final int BLOCK = -3;
	
	RobotTracker owner;
	Environment environment;
	JTextField xtext, ytext;
	JSlider xpos, ypos;
	JButton OK, cancel;
	int x,y,type,offset=0;
	int xupper,xlower, yupper, ylower;
	Obstacle ob;
	
	boolean disable = true;
	
	public AddObstacleDialog (RobotTracker owner, String title, int type){
		super(owner, "Add "+title);
		this.owner = owner;
		this.type = type;
		environment = owner.getRobot().getEnvironment();
		disable = false;
		
		//set up local variables
		switch(type){
		case FLOWERPOT:
			//we are going to assume that these are placed with respect to their center
			//not the top left corner as before. Keeps the code more consistent
			offset = FlowerPot.DIAMETER/2;
			ob = new FlowerPot();
			break;
		case BLOCK:
			offset = Block.DIAMETER/2;
			ob = new Block();
			break;
		case WALL:
			/*
			 * This is a little complicated
			 * I beleive what will happen is the offset will be determined by the bounding rectangle
			 * but this will likely need to be calculated on completion, because initially we know
			 * nothing of the wall
			 */
			//offset = Wall. 
			break;
			
		}
		
		//set up GUI
		setSize(330,250);
		setLocation(owner.getX()+owner.getWidth(),owner.getY());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		//set upper and lower bounds for slider
		int wallwidth = (int)Environment.WALLWIDTH;
		xlower = wallwidth+offset;
		xupper = (int)(Environment.LENGTH*3)-offset- wallwidth;
		x = xupper/2;
		
		//x label
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		JLabel label = new JLabel(title + " X ("+xlower+" - "+ xupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);
		
		//x position
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
		
		//the y-axis is inverted
		ylower = 480-((int)(Environment.WIDTH*3)-offset- (wallwidth/2));
		yupper = 480-(wallwidth+offset);
		y = yupper/2;
		
		
		//y label
		constraints.gridx = 0; constraints.gridy = 2;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel(title + " Y ("+ylower+" - "+ yupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);
		
		//y position
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
		
		//OK button
		OK = new JButton("OK");
		constraints.gridx = 1; constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(OK, constraints);
		add(OK);
		
		//Cancel button
		cancel = new JButton("Cancel");
		constraints.gridx = 2; constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(10, 25, 10, 10);
		layout.setConstraints(cancel, constraints);
		add(cancel);
		setVisible(true);

		//slider listeners
		xpos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				xtext.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
					x = (int)source.getValue();
					updateObstaclePosition();
				}
			}
		});

		ypos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				ytext.setText(String.valueOf(source.getValue()));
				if (!source.getValueIsAdjusting()){
					y = (int)source.getValue();
					updateObstaclePosition();
				}
			}
		});
		
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
		
		environment.addObstacle(ob);
		updateObstaclePosition();
	}
	
	public void OKButtonClicked(){
		//Obstacle obstacle;
		owner.trackingPanel.processImage();
		disable = true;
		dispose();
	}
	
	public void cancelButtonClicked(){
		environment.getObstacles().remove(ob);
		owner.trackingPanel.processImage();
		disable = true;
		dispose();
	}

	/**
	 * A callback method. If the user clicks the mouse on the screen
	 * and this dialog is running, we update the position of the 
	 * obstacle accordingly. We need the flag because even after you
	 * dispose of the dialog, the instance of it is still in memory. 
	 * Neat huh? So the obstacle will continue to move every time you
	 * click the screen even after you have closed the dialog.
	 * @param point
	 */
	public void mousePosition(Point point) {
		// TODO Auto-generated method stub
		if (!disable){
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
			this.x=x;
			this.y=y;
			//does this belong here? What method is this?
			updateObstaclePosition();
		}
	}
	
	public void updateObstaclePosition(){
		ob.setX(x);
		ob.setY(480-y);
		owner.trackingPanel.processImage();
	}
}

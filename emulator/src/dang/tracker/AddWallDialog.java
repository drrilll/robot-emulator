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

import dang.tracker.objects.Environment;
import dang.tracker.objects.Obstacle;
import dang.tracker.objects.Wall;

public class AddWallDialog extends JDialog {

	RobotTracker owner;
	Environment environment;
	JTextField x1text, x2text, y1text, y2text;
	JSlider x1pos, x2pos, y1pos, y2pos;
	JButton OK, cancel;
	int x1, x2, y1, y2;
	int xupper,xlower, yupper, ylower;
	Wall ob;

	boolean disable = true;

	public AddWallDialog(RobotTracker owner){
		this.owner= owner;
		environment = owner.getRobot().getEnvironment();
		disable = false;

		setSize(330,300);
		setLocation(owner.getX()+owner.getWidth(),owner.getY());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		int wallwidth = (int)Environment.WALLWIDTH;
		xlower = wallwidth;
		xupper = (int)(Environment.LENGTH*3)- wallwidth;
		x1 = xupper/4;
		x2 = xupper/2;

		//x1 label
		constraints.gridx = 0; constraints.gridy = 0;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		JLabel label = new JLabel("Wall x1 ("+xlower+" - "+ xupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//x1 position
		x1pos = new JSlider(JSlider.HORIZONTAL, xlower, xupper,x1);
		constraints.gridx = 1; constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(x1pos, constraints);
		add(x1pos);

		//x1 text
		x1text = new JTextField(5);
		x1text.setText(String.valueOf(x1pos.getValue()));
		x1text.setEditable(false);
		constraints.gridx=0; constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(x1text, constraints);
		add(x1text);

		//the y-axis is inverted
		ylower = 480-((int)(Environment.WIDTH*3)-(wallwidth/2));
		yupper = 480-(wallwidth);
		y1 = yupper/4;
		y2 = yupper/2;


		//y1 label
		constraints.gridx = 0; constraints.gridy = 2;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel("Wall y1 ("+ylower+" - "+ yupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//y1 position
		y1pos = new JSlider(JSlider.HORIZONTAL, ylower, yupper,y1);
		constraints.gridx = 1; constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(y1pos, constraints);
		add(y1pos);

		//y1 text
		y1text = new JTextField(5);
		y1text.setText(String.valueOf(y1pos.getValue()));
		y1text.setEditable(false);
		constraints.gridx=0; constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(y1text, constraints);
		add(y1text);

		//x2
		//x2 label
		constraints.gridx = 0; constraints.gridy = 4;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel("Wall x2 ("+xlower+" - "+ xupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//x2 position
		x2pos = new JSlider(JSlider.HORIZONTAL, xlower, xupper,x2);
		constraints.gridx = 1; constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(x2pos, constraints);
		add(x2pos);

		//x2 text
		x2text = new JTextField(5);
		x2text.setText(String.valueOf(x1pos.getValue()));
		x2text.setEditable(false);
		constraints.gridx=0; constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(x2text, constraints);
		add(x2text);

		//y2
		//y2 label
		constraints.gridx = 0; constraints.gridy = 6;
		constraints.gridwidth = 3; constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1; constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		label = new JLabel("Wall y2 ("+ylower+" - "+ yupper+  ")  :");
		layout.setConstraints(label, constraints);
		add(label);

		//y2 position
		y2pos = new JSlider(JSlider.HORIZONTAL, ylower, yupper,y2);
		constraints.gridx = 1; constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 0, 10);
		layout.setConstraints(y2pos, constraints);
		add(y2pos);

		//y2 text
		y2text = new JTextField(5);
		y2text.setText(String.valueOf(y2pos.getValue()));
		y2text.setEditable(false);
		constraints.gridx=0; constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 10, 0, 0);
		layout.setConstraints(y2text, constraints);
		add(y2text);
		
		
	//OK button
			OK = new JButton("OK");
			constraints.gridx = 1; constraints.gridy = 9;
			constraints.gridwidth = 1;
			constraints.insets = new Insets(10, 25, 10, 10);
			layout.setConstraints(OK, constraints);
			add(OK);
			
			//Cancel button
			cancel = new JButton("Cancel");
			constraints.gridx = 2; constraints.gridy = 9;
			constraints.gridwidth = 1;
			constraints.insets = new Insets(10, 25, 10, 10);
			layout.setConstraints(cancel, constraints);
			add(cancel);
			setVisible(true);

			//slider listeners
			x1pos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider)e.getSource();
					x1text.setText(String.valueOf(source.getValue()));
					if (!source.getValueIsAdjusting()){
						x1 = (int)source.getValue();
						updateObstaclePosition();
					}
				}
			});

			y1pos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider)e.getSource();
					y1text.setText(String.valueOf(source.getValue()));
					if (!source.getValueIsAdjusting()){
						y1 = (int)source.getValue();
						updateObstaclePosition();
					}
				}
			});
			
			//slider listeners
			x2pos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider)e.getSource();
					x2text.setText(String.valueOf(source.getValue()));
					if (!source.getValueIsAdjusting()){
						x2 = (int)source.getValue();
						updateObstaclePosition();
					}
				}
			});

			y2pos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider)e.getSource();
					y2text.setText(String.valueOf(source.getValue()));
					if (!source.getValueIsAdjusting()){
						y2 = (int)source.getValue();
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
			
			ob = new Wall(x1,y1,x2,y2);
			environment.addObstacle(ob);
			updateObstaclePosition();
			setVisible(true);
	
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

		public void updateObstaclePosition(){
			ob.setLine(x1, 480-y1, x2, 480-y2);	
			owner.trackingPanel.processImage();
		}
}

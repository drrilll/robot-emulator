package dang.tracker;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;

import dang.tracker.objects.Environment;
import dang.tracker.objects.Obstacle;

public class RemoveObstacleDialog extends JDialog{
	
	RobotTracker owner;
	Environment environment;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	JCheckBoxMenuItem[] items;

	public RemoveObstacleDialog(RobotTracker owner){
		super(owner, "Remove Item");
		this.owner = owner;
		environment = owner.getRobot().getEnvironment();
		obstacles = environment.getObstacles();
		int size = obstacles.size();
		setSize(330, 80 + 30*size);
		setLocation(owner.getX()+owner.getWidth(),owner.getY());
		
		GridLayout layout = new GridLayout(size+2,3,5,5);
		setLayout(layout);
		add(new JLabel("Item"));
		add(new JLabel("X position"));
		add(new JLabel("Y position"));
		items = new JCheckBoxMenuItem[size];
		Obstacle o;
		for (int i = 0; i < size; i ++){
			o = obstacles.get(i);
			String name = "unknown";
			switch(o.getType()){
			case -1: name =      "Wall"; 	break;
			case -2: name = "Flowerpot"; 	break;
			case -3: name =     "Block"; 	break;
			}
			items[i] = new JCheckBoxMenuItem(name);
			add(items[i]);
			//add(new JLabel(name));
			add(new JLabel(String.valueOf(o.getX())));
			add(new JLabel(String.valueOf(o.getY())));
			items[i].setActionCommand(String.valueOf(i));
			items[i].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					handleItemChecked(e);
					
				}
				
			});
		}
		JButton remove = new JButton("Remove");
		JButton cancel = new JButton("Cancel");
		add(remove);
		add(new JLabel());
		add(cancel);
		
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRemoveClicked();
				
			}		
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				handleCancelClicked();
				
			}		
		});
		setVisible(true);
	}
	
	public void handleCancelClicked(){
		//get rid of the halos
		for (Obstacle o: obstacles){	
			o.setHalo(false);		
		}
		owner.trackingPanel.processImage();
		dispose();
	}
	
	public void handleRemoveClicked(){
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		Obstacle o;
		for (int i =0; i< items.length; i++){
			if (items[i].isSelected()){
				o = obstacles.get(i);
				//again we are skipping the walls
				obs.add(o);
			}
		}
		obstacles.removeAll(obs);
		owner.trackingPanel.processImage();
		dispose();
	}
	
	private void handleItemChecked(ActionEvent e) {
		//put a halo around all the selected items
		for (int i = 0; i< items.length; i++){
			if (items[i].isSelected()){
				obstacles.get(i).setHalo(true);
			}else{
				obstacles.get(i).setHalo(false);
			}
		}
		//update the image
		owner.trackingPanel.processImage();		
	}
}

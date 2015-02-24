package dang.tracker.objects;

import java.awt.Graphics;

import javax.swing.JPanel;

import dang.robot.Robot;

public class EnvironmentPanel extends JPanel {
	Robot robot = new Robot (100,120,0);

	public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        robot.draw(g);
        
	}
}

package dang.tracker;


import java.awt.*;
import javax.swing.*;

public class StartupDialog extends JDialog {

    public StartupDialog(Frame owner){

		super(owner,"Robot Tracker",false);
		setResizable(false);
		setSize(330, 418);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.white, 2));

        JLabel label = new JLabel("Robot Tracker (v" + RobotTracker.VERSION + ")");
 		label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);

        label = new JLabel("School of Computer Science");
 		label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);

        label = new JLabel("               Carleton University               ");
 		label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);

        add(new JLabel("Copyright " + RobotTracker.YEAR));
        add(new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\Startup.png")));
        add(new JLabel("Initializing WebCam ..."));
	}
}
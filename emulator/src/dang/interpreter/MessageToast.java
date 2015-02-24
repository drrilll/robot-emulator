package dang.interpreter;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * A temporary dialog (about 1.2 seconds) that lets the user
 * know whether the action they have just taken is successful 
 * or not.
 * @author darrylhill
 *
 */
public class MessageToast extends Dialog implements ActionListener{
	
    private final Timer timer = new Timer(1200,this);

	public MessageToast(JFrame owner, String name, String output){
		super(owner, name);
		setSize(350,100);
		this.setLocationRelativeTo(owner);
		setLayout(new BorderLayout());
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1,3));
		add(south,BorderLayout.SOUTH);
		final JLabel message = new JLabel(output, SwingConstants.CENTER);
		
		//JScrollPane scrollPane = new JScrollPane(message);
		add(message, BorderLayout.CENTER);
		setVisible(true);
		timer.setRepeats(false);
		timer.start();
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//the timer goes off, and we are done
		this.dispose();
	}
	
}

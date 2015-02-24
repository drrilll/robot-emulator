package dang.tracker;


import java.sql.Time;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogDialog extends JDialog {
	private	JTextArea	logField;
	private JPopupMenu 	logPopMenu;
	private boolean 	newLine = true;	  	// Specifies whether to add a new line to the log output when appending.
	private boolean 	timeStamp = false;	// Specifies whether to add a timestamp to the log output when appending.

    public LogDialog(Frame owner){
		super(owner,"Log Output",false);
		setLocationRelativeTo(owner);
		setLocation(RobotTracker.MIN_WIDTH+40, 40);
		setSize(300, 400);
		setLayout(new GridBagLayout());
        logField = new JTextArea("");
        logField.setEditable(false);
        logField.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(logField,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        c.weightx = 1.0; c.weighty = 1.0;
        c.insets= new Insets(5,5,5,5);
		add(scrollPane, c);

		// Add the popup menu
		logPopMenu = new JPopupMenu();
		logField.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON3)
					logPopMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			});

		JMenuItem 	item = new JMenuItem("Clear Log Output");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logField.setText("");
				newLine = true;
			}
		});
		logPopMenu.add(item);
		JCheckBoxMenuItem 	item2 = new JCheckBoxMenuItem("Append Time Stamps");
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeStamp = ((JCheckBoxMenuItem)e.getSource()).getState();
			}
		});
		logPopMenu.add(item2);
	}

	public void clearText() {
		logField.setText("");
	}

	// Append text to the log output.
	public void appendText(String output) {
		if (timeStamp && newLine)
			writeTimeStamp();
		logField.append(output);
	}

	// Append text to the log output and add a new line to it.
	public void appendTextWithCR(String output) {
		if (timeStamp && newLine)
			writeTimeStamp();
		logField.append(output+"\n");
		newLine = true;
		logField.setCaretPosition(logField.getText().length());
	}

	// Writes the current time to the specified text area.
	private void writeTimeStamp() {
		logField.append("[" + new Time(System.currentTimeMillis()) + "] ");
		newLine = false;
	}
}
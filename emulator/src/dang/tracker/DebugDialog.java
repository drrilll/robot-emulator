package dang.tracker;


import java.sql.Time;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class DebugDialog extends JDialog {
	private	JTextArea	debugField;
	private JPopupMenu 	debugPopMenu;
	private boolean 	newLine = true;	  // Specifies whether to add a new line to the debug output when appending.
	private boolean 	timeStamp = false;	  // Specifies whether to add a timestamp to the debug output when appending.

    public DebugDialog(Frame owner){
		super(owner,"Debug Output",false);
		setLocationRelativeTo(owner);
		setLocation(RobotTracker.MIN_WIDTH, 0);

		setSize(300, 400);
		setLayout(new GridBagLayout());
        debugField = new JTextArea("");
        debugField.setEditable(false);
        debugField.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(debugField,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        c.weightx = 1.0; c.weighty = 1.0;
        c.insets= new Insets(5,5,5,5);
		add(scrollPane, c);

		// Add the popup menu
		debugPopMenu = new JPopupMenu();
		debugField.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON3)
					debugPopMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			});

		JMenuItem 	item = new JMenuItem("Clear Debug Output");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debugField.setText("");
				newLine = true;
			}
		});
		debugPopMenu.add(item);
		JCheckBoxMenuItem 	item2 = new JCheckBoxMenuItem("Append Time Stamps");
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeStamp = ((JCheckBoxMenuItem)e.getSource()).getState();
			}
		});
		debugPopMenu.add(item2);
	}

	public void clearText() {
		debugField.setText("");
	}

	// Append text to the debug output.
	public void appendText(String output) {
		if (timeStamp && newLine)
			writeTimeStamp();
		debugField.append(output);
	}

	// Append text to the debug output and add a new line to it.
	public void appendTextWithCR(String output) {
		if (timeStamp && newLine)
			writeTimeStamp();
		debugField.append(output+"\n");
		newLine = true;
		debugField.setCaretPosition(debugField.getText().length());
	}

	// Writes the current time to the specified text area.
	private void writeTimeStamp() {
		debugField.append("[" + new Time(System.currentTimeMillis()) + "] ");
		newLine = false;
	}
}
package dang.interpreter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ErrorDialog extends JDialog {
	Exception ex = null;
	final String errorHeader;
	final String errorText;

	public ErrorDialog(JFrame owner, String name, Exception ex){
		super(owner, name);
		this.ex = ex;
		this.setLocationRelativeTo(owner);
		errorHeader = ex.toString();
		errorText = getStackTrace(ex);
		initComponents();
		
	}
	
	public ErrorDialog(JFrame parent, String name, String e) {
		super (parent, name);
		errorHeader = e;
		errorText = null;
	}
	
	public void initComponents(){
		setSize(600,100);
		setLayout(new BorderLayout());
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1,3));
		add(south,BorderLayout.SOUTH);
		final JTextArea errorMessage = new JTextArea(errorHeader);
		JScrollPane scrollPane = new JScrollPane(errorMessage);
		add(scrollPane, BorderLayout.CENTER);
		JButton okButton = new JButton("OK");
		south.add(okButton);
		if (ex != null){
			JButton stackTrace = new JButton("Show Stacktrace");
			south.add(stackTrace);
			stackTrace.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setSize(600,400);
					errorMessage.setText(errorText);
				}
			});
		}
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		setVisible(true);
	}

	public String getStackTrace(Throwable aThrowable) {
	    Writer result = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(result);
	    aThrowable.printStackTrace(printWriter);
	    return result.toString();
	  }
}

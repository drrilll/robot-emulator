package dang.interpreter;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import dang.program.Debug;

public class FindTextDialog extends JDialog {
	String currentText = null;
	StringReader readText;
	BufferedReader readBuffer;
	IDE ide;
	JFrame parent;
	String textToFind = null;
	JTextPane textPane;
	JTextField ttf;
	JLabel messageField;
	DefaultHighlighter.DefaultHighlightPainter highlightPainter;
	String replacer = "¥";
	
	int num;
	
	
	public FindTextDialog(IDE ide){
		highlightPainter = 
		        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		num = 0;
		int ignorecase = 1;
		this.ide = ide;
		parent = ide.getParent();
		textPane = ide.getCurrentPane();
		currentText = textPane.getText();
		StringBuilder build = new StringBuilder(currentText);
		//strip out the '\r'
		int pos;
		while(true){
			pos = build.lastIndexOf("\r");
			if (pos == -1)break;
			build.deleteCharAt(pos);
		}
		currentText = build.toString();
		
		setLayout(new GridLayout(0,2));
		add(new JLabel("Find:"));
		add(ttf=new JTextField());
		
		JButton find, cancel;
		add(find = new JButton("Find"));
		find.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				textPane.getHighlighter().removeAllHighlights();
				textToFind = ttf.getText();
				currentText = textPane.getText();
				StringBuilder build = new StringBuilder(currentText);
				//strip out the '\r'
				int pos;
				while(true){
					pos = build.lastIndexOf("\r");
					if (pos == -1)break;
					build.deleteCharAt(pos);
				}
				currentText = build.toString();
				num = 0;

				
				findText();
				
			}
			
		});
		
		add(cancel = new JButton("Cancel"));
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				handleCancel();
				
			}
			
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				handleCancel();
			}
		});
		
		add(messageField = new JLabel());
		
		setSize(200,100);
		setVisible(true);
		
	}
	
	public void handleCancel(){
		textPane.getHighlighter().removeAllHighlights();
		dispose();
	}
	
	public void findText(){
		int counter = 0;
		int startPos, endPos;
		currentText = currentText.toLowerCase();
		textToFind = textToFind.toLowerCase();
		readText = new StringReader(currentText);
		readBuffer = new BufferedReader(readText);
		try {
			String Line = readBuffer.readLine();
			while(Line!=null){
				if(Line.contains(textToFind)){
					//line now consists of the substring after the found text
					Line = Line.substring(Line.indexOf(textToFind)+ textToFind.length());
					counter = counter + 1;

					startPos = currentText.indexOf(textToFind)+num;
					endPos = currentText.indexOf(textToFind) + textToFind.length()+num;
					num += currentText.indexOf(textToFind) + textToFind.length();
					currentText = currentText.substring(currentText.indexOf(textToFind) + textToFind.length());
							
					textPane.getHighlighter().addHighlight(startPos, endPos, 
					            highlightPainter);
				}else{
					Line = readBuffer.readLine();
				}
			}
			if(counter > 0){				
				
				messageField.setText("Found "+counter);    

			}
			else{
				messageField.setText("No Matches.");    
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch(NullPointerException e){
			messageField.setText("No Matches.");    
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}

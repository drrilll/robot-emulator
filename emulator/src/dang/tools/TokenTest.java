package dang.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.KeyStroke;


import dang.interpreter.IDE;
import dang.interpreter.SpinTokenizer;
import dang.robot.Robot;

public class TokenTest {
	JEditorPane pane;

	public static void main(String[] args){
		new TokenTest();
	}
	public TokenTest(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		final int WIDTH = screenSize.width;
		final int HEIGHT = screenSize.height;
		JFrame f = new JFrame("Robot Graph");
        f.setLocation(WIDTH-600, 0);
		Container content = f.getContentPane();
		content.setLayout(new BorderLayout());
		JButton button = new JButton("Start Tracker");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pane = new JEditorPane();
		pane.setDocument(new IDE.TabDocument());
		AbstractAction functionKeyActions = new FunctionKeyAction();
		pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0),
                "actionMapKey");
		pane.getActionMap().put("actionMapKey",functionKeyActions);
		content.add(pane,BorderLayout.CENTER);
		content.add(button,BorderLayout.SOUTH);
		f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,500);
        f.setVisible(true);
        f.setResizable(false);
	}
	
	public void load() throws Exception{
		/*SpinTokenizer token = new SpinTokenizer(
				new BufferedReader(new InputStreamReader
						(new ByteArrayInputStream(pane.getText().getBytes()))));*/
		SpinTokenizer token = new SpinTokenizer(pane.getText());
		while(token.nextToken()!=SpinTokenizer.TT_EOF){
			System.out.println("Token type: "+token.ttype);
			System.out.println("Token sval: "+ token.sval);
			System.out.println("Token nval: "+ token.nval);
			System.out.println("Token oval: "+ token.oval);
			System.out.println(Character.toString((char) token.ttype));
			/*token.pushBack();
			System.out.println("Token type: "+token.ttype);
			System.out.println("Token sval: "+ token.sval);
			System.out.println("Token nval: "+ token.nval);
			System.out.println(Character.toString((char) token.ttype));
			token.nextToken();
			System.out.println("Token type: "+token.ttype);
			System.out.println("Token sval: "+ token.sval);
			System.out.println("Token nval: "+ token.nval);
			System.out.println(Character.toString((char) token.ttype));*/
		}
	}
	

private class FunctionKeyAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub

	}



}

}

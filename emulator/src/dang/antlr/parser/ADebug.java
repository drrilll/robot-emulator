package dang.antlr.parser;


import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class ADebug {
	static String[] disable = {"bc","blockdetect"};
	static HashMap<String, DebugFrame> debugFrames = new HashMap<String, DebugFrame>();
	static boolean DEBUG = false;
	
	public static void setDebug(boolean d){
		DEBUG = d;
	}
	
	public static void debug(String message){
		if (DEBUG){
			System.out.println(message);
		}
	}
	
	public static void debug(char c){
		if (DEBUG){
			System.out.println(c);
		}
	}
	
	public static void debug(String message, String window){
		//disable windows we don't need to avoid clutter
		for (int i = 0; i < disable.length; i++){
			if (disable[i].equals(window)){
				return;
			}
		}
		if (debugFrames.containsKey(window)){
			debugFrames.get(window).addMessage(message);
		}else{
			DebugFrame frame = new DebugFrame(window);
			debugFrames.put(window, frame);
			frame.addMessage(message);
		}
	}
	
	private static class DebugFrame extends JFrame{
		
		JTextArea output;
		public DebugFrame(String name){
			super(name);
			setLocation(400, 0);
			setSize(400,400);
			output = new JTextArea();
			JScrollPane pane = new JScrollPane(output,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(pane);
			setVisible(true);
			
		}
		
		public void addMessage(String message){
			output.append(message+"\n");
		}
	}
}

package dang.interpreter;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import dang.program.Debug;
import dang.robot.Robot;
import dang.tracker.RobotTracker;


/**
 * All tabs are replaced by 8 spaces for consistent parsing and so forth.
 * The default SPIN tabs are a nightmare. If there is one warning in 
 * transitioning code from SPIN to here, this would be it. Mind your tabs. 
 * Better yet, just use spaces, save yourself the headache.
 * @author darrylhill
 *
 */
public class IDE {

	final String LASTFILE = "lastFile";

	JTextPane pane;						//the current selected pane of the tabbedpane
	Compiler compiler;					//compiler object
	RobotTracker tracker;				//reference to tracker. Also this is where the 
										//tracker is initially created
	Robot robot;						//robot, so we can load the code on
	JFrame parent;						//the frame this IDE sits in, so we can align
										//dialog boxes
	boolean outputErrorToDialog = false;	//we can output to the console as well
	final IDE foo;							//a placeholder for "this"
	private MenuListener menuListener;		
	JMenuItem newFile, save, open;			
	TabbedEditor tpane;
	SpinDocumentListener spinDocumentListener;	//for the editing functions
	TextLineNumber tln;							//a component that draws line numbers
												//on the ide
	AbstractDocument doc;						
	JLabel message;								//messages
	boolean actionTableCreated = false;		//flag

	public IDE() throws Exception{
		foo = this;
		JMenu fileMenu = new JMenu("File");
		JMenu compilerMenu = new JMenu("Compiler");
		newFile = new JMenuItem("new");
		open = new JMenuItem("open");
		save = new JMenuItem("save");
		JMenuItem saveAs = new JMenuItem("save as ...");
		JMenuItem compile = new JMenuItem("compile");
		JMenu errorOutput = new JMenu("error output");
		JCheckBoxMenuItem debug = new JCheckBoxMenuItem("debug");


		JRadioButtonMenuItem dialogOut, consoleOut;
		ButtonGroup group = new ButtonGroup();
		dialogOut = new JRadioButtonMenuItem("output to dialog");
		consoleOut = new JRadioButtonMenuItem("output to console");
		dialogOut.setSelected(true);
		group.add(dialogOut);
		group.add(consoleOut);
		errorOutput.add(dialogOut);
		errorOutput.add(consoleOut);
		dialogOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outputErrorToDialog = true;
			}
		});
		consoleOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outputErrorToDialog = false;
			}
		});
		debug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Debug.setDebug(((JCheckBoxMenuItem)e.getSource()).isSelected());		
			}	
		});


		open.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		compile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F9,0));

		message = new JLabel();	
		message.setFont(new Font("wtf",Font.PLAIN, 10));
		menuListener = new MenuListener(this, getLastFiles());
		spinDocumentListener = new SpinDocumentListener(this);

		newFile.addActionListener(menuListener);
		save.addActionListener(menuListener);
		saveAs.addActionListener(menuListener);
		open.addActionListener(menuListener);
		compile.addActionListener(menuListener);

		compilerMenu.add(compile);
		compilerMenu.add(errorOutput);
		compilerMenu.addSeparator();
		compilerMenu.add(debug);

		fileMenu.add(newFile);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);

		JMenuBar menuBar = new JMenuBar();

		menuBar.add(fileMenu);
		menuBar.add(compilerMenu);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		final int WIDTH = screenSize.width;
		final int HEIGHT = screenSize.height;
		robot = new Robot ();
		setCompiler(new Compiler(getRobot(), message, this));
		JFrame f = new JFrame("Spin IDE");
		f.setJMenuBar(menuBar);
		f.setLocation(WIDTH-600, 0);
		Container content = f.getContentPane();
		content.setLayout(new BorderLayout());
		JButton button = new JButton("Start Tracker");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tracker == null){
					tracker = new RobotTracker("Tracker 2.0", foo);
					message.setText("tracker started");
				}
				//if there is already a tracker, do nothing
			}
		});

		// Event Handler for Window Closing
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeIde();
			}
		});

		tpane = new TabbedEditor();
		tpane.addChangeListener(menuListener);
		content.add(tpane,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(button, BorderLayout.SOUTH);
		content.add(panel,BorderLayout.SOUTH);
		panel.add(message, BorderLayout.NORTH);
	
		/* ****************************/
		/*
		 * basically an unused pane to initialize the command map
		 * This is what happens when you use other peoples code.
		 * I need this initialized before I load a pane, so we use
		 * a blank pane and throw it away after.
		 * The code used is properly credited near the bottom of
		 * this class
		 */
		JTextPane pane = new JTextPane();
		StyledDocument styledDoc = pane.getStyledDocument();
		if (styledDoc instanceof AbstractDocument) {
			doc = (AbstractDocument)styledDoc;
		}
		doc.addUndoableEditListener(new MyUndoableEditListener());
		pane.setDocument(new TabDocument());
		//all so we can create an action table
		actions=createActionTable(pane);

		JMenu editMenu = createEditMenu();
		JMenu styleMenu = createStyleMenu();
		menuBar.add(editMenu);
		menuBar.add(styleMenu);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600,500);
		f.setVisible(true);
		f.setResizable(false);

		menuListener.openCurrentFiles();


	}


	public JLabel getMessage() {
		return message;
	}


	public void setMessage(JLabel message) {
		this.message = message;
	}


	public JTextPane addNewPane(String name){
		pane = new JTextPane();      
		pane.setDocument(new TabDocument());
		//set up the editing capabilities
		pane.getDocument().addDocumentListener(spinDocumentListener);
		pane.getDocument().addUndoableEditListener(new MyUndoableEditListener());
		JScrollPane scrollPane = new JScrollPane(pane);
		//line number drawing component
		scrollPane.setRowHeaderView(new TextLineNumber(pane));
		scrollPane.setName(name);
		pane.setName(name);
		//add the new pane to our tabbed pane
		tpane.setSelectedComponent(tpane.add(scrollPane));
		tpane.setTabComponentAt(tpane.getSelectedIndex(),new ButtonTabComponent(tpane, menuListener));

		return pane;
	}

	public JTextPane getCurrentPane(){
		JViewport viewport = ((JScrollPane)tpane.getSelectedComponent()).getViewport(); 
		return (JTextPane)viewport.getView(); 
	}

	public String getCurrentName(){
		return ((JScrollPane)tpane.getSelectedComponent()).getName();
	}

	public void setCurrentName(String name){
		((JScrollPane)tpane.getSelectedComponent()).setName(name);
		Debug.debug("Setting pane "+tpane.getSelectedIndex() +" to "+name);
		tpane.setTitleAt(tpane.getSelectedIndex(), name);
	}

	public int getCurrentPaneId(){
		return tpane.getSelectedIndex();
	}

	/* ******** Getters and setters *****************/

	public JFrame getParent() {
		return parent;
	}

	public void setParent(JFrame parent) {
		this.parent = parent;
	}

	public MenuListener getMenuListener() {
		return menuListener;
	}

	public void setMenuListener(MenuListener menuListener) {
		this.menuListener = menuListener;
	}

	public JTextPane getPane() {
		return pane;
	}

	public void setPane(JTextPane pane) {
		this.pane = pane;
	}

	public Compiler getCompiler() {
		return compiler;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public RobotTracker getTracker() {
		return tracker;
	}

	public void setTracker(RobotTracker tracker) {
		this.tracker = tracker;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}



	public static void main(String args[]){
		try {
			new IDE();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO pop-up message
			e.printStackTrace();
		}
	}

	public void compile(){
		Debug.debug("loading . . .");
		message.setText("loading ...");
		File file = null;
		boolean err = true;
		try {
			//get the directory of the file so we can load objects if need be
			file = menuListener.getOpenFiles().get(getCurrentPaneId());
			String workingDirectory = file.getParent();
		    new MessageToast(getParent(),"Compiling " +file.getName(), "compiling "+file.getName());
			err=getCompiler().compile(getCurrentPane().getText(), 
					getCurrentPane().getName(), workingDirectory);
		} catch (Exception e) {
			if (outputErrorToDialog){
				new ErrorDialog(getParent(),"Error!",e);
			}else{
				e.printStackTrace();
			}
		}
		final String fileName = file.getName();
		if(!err){	
			new MessageToast(getParent(),"Compiled", fileName+" compiled successfully");
		}
		Debug.debug("IDE robot: "+robot);
	}

	/**
	 * When the ide is shut down, it saves all the files that
	 * it currently has open. Here we read them back in and 
	 * store them in an ArrayList and return them.
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SpinFile> getLastFiles() throws Exception{
		String currentDirectory = System.getProperty("user.dir");
		String fs = System.getProperty("file.separator");
		String s = "";
		ArrayList<SpinFile> files = new  ArrayList<SpinFile>();
		Scanner scanner = null; 
		Debug.debug("Reading from directory: "+ currentDirectory +fs+"config"+fs+ LASTFILE);
		File file;
		try {
			scanner = new Scanner(new FileReader(currentDirectory +fs+"config"+fs+ LASTFILE));
			while(scanner.hasNextLine()){
				s = scanner.nextLine();
				Debug.debug("Reading in file: "+s);
				file = new SpinFile(s);
				if (file!=null){
					files.add(new SpinFile(s));
				}
			}
		}catch(Exception e){

		}finally{
			if (scanner != null){
				scanner.close();
			}
		}
		return files;
	}

	public void closeIde() {
		//save all the open filenames and exit
		if (tracker != null){
			tracker.closeTracker();
		}
		//get all the files that are open
		ArrayList<SpinFile> openFiles = getMenuListener().getOpenFiles();
		String currentDirectory = System.getProperty("user.dir");
		String fs = System.getProperty("file.separator");
		PrintWriter out = null;
		//write all the open files to a text file, so we can reopen them
		//next time the IDE boots up
		File lastfiles = new File(currentDirectory +fs+"config"+fs+ LASTFILE);
		try {
			lastfiles.createNewFile();
			out = new PrintWriter(new FileWriter(lastfiles));
			for (SpinFile file : openFiles){
				//Only write files that the user has saved. 
				//ie, if it is untitled we don't save it.
				//I need to prompt for saving
				//but it is not a priority right now
				if (file.isTitled()){
					Debug.debug("File being written: "+file.getName());
					out.println(file.getAbsolutePath());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null){
				out.close();
			}
		}
		//closing the ide closes everything
		System.exit(1);
	}


	/**
	 * Replace all the tabs with spaces so that I don't have
	 * an aneurysm. Tabs are implementation dependent, which
	 * makes them a nightmare.
	 * @author darrylhill
	 *
	 */
	@SuppressWarnings("serial")
	public static class TabDocument extends DefaultStyledDocument {
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			str = str.replaceAll("\t", "        ");
			super.insertString(offs, str, a);
		}
	}


	/*
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions
	 * are met:
	 *
	 *   - Redistributions of source code must retain the above copyright
	 *     notice, this list of conditions and the following disclaimer.
	 *
	 *   - Redistributions in binary form must reproduce the above copyright
	 *     notice, this list of conditions and the following disclaimer in the
	 *     documentation and/or other materials provided with the distribution.
	 *
	 *   - Neither the name of Oracle or the names of its
	 *     contributors may be used to endorse or promote products derived
	 *     from this software without specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
	 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
	 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */

	/*
	 * Copyright applies to the following code
	 */

	//undo helpers
	protected UndoAction undoAction;
	protected RedoAction redoAction;
	protected UndoManager undo = new UndoManager();
	HashMap<Object, Action> actions;

	//This one listens for edits that can be undone.
	protected class MyUndoableEditListener
	implements UndoableEditListener {
		public void undoableEditHappened(UndoableEditEvent e) {
			//Remember the edit and update the menus.
			undo.addEdit(e.getEdit());
			undoAction.updateUndoState();
			redoAction.updateRedoState();
		}
	}
	/*
    //And this one listens for any changes to the document.
    protected class MyDocumentListener
                    implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        public void removeUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        public void changedUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        private void displayEditInfo(DocumentEvent e) {
            Document document = e.getDocument();
            int changeLength = e.getLength();
            changeLog.append(e.getType().toString() + ": " +
                changeLength + " character" +
                ((changeLength == 1) ? ". " : "s. ") +
                " Text length = " + document.getLength() +
                "." + newline);
        }
    }


    //Add a couple of emacs key bindings for navigation.
    protected void addBindings() {
        InputMap inputMap = textPane.getInputMap();

        //Ctrl-b to go backward one character
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.backwardAction);

        //Ctrl-f to go forward one character
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.forwardAction);

        //Ctrl-p to go up one line
        key = KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.upAction);

        //Ctrl-n to go down one line
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.downAction);
    }
	 */
	//Create the edit menu.
	protected JMenu createEditMenu() {
		JMenu menu = new JMenu("Edit");

		//Undo and redo are actions of our own creation.
		undoAction = new UndoAction();
		JMenuItem mItem = new JMenuItem("undo");
		mItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		mItem.addActionListener(undoAction);
		menu.add(mItem);

		redoAction = new RedoAction();
		mItem = new JMenuItem("redo");
		mItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		mItem.addActionListener(redoAction);
		menu.add(mItem);

		menu.addSeparator();

		//These actions come from the default editor kit.
		//Get the ones we want and stick them in the menu.
		menu.add(getActionByName(DefaultEditorKit.cutAction));
		menu.add(getActionByName(DefaultEditorKit.copyAction));
		menu.add(getActionByName(DefaultEditorKit.pasteAction));

		menu.addSeparator();

		menu.add(getActionByName(DefaultEditorKit.selectAllAction));

		menu.addSeparator();

		mItem = new JMenuItem("find");
		mItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		mItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					handleFind();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}

		});

		menu.add(mItem);
		return menu;
	}

	public void handleFind() throws BadLocationException{
		new FindTextDialog(this);
		//GetTextToFindAndFind(getCurrentPane(), null, 1, 0);
	}

	//Create the style menu.
	protected JMenu createStyleMenu() {
		JMenu menu = new JMenu("Style");

		Action action = new StyledEditorKit.BoldAction();
		action.putValue(Action.NAME, "Bold");
		menu.add(action);

		action = new StyledEditorKit.ItalicAction();
		action.putValue(Action.NAME, "Italic");
		menu.add(action);

		action = new StyledEditorKit.UnderlineAction();
		action.putValue(Action.NAME, "Underline");
		menu.add(action);

		menu.addSeparator();

		menu.add(new StyledEditorKit.FontSizeAction("12", 12));
		menu.add(new StyledEditorKit.FontSizeAction("14", 14));
		menu.add(new StyledEditorKit.FontSizeAction("18", 18));

		menu.addSeparator();

		menu.add(new StyledEditorKit.FontFamilyAction("Serif",
				"Serif"));
		menu.add(new StyledEditorKit.FontFamilyAction("SansSerif",
				"SansSerif"));

		menu.addSeparator();

		menu.add(new StyledEditorKit.ForegroundAction("Red",
				Color.red));
		menu.add(new StyledEditorKit.ForegroundAction("Green",
				Color.green));
		menu.add(new StyledEditorKit.ForegroundAction("Blue",
				Color.blue));
		menu.add(new StyledEditorKit.ForegroundAction("Black",
				Color.black));

		return menu;
	}
	/*
    protected void initDocument() {
        String initString[] =
                { "Use the mouse to place the caret.",
                  "Use the edit menu to cut, copy, paste, and select text.",
                  "Also to undo and redo changes.",
                  "Use the style menu to change the style of the text.",
                  "Use the arrow keys on the keyboard or these emacs key bindings to move the caret:",
                  "Ctrl-f, Ctrl-b, Ctrl-n, Ctrl-p." };

        SimpleAttributeSet[] attrs = initAttributes(initString.length);

        try {
            for (int i = 0; i < initString.length; i ++) {
                doc.insertString(doc.getLength(), initString[i] + newline,
                        attrs[i]);
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text.");
        }
    }
	 */
	protected SimpleAttributeSet[] initAttributes(int length) {
		//Hard-code some attributes.
		SimpleAttributeSet[] attrs = new SimpleAttributeSet[length];

		attrs[0] = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attrs[0], "SansSerif");
		StyleConstants.setFontSize(attrs[0], 16);

		attrs[1] = new SimpleAttributeSet(attrs[0]);
		StyleConstants.setBold(attrs[1], true);

		attrs[2] = new SimpleAttributeSet(attrs[0]);
		StyleConstants.setItalic(attrs[2], true);

		attrs[3] = new SimpleAttributeSet(attrs[0]);
		StyleConstants.setFontSize(attrs[3], 20);

		attrs[4] = new SimpleAttributeSet(attrs[0]);
		StyleConstants.setFontSize(attrs[4], 12);

		attrs[5] = new SimpleAttributeSet(attrs[0]);
		StyleConstants.setForeground(attrs[5], Color.red);

		return attrs;
	}

	//The following two methods allow us to find an
	//action provided by the editor kit by its name.
	private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
		HashMap<Object, Action> actions = new HashMap<Object, Action>();
		Action[] actionsArray = textComponent.getActions();
		for (int i = 0; i < actionsArray.length; i++) {
			Action a = actionsArray[i];
			actions.put(a.getValue(Action.NAME), a);
		}
		return actions;
	}

	private Action getActionByName(String name) {
		return actions.get(name);
	}

	class UndoAction extends AbstractAction {
		public UndoAction() {
			super("Undo");
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				undo.undo();
			} catch (CannotUndoException ex) {
				System.out.println("Unable to undo: " + ex);
				// ex.printStackTrace();
			}
			updateUndoState();
			redoAction.updateRedoState();
		}

		protected void updateUndoState() {
			if (undo.canUndo()) {
				setEnabled(true);
				putValue(Action.NAME, undo.getUndoPresentationName());
			} else {
				setEnabled(false);
				putValue(Action.NAME, "Undo");
			}
		}
	}

	class RedoAction extends AbstractAction {
		public RedoAction() {
			super("Redo");
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				undo.redo();
			} catch (CannotRedoException ex) {
				System.out.println("Unable to redo: " + ex);
				// ex.printStackTrace();
			}
			updateRedoState();
			undoAction.updateUndoState();
		}

		protected void updateRedoState() {
			if (undo.canRedo()) {
				setEnabled(true);
				putValue(Action.NAME, undo.getRedoPresentationName());
			} else {
				setEnabled(false);
				putValue(Action.NAME, "Redo");
			}
		}
	}

	public void GetTextToFindAndFind(JTextPane textPane, String textToFind, int ignorecase, int findCounter) throws BadLocationException{
		// findCounter = 0 or 1. 0 represents find and 1 represents findCounter.
		String currentText = null;
		StringReader readText;
		BufferedReader readBuffer;
		if(findCounter ==0){

			if(textToFind == null){
				textToFind = JOptionPane.showInputDialog(parent, "Please Enter Text.", "Find", 0);
			}
			else if(textToFind.isEmpty()){
				textToFind = JOptionPane.showInputDialog(parent, "Please Enter Text.", "Find", 0);
			}

			// Use any Character. But I a suggest to use a character from an Encrypted file.   
			currentText = textPane.getText();
			if(ignorecase==1){
				currentText = currentText.toLowerCase();
				textToFind = textToFind.toLowerCase();
			}
			int counter = 0;
			readText = new StringReader(currentText);
			readBuffer = new BufferedReader(readText);
			try {
				String Line = readBuffer.readLine();
				int found = 0;
				while(Line!=null || found != 1){
					if(Line.contains(textToFind)){
						Line = null;
						found = 1;
					}
					if(Line!=null){
						Line = readBuffer.readLine();
						counter = counter + 1;
					}
				}
				if(found == 1){
					int startPos = currentText.indexOf(textToFind);
					int endPos = currentText.indexOf(textToFind) + textToFind.length();
					DefaultHighlighter.DefaultHighlightPainter highlightPainter = 
					        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
					    textPane.getHighlighter().addHighlight(startPos, endPos, 
					            highlightPainter);
					/*int counter2 = 1;
					while(counter2!=textToFind.length()){
						replacer = replacer + "¥";
						counter2 = counter2 + 1;
					}*/
					//currentText = currentText.replaceFirst(textToFind, replacer);
					findCounter = 1;
					JOptionPane.showMessageDialog(parent, "Found one.", "Message", 0);    

				}
				else{
					JOptionPane.showMessageDialog(null, "No Matches.", "Message", 0);    
				}

			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch(NullPointerException e){
				JOptionPane.showMessageDialog(null, "No Matches.", "Message", 0);
			}

		}

		else{
			int counter = 0;
			readText = new StringReader(currentText);
			readBuffer = new BufferedReader(readText);
			try {
				String Line = readBuffer.readLine();
				int found = 0;
				while(Line!=null || found != 1){
					if(Line.contains(textToFind)){
						Line = null;
						found = 1;
					}
					if(Line!=null){
						Line = readBuffer.readLine();
						counter = counter + 1;
					}
				}
				if(found == 1){
					textPane.setSelectionStart(currentText.indexOf(textToFind));
					textPane.setSelectionEnd(currentText.indexOf(textToFind) + textToFind.length());
					//currentText = currentText.replaceFirst(textToFind, replacer);
				}
				else{
					JOptionPane.showMessageDialog(null, "No Matches.", "Message", 0);    
				}
			}
			catch(IOException e){
				e.printStackTrace();
			} catch(NullPointerException e){
				JOptionPane.showMessageDialog(null, "No Matches.", "Message", 0);    
			}
		}
	}


}

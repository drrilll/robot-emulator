package dang.interpreter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dang.program.Debug;

public class MenuListener implements ActionListener, ChangeListener {
	
	private IDE ide;
	private JLabel message;
	JFileChooser jfc;
	ArrayList<SpinFile> openFiles;
	ArrayList<SpinFile> removeFiles;
	int untitledCount = 0;

	public MenuListener(IDE ide, ArrayList<SpinFile> lastFiles){
		this.ide = ide;
		message = ide.getMessage();
		jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//by default we look here first
		jfc.setCurrentDirectory(new File("spinfiles"));
		if (lastFiles != null){
			openFiles = lastFiles;
		}
		removeFiles = new ArrayList<SpinFile>();
		
		//debugging
		for (File file : openFiles){
			Debug.debug("Files open: "+file.getName());
		}
	}
	
	public ArrayList<SpinFile> getOpenFiles() {
		return openFiles;
	}

	public void setOpenFiles(ArrayList<SpinFile> openFiles) {
		this.openFiles = openFiles;
	}

	public IDE getIde() {
		return ide;
	}

	public void setIde(IDE ide) {
		this.ide = ide;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("compile")){
			getIde().compile();
		}else if (command.equals("new")){
			untitledCount ++;
			String name = "untitled "+untitledCount;
			//can't do this. Save must go to save as
			SpinFile file = new SpinFile(name);
			file.setTitled(false);
			openFiles.add(file);
			ide.addNewPane(name);
		}else if (command.equals("open")){
			openFile();
		}else if (command.equals("save")){
			int fileId = ide.getCurrentPaneId();
			SpinFile file = openFiles.get(fileId);
			Debug.debug("currentPane filename: " +file.getName());
			if(file.isTitled()){
				saveFile(file);
			}else{
				saveAs(file);
			}
			new MessageToast(ide.getParent(), "Saved","saved file "+file.getName());
		}else if (command.equals("save as ...")){
			SpinFile file = openFiles.get(ide.getCurrentPaneId());
			saveAs(file);
			new MessageToast(ide.getParent(), "Saved","saved file "+file.getName());
		}
	}
	
	public SpinFile saveAs(SpinFile file){
		if (file.isTitled()){
			jfc.setCurrentDirectory(file);
		}
		SpinFile newfile = null;
		int returnVal = jfc.showSaveDialog(ide.getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION){
			newfile = new SpinFile(jfc.getSelectedFile().getAbsolutePath());
			jfc.setCurrentDirectory(newfile);
			//this means that the user has saved it, ie it is not "untitled1"
			file.setTitled(true);
			ide.setCurrentName(newfile.getName());
			openFiles.set(ide.getCurrentPaneId(), newfile);
			saveFile(newfile);
		}
		return newfile;
	}

	public void saveFile(SpinFile file){
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
			out.print(ide.getCurrentPane().getText());	
			out.close();
			message.setText("Saved:  "+file.getName());
		} catch (FileNotFoundException e) {
			ErrorDialog ed = new ErrorDialog(ide.getParent(),"File error", e);
			//e.printStackTrace();
		} finally {
			if (out != null){
				out.close();
			}
		}
	}
	
	public SpinFile openFile(){
		SpinFile currentFile = null;
		int returnVal = jfc.showOpenDialog(ide.getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION){
			currentFile = new SpinFile(jfc.getSelectedFile().getAbsolutePath());
			openFiles.add(currentFile);
			jfc.setCurrentDirectory(currentFile);
			openCurrentFile(currentFile);
		}
		return currentFile;
	}
	
	/**
	 * Open all the files that were open when the ide
	 * closed. Any file that isn't found is added to 
	 * removeFiles, which then removed from openFiles.
	 * We can't remove it from openFiles immediately, 
	 * because we are iterating over it and java will 
	 * throw an error. Once we are done iterating it is
	 * safe to remove them.
	 */
	public void openCurrentFiles(){
		removeFiles.clear();
		for (SpinFile file: openFiles){
			openCurrentFile(file);
		}
		openFiles.removeAll(removeFiles);
	}
	
	/**
	 * open the file that is given as a parameter and add
	 * it to a textpane which is added to the tabbedpane
	 * @param currentFile
	 */
	public void openCurrentFile(SpinFile currentFile){
		
		try {
			String code = readFile(currentFile);
			if (code != null){
				ide.addNewPane(currentFile.getName()).setText(code);
				//if (message != null)message.setText("hi");
				message.setText("Opened file:  "+currentFile.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeFile(int index){
		openFiles.remove(index);
	}
	
	/**
	 * Take an instance of a file and return its text as a string
	 * @param file
	 * @return
	 * @throws IOException
	 */
	 private String readFile( SpinFile file ) throws IOException {
		 	if ((file.isDirectory())||(!file.exists())){
		 		removeFiles.add(file);
		 		//openFiles.remove(file);
		 		return null;
		 	}
		    BufferedReader reader = new BufferedReader( new FileReader (file));
		    String line  = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    String ls = System.getProperty("line.separator");
		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    return stringBuilder.toString();
		 }

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JTabbedPane pane = (JTabbedPane)e.getSource();

		// Get current tab
		int sel = pane.getSelectedIndex();
	}
}




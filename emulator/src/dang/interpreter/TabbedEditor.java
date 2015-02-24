package dang.interpreter;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

public class TabbedEditor extends JTabbedPane {

	public String getText(String name){
		for (int i = 0; i < getTabCount(); i++){
			JScrollPane spane = (JScrollPane)getComponentAt(i); 
			String paneName = spane.getName();
			if (paneName.equals(name)){
				JTextPane epane = (JTextPane)spane.getViewport().getView();
				return epane.getText();
			}
		}
		return null;
	}
	
	public JTextPane getCurrentPane(){
		JScrollPane spane = (JScrollPane)this.getSelectedComponent();
		return (JTextPane)spane.getViewport().getView();
	}
}

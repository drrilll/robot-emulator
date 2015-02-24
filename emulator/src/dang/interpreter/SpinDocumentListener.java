package dang.interpreter;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import dang.program.Debug;

public class SpinDocumentListener implements DocumentListener {

	private IDE ide;
	//flags for which color we are using on the background
	boolean con = false,var = false, obj = false, pub = false, pri = false;
	
	public SpinDocumentListener(IDE ide){
		this.ide = ide;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		//colorBackGround();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		//colorBackGround();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		//colorBackGround();
	}
	
	public void colorBackGround() {
		Debug.debug("recoloring....");
		JEditorPane pane = ide.getCurrentPane();
		Reader colorPass =
				new BufferedReader(new InputStreamReader
						(new ByteArrayInputStream(pane.getText().getBytes())));

		SpinTokenizer token = new SpinTokenizer(pane.getText());
		Color backgroundColor = null;
		int headerStartLine = 0;
		int headerEndLine = 0;
		String lastHeader = "";
		try {
			while (token.nextToken()!= SpinTokenizer.TT_EOF){
				if (token.ttype == SpinTokenizer.TT_HEADER){
					backgroundColor = getSectionColor(lastHeader);
					lastHeader = token.sval;
					headerEndLine = token.lineno()-2;

					Highlighter h = pane.getHighlighter();
					int startOffset = getLineStartOffset(pane, headerStartLine);
					int endOffset = getLineEndOffset(pane, headerEndLine);
					h.addHighlight(startOffset, endOffset,
							new DefaultHighlighter.DefaultHighlightPainter(backgroundColor));
					headerStartLine = token.lineno()-1;
				}
	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getSectionColor(String section){
		/* con is yellow
		 * var is salmon
		 * obj is pink
		 * pub is blue
		 * pri is green
		 */
		
		Color con1 = Color.YELLOW;
		Color con2 = Color.YELLOW;
		Color var1 = Color.MAGENTA;
		Color var2 = Color.MAGENTA;
		Color obj1 = Color.PINK;
		Color obj2 = Color.PINK;
		Color pub1 = Color.BLUE;
		Color pub2 = Color.CYAN;
		Color pri1 = Color.GREEN;
		Color pri2 = Color.GRAY;
		
		if (section.equals("con")){
			con = !con;
			return con ? con1 : con2;
		}else if (section.equals("var")){
			var = !var;
			return var ? var1 : var2;
		}else if (section.equals("obj")){
			obj = !obj;
			return obj ? obj1 : obj2;
		}else if (section.equals("pub")){
			pub = !pub;
			return pub ? pub1 : pub2;
		}else if (section.equals("pri")){
			pri = !pri;
			return pri ? pri1 : pri2;
		}else{
			return Color.white;
		}
	}
	
	int getLineStartOffset(JTextComponent comp, int line) throws BadLocationException {
	    Element map = comp.getDocument().getDefaultRootElement();
	    if (line < 0) {
	        throw new BadLocationException("Negative line", -1);
	    } else if (line >= map.getElementCount()) {
	        throw new BadLocationException("No such line", comp.getDocument().getLength() + 1);
	    } else {
	        Element lineElem = map.getElement(line);
	        return lineElem.getStartOffset();
	    }
	}
	
	int getLineEndOffset(JTextComponent comp, int line) throws BadLocationException {
	    Element map = comp.getDocument().getDefaultRootElement();
	    if (line < 0) {
	        throw new BadLocationException("Negative line", -1);
	    } else if (line >= map.getElementCount()) {
	        throw new BadLocationException("No such line", comp.getDocument().getLength() + 1);
	    } else {
	        Element lineElem = map.getElement(line);
	        return lineElem.getEndOffset();
	    }
	}


}

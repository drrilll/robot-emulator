package dang.antlr.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dang.program.Debug;

public class TabPreprocessor {
	Stack stack;
	public int offset;
	int block = 3;
	public static final int ENDOFLINE = -1;
	public static final int STARTOFLINE = -2;
	boolean lastHeaderWasMethod = true;
	boolean inCase = false;
	boolean firstheader = true;
	int caseTab = 0;
	public TabPreprocessor(){
		stack = new Stack();
		stack.add(-1,TabTokenizer.TT_EOF);
		offset = 0;
	}
	
	/*
	 * What are the blocks? if, ifelse, else, methods (special case).
	 * repeat and all its variations. case statements definitely.
	 * ifnot, elseifnot, I think that is it.
	 */
	public void process(String input) throws Exception{
		TabTokenizer token = new TabTokenizer(input);
		StringBuffer output = new StringBuffer(input);
		while (token.nextToken()!=TabTokenizer.TT_EOF){
			
			
			if((token.ttype != TabTokenizer.TT_EOL)&&(!token.blankline)){
				while(token.ttab <= stack.lastTab()){
					putDedent(output, token.getPosition()- token.sval.length(), "dedent for "+ block);
					stack.pop();
				}
			}
			switch (token.ttype){
			case TabTokenizer.TT_CASE:
			case TabTokenizer.TT_ELSE:
			case TabTokenizer.TT_ELSEIF:
			case TabTokenizer.TT_IF:
			case TabTokenizer.TT_IFNOT:
			case TabTokenizer.TT_ELSEIFNOT:
			case TabTokenizer.TT_REPEAT:


				//System.out.println("adding tab for if/elseif/else: "+token.ttab);
				stack.add(token.ttab, token.ttype);
				//advance to the end of the line
				while(true){
					token.nextToken();
					if (token.ttype==TabTokenizer.TT_EOL) break;
					if (token.ttype=='\'')break;
					if (token.ttype =='{')break;
				}
				putIndent(output, token.getPosition());
				
				break;
			case ':':
				if (stack.lastStatement()==TabTokenizer.TT_CASE){
					//System.out.println("adding tab for CASECASE: "+token.ttab);
					stack.add(token.ttab, token.ttype);
					
					putIndent(output, token.getPosition()+1);
					
					//in case there is a command on the same line, we advance to the end.
					//Otherwise, since the tab would still be equal, next iteration
					//it will pop off a tab
					while(token.nextToken()!=TabTokenizer.TT_EOL);

					
				}
				break;
			
			case TabTokenizer.TT_HEADER:
				//we pop off all the stack, inserting dedents above the header.
				//System.out.println("Got header " + token.sval);
				Debug.debug("clearing stack from header");
				clearStack(output, token.getPosition());
				//to make method blocks, we delineate with indent and dedent.
				//but they do not depend on indentation, only on if it is a method
				//Its a crude hack, but thats the way I like it
				if (!firstheader){
					if (lastHeaderWasMethod)putDedent(output, token.getPosition()- token.sval.length()+1);}
				firstheader = false;
				if ((token.sval.equals("pub")||(token.sval.equals("pri")))){
					lastHeaderWasMethod = true;
					while (token.nextToken()!=TabTokenizer.TT_EOL);
					putIndent(output, token.getPosition());
				}else{
					//lastHeaderWasMethod = false;
				}
				break;
			}
			//clear the stack
			
		}
		Debug.debug("clearing stack from general");
		clearStack(output, token.getPosition());
		if (lastHeaderWasMethod) putDedent(output, token.getPosition());
		writeToFile(output);
	}
	
	private void writeToFile(StringBuffer output) {
		BufferedWriter out = null;
		String fs = System.getProperty("file.separator");
		try {
		//	out = new BufferedWriter(new FileWriter(RobotTracker.INSTALL_DIRECTORY + 
		//			RobotTracker.fs+"out.txt"));	
			out = new BufferedWriter(new FileWriter("config"+fs+"out.txt"));
			out.write(output.toString().toLowerCase());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void clearStack(StringBuffer output, int position){
		while(stack.size()>1){
			stack.pop();
			putDedent(output, position);
		}
	}
	
	
	private void putDedent(StringBuffer output, int position){
		//String m = "{Block: "+block+"}\n";
		//output.insert(position +offset, "\n,>\n");
		output.insert(position +offset, ",>");
		offset += 2 ;
		block --;
	}
	
	private void putDedent(StringBuffer output, int position, String message){
		//String m = '{'+message+"}\n";
		//String m = "{Block: "+block+"}\n";
		output.insert(position +offset, ",>");
		//output.insert(position +offset, "\n,>\n");
		offset += 2;
		block --;
	}
	
	private void putIndent(StringBuffer output, int position){
		output.insert(position +offset, ",<");
		offset += 2;
		block ++;
	}
	/*
	
	private void putIndent(StringBuffer output, int position){
		String m = "{Block: "+block+"}\n";
		output.insert(position +offset, ",<\n"+m);
		offset += 2 + m.length();
		block ++;
	}*/
	
	private class Stack{
		ArrayList<Integer> stack = new ArrayList<Integer>();
		ArrayList<Integer> stateStack = new ArrayList<Integer>();
		public Stack(){
			
		}
		
		public void add(int tab, int statement){
			stack.add(tab);
			stateStack.add(statement);
		}
		
		public int size(){
			return stack.size();
		}
		
		public ArrayList<Integer> getTabStack(){
			return stack;
		}
		
		public int lastTab(){
			return stack.get(stack.size()-1);
		}
		
		public int pop(){
			stateStack.remove(stateStack.size()-1);
			return stack.remove(stack.size()-1);
		}
		
		public int lastStatement(){
			return stateStack.get(stateStack.size()-1);
		}
	}
}

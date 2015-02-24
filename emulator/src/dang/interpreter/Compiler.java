package dang.interpreter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JLabel;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import dang.antlr.parser.CommentPreProcessor;
import dang.antlr.parser.ProgWalker;
import dang.antlr.parser.Spin2GrammarLexer;
import dang.antlr.parser.Spin2GrammarParser;
import dang.antlr.parser.Spin2GrammarParser.prog_return;
import dang.antlr.parser.TabPreprocessor;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Debug;
import dang.program.Program;
import dang.program.objects.SpinObject;
import dang.robot.Robot;

public class Compiler {
	

	//private Program program;
	private Robot robot;
	private MethodHandler methodHandler;
	private JLabel message;
	private String workingDirectory = "";
	private IDE ide;
	
	/*
	 * Variables that hold the constants for the state machine
	 * I can't remember which ones I still use, so I left them in
	 */
	public static final String CON = "con";
	public static final String VAR = "var";
	public static final String OBJ = "obj";
	public static final String PUB = "pub";
	public static final String PRI = "pri";

	/**
	 * 
	 * @param robot
	 * @param message is a JLabel on the IDE that we print messages on
	 * @param ide
	 * @throws IOException
	 * @throws SpinError
	 */
	public Compiler(Robot robot, JLabel message, IDE ide) throws IOException, SpinError{
		this.robot = robot;
		this.message = message;	
		this.ide = ide;
	}
	
	/**
	 * 
	 * @param code
	 * The code from the front lying textpane in the form of a string.
	 * @param name
	 * @param workingDirectory 
	 * when we are compiling the objects, we
	 * look for unreferenced objects in the same directory as the original 
	 * code comes from, which we pass in
	 * @return
	 */
	public boolean compile(String code, String name, String workingDirectory){
		this.workingDirectory = workingDirectory;
		Program program = null;
		boolean err = false;
		try {
			program = new Program(robot, "Main program");
			//adding in things like PI and cnt
			program.addInitialConstants();
			err = compileObject(program, code, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new ErrorDialog(ide.getParent(),"Error!", e);
			err=true;
		}
		
		//sets the program on the robot, which will then run
		//when the robot is run
		if ((!err)&&(program!=null)){
			program.loadIntoRobot();
		}
		return err;
	}
	
	public boolean compileSpinObject(String name, File file, SpinObject object) throws Exception{
		String code = Compiler.getContents(file);
		//the code that is compiled is loaded onto the SpinObject
		return compileObject(object, code, name);
	}

	public boolean compileObject(Program program,String code, String name) throws Exception {
		StringTokenizer methodToken = new StringTokenizer(code.toString(),"\n");
		
		if (program.getMemory() == null) Debug.debug("mem is null","wtf");
		
		/*
		 * A preprocessor that loads in the method signatures, so that 
		 * when they are referenced the compiler knows what is being 
		 * referenced
		 */
		methodHandler = new MethodHandler(program);
		//this method actually calls the above object
		extractMethods(methodToken, program);
		
		/*
		 * This strips the single line comments, as they sometimes cause
		 * issues, in particular for the tab preprocessor
		 */
		CommentPreProcessor cpp = new CommentPreProcessor();
		code = cpp.process(code);
		
		/*
		 * Outputs to a file called "out.txt"
		 * which we read into the ANTLR charstream.
		 * This inserts indent and dedent tokens where appropriate,
		 * based on SPIN's method of indented code blocks. 
		 * It was much easier to separate it and use tokens rather
		 * than trying to handle all the indentation stuff with ANTLR
		 */
		TabPreprocessor pp = new TabPreprocessor();
		pp.process(code);
		//Debug.debug(Compiler.getContents(new File("out.txt")),"out.txt");
		
		/*
		 * These classes are all based on the ANTLR specification.
		 * Feed the stream into a lexer, the lexer generates a token
		 * stream, which we feed into the parser. The parser outputs
		 * an AST tree which we give to a TreeWalker, which takes
		 * a program (with method sigs loaded) as input, and sends
		 * the same program back as output, with the compiled "code"
		 * loaded and ready to go
		 */
		boolean err = false;
		ArrayList<Exception> pErrors;
		ArrayList<Exception> lErrors;
		String fs = System.getProperty("file.separator");
		CharStream charStream = new ANTLRFileStream("config"+fs+"out.txt");
		Spin2GrammarLexer lexer = new Spin2GrammarLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		lErrors = lexer.getErrors();
		if (lErrors.size()>0){
			Debug.debug("found errors","error");
			err = true;
			for (Exception e : lErrors){
				new ErrorDialog(ide.getParent(),"parser error",e);
			}
		}
		Spin2GrammarParser parser = new Spin2GrammarParser(tokenStream);
		
		prog_return prog = parser.prog(ide);
		pErrors = parser.getErrors();
		if (pErrors.size()>0){
			Debug.debug("found errors","error");
			err = true;
			for (Exception e : pErrors){
				new ErrorDialog(ide.getParent(),"parser error",e);
			}
		}
		//debugging printouts
		//System.out.println("Printing tree...");
		//System.out.println(((CommonTree)prog.getTree()).toStringTree());
		//Debug.debug(((CommonTree)prog.getTree()).toStringTree(),"tree");
		
		//A TreeWalker that will compile the "code" into the program
		CommonTreeNodeStream nodeStream = new CommonTreeNodeStream((CommonTree)prog.getTree());
		ProgWalker progWalker = new ProgWalker(nodeStream);
		program= progWalker.prog(program, message, workingDirectory, ide);

		//more debugging to ensure all variables loaded properly
		//program.printVariables();
		//outputs the compiled code to wherever the debugger is 
		//outputting
		program.printAllStatements();
		
		//set a message on the ide, so the user knows what is
		//going on
		if(!err){message.setText("compiled "+name);	}
		return err;
		
	}
	
	
	public void extractMethods(StringTokenizer methodToken, Program program) throws Exception {
		String token;
		SpinTokenizer spinToken;
		while (methodToken.hasMoreTokens()){
			token = methodToken.nextToken().toLowerCase();
			if (token.startsWith(PUB)||token.startsWith(PRI)){
				spinToken = new SpinTokenizer(token);
				program.addMethod(methodHandler.readMethods(spinToken, program));
			}
		}
	}
	
	static public String getContents(File aFile) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return contents.toString();
	  }
	
	
}

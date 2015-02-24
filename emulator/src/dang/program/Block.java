/**
 * 
 */
package dang.program;

import java.util.ArrayList;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.statements.Return;
import dang.program.statements.Statement;
import dang.robot.Robot;

/**
 * @author darrylhill
 * A block of statements, such as you would see inside a method, or 
 * a collection of statements indented signifying they are part of
 * a repeat loop, that sort of thing. Any grouping of statements in
 * a repetition statement, or under if then else
 */
public class Block {
	
	Method method;
	
	public Block(Method method){
		this.method = method;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	private ArrayList<Statement> statements = new ArrayList<Statement>();

	public ArrayList<Statement> getStatements() {
		return statements;
	}

	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}

	public void addStatement(Statement s){
		statements.add(s);
	}
	
	public Statement getLastStatement(){
		return statements.get(statements.size()-1);
	}

	
	public int execute(MethodInstance method) throws Exception {
		/*
		 * I am sure there is a special level of hell for anyone who is so 
		 * careless as to need to exit a program via an exception. See you there.
		 * But it covers all cases, a methodology already up and running. Why should
		 * I reinvent the wheel.
		 */
		
		//this thread needs to be slowed down to allow the other threads to run
		//smooth
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check if the robot is running
		if (!method.getRobot().isRunning()){
			throw new RobotNotRunningException();
		}
		if (statements != null){
			for (Statement s: statements){
				//TODO implement quit and next for looping constructs
				//not sure how exactly...
				//could throw an exception, but that is a little wasteful
				Debug.debug("****************************statement: "+s);
				s.execute(method);
				if (s instanceof Return){
					break;
				}
			}
			return 1;
		}else{
			return 0;
		}

	}


}

package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.expressions.Expression;

/**
 * 
 * @author darrylhill
 * Technically I should be subclassing Repeat (or that is one school of 
 * thought I am sure), but there isn't much to be gained and this way all the code is
 * explicit. This thing is still in its infancy and there is a lot of details
 * to lose track of, so I like that everything is in front of me
 */
public class RepeatVariableFrom extends Statement {
	
	private Block block;
	private String variable;
	private Expression start, end, step;
	private int tab;
	
	
	public Expression getStep() {
		return step;
	}

	public void setStep(Expression step) {
		this.step = step;
	}

	public Expression getStart() {
		return start;
	}

	public void setStart(Expression start) {
		this.start = start;
	}

	public Expression getEnd() {
		return end;
	}

	public void setEnd(Expression end) {
		this.end = end;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	@Override
	public void setTab(int tab) {
		this.tab = tab;
	}

	@Override
	public int getTab() {
		return tab;
	}

	
	/**
	 * It would be nice to check if its a valid variable,
	 * unfortunately in the current implementation that isn't
	 * possible, so the variable will need to be checked on 
	 * being generated (ie compiled) which is probably better
	 * anyway.
	 * @param start
	 * @param end
	 * @param variable
	 */
	public RepeatVariableFrom(Expression start, Expression end, String variable, Expression step,
			Method method){
		//TODO I think I need to test this with, say count from 10 to 0 and a 
		//positive step to see if it automatically adjusts or if you are screwed
		//The assumption I make is you are screwed. Hell, maybe java throws an 
		//exception
		super(method);
		block = new Block(method);
		this.variable = variable;
		this.start = start;
		this.end = end;
		this.step = step;
	}
	
	public RepeatVariableFrom(Expression start, Expression end, String variable, 
			Expression step, Block block, Method method){
		
		super(method);
		this.block = block;
		this.variable = variable;
		this.start = start;
		this.end = end;
		this.step = step;
	}
	
public RepeatVariableFrom(Expression start, Expression end, String variable, Method method){
		
		super(method);
		block = new Block(method);
		this.variable = variable;
		this.start = start;
		this.end = end;
	}
	
	public RepeatVariableFrom(Expression start, Expression end, String variable,Block block,
			Method method){
		
		super(method);
		this.block = block;
		this.variable = variable;
		this.start = start;
		this.end = end;
		
	}

	
	@Override
	public int execute(MethodInstance method) throws Exception {
		int startValue = start.evaluateToValue(method);
		int endValue = end.evaluateToValue(method);
		int stepValue;
		Variable v = method.getVariable(variable);
		if (step == null){
			if (startValue>endValue){
				stepValue = -1;
			}else{
				stepValue = 1;
			}
		}else{
			stepValue = step.evaluateToValue(method);
			Debug.debug("stepvalue: "+stepValue);
		}
		if (startValue>endValue){
			for (int i = startValue;i > endValue; i += stepValue ){
				v.setValue(i, method);
				for (Statement s: block.getStatements()){
					s.execute(method);
				}
			}
		}else{
			for (int i = startValue;i < endValue; i += stepValue ){
				v.setValue(i, method);
				for (Statement s: block.getStatements()){
					s.execute(method);
				}
			}
		}
		return 1;
	}

}

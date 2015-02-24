package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.program.AnonVariable;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.expressions.Expression;
import dang.program.expressions.VariableExpression;

/**
 * In retrospect this could have been included in SpinExpression with the
 * rest of the assignment statements.
 * @author darrylhill
 *
 */
public class Assignment extends Statement implements Expression {
	
	Expression variable;
	Expression value;

	//watch as this method returns an expression
	public Expression getVariable() {
		return variable;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	public Assignment(Expression variable, Expression value, Method method) {
		super(method);
		this.variable = variable;
		this.value = value;
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		Variable v = variable.evaluate(method);
		if (variable instanceof VariableExpression){
			Debug.debug("variable expression");
		}
		if (method == null){
			Debug.debug("Null method");
		}
		if (value == null){
			Debug.debug("Null value");
		}
		if (v == null){
			Debug.debug("Null variable");
		}
		Debug.debug(method.getLocalVariables().toString());
		v.setValue(value.evaluateToValue(method), method);
		return v.evaluateToValue(method);
	}

	@Override
	public void setBlock(Block block) throws CompilerError {
		throw new CompilerError("no block in assignment statment");
	}

	@Override
	public void setTab(int tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTab() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Block getBlock() throws SpinError {
		//throw new CompilerError("blocks are not stored in assignments");	
		return null;
	}

	@Override
	public String toString(){
		return "Assignment statement: "+variable +":="+value;
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		return new AnonVariable(execute(context));
	}

	@Override
	public int evaluateToValue(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		return execute(context);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return false;
	}

}

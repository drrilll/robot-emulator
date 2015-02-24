package dang.program.expressions;

import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.statements.Statement;

public class RandomExpression extends Statement implements Expression {
	Expression variable;

	public RandomExpression(Expression variable, Method method) {
		super(method);
		this.variable = variable;
	}

	public Expression getVariable() {
		return variable;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		Variable v = variable.evaluate(context);
		int rand = (int) (Math.random()*Integer.MAX_VALUE);
		v.setValue(rand, context);
		return v;
	}

	@Override
	public int evaluateToValue(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		if (variable == null)Debug.debug("variable is null");
		Variable v = variable.evaluate(context);
		int rand = (int) (Math.random()*Integer.MAX_VALUE);
		v.setValue(rand, context);
		return rand;
	}
	
	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int execute(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		return evaluateToValue(context);
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getBlock() throws SpinError {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		return "RandomExpression on: "+ variable;
	}

}

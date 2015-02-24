package dang.program.expressions;

import dang.program.MethodInstance;
import dang.program.Variable;

/**
 * When the variable is local, we can't have an instance of it
 * until runtime. So we use this, which retrieves it upon execution.
 * I think it even works with global variables. Probably should
 * use this exclusively, but right now I don't
 * @author darrylhill
 *
 */
public class VariableExpression implements Expression {
	
	String varName;
	Expression offset;
	
	public VariableExpression(String varName){
		this.varName = varName;
	}
	
	public VariableExpression(String varName, Expression offset){
		this.varName = varName;
		this.offset = offset;
	}
	
	public void setOffset(Expression offset){
		this.offset = offset;
	}

	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		// TODO Auto-generated method stub
		
		return method.getVariable(varName);
		
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		// TODO Auto-generated method stub
		return evaluate(method).evaluateToValue(method);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override 
	public String toString(){
		return "Variable (Expression): " + varName;
	}

}

package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.expressions.Expression;

/**
 * AKA a pointer variable.
 * I can't recall if this should stay a anon variable 
 * or if it needs its own address. Should be OK like this.
 * @author darrylhill
 *
 */
public class Address extends AnonVariable {
	
	private Variable exp;
	private Expression var;
	private boolean local = false;

	public Address(Variable exp, Memory memory) {
		super(VariableType.LONG, 0);
		setMemory(memory);
		this.exp = exp;
		// TODO Auto-generated constructor stub
	}
	
	public Address(Expression var, Memory memory){
		super (VariableType.LONG,0);
		setMemory(memory);
		local = true;
		this.var = var;
	}
	
	@Override
	public int getValue() throws CompilerError{
		throw new CompilerError("trying to access getValue in Address");
	}
	
	@Override
	public Variable evaluate(MethodInstance method)  {
		return this;
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception  {
		if (local){
			exp = var.evaluate(method);
		}
		Debug.debug(exp.toString());
		//Variable v = exp.evaluate(method);
		if (exp == null){
			Debug.debug("Variable is null");
		}
		return exp.getLocation(method);
	}
	
	@Override
	public String toString(){
		if (local){
			return "Address of local: "+var;
		}
		return "Address of "+exp+" at: "+ exp.location;
	}

}

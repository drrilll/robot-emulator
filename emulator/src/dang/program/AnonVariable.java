package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.expressions.Expression;

/**
 * A variable, or just a holder for a number, when we require
 * a value but don't need to put it in memory (ie return values,
 * which if not assigned disappear).
 * 
 * @author darrylhill
 *
 */
public class AnonVariable extends Variable {
	
	int value;
	Expression expValue;
	boolean isExpression;
	public boolean isfloat = false;
	
	public AnonVariable(VariableType type, int value){
		super ("anonymous", type, null);
		this.value = value;
	}
	public AnonVariable(int value){
		super("anonymous", VariableType.LONG, null);
		this.value = value;
	}
	public AnonVariable(int value, boolean isfloat){
		this(value);
		this.isfloat = isfloat;
	}
	
	@Override
	public int getLocation(MethodInstance method) throws CompilerError{
		throw new CompilerError("anonymous variables have no location");
	}
	
	@Override 
	public void setValue(int value,MethodInstance method) throws Exception {
		Debug.debug("setting anonymous variable value");
		this.value = value;
	}
	
	@Override
	public int getValue() throws CompilerError{
		return value;
	}
	
	@Override 
	public int evaluateToValue(MethodInstance method) throws Exception {
		return value;
	}
	
	@Override
	public Variable increment(MethodInstance method){
		value ++;
		return this;
	}
	
	@Override
	public Variable decrement(MethodInstance method){
		value --;
		return this;
	}
	
	@Override
	public String toString(){
		return new String("Variable "+getName()+" value: "+value);
	}
}

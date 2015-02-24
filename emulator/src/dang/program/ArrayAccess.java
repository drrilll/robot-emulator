package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.expressions.Expression;

/**
 * I think this can only occur on an actual variable. I can't
 * think of any other place it may occur
 * @author darrylhill
 *
 */
public class ArrayAccess extends Variable {
	
	private Variable variable;
	private Expression exp;
	private String var;
	private boolean local = false;
	
	/*
	 * Variable constructor, for global variables
	 */
	public ArrayAccess(Variable variable, Expression exp, Memory memory){
		super(variable.getName(), variable.getType(), memory);
		this.variable = variable;
		this.exp = exp;
	}
	
	/**
	 * Expression constructor, for local variables. Because we don't have access to 
	 * local variables until runtime, we need to store the variable as a variabe 
	 * expression, and set the local flag
	 */
	public ArrayAccess(String var, Expression exp){
		super (var, VariableType.LONG, null);
		this.var = var;
		this.exp = exp;
		local = true;
	}

	private void setupLocalVariable(MethodInstance method) throws SpinError{
		//have to set things up first for local variables
		variable = method.getVariable(var);
		setMemory(method.getMemory());
	}
	
	@Override
	public void setValue(int value, MethodInstance method) throws Exception {
		if (local){
			setupLocalVariable(method);
		}

		int varPointer =  variable.getLocation(method);

		//evaluate the expression within the [] brackets to get the offset
		int offset = exp.evaluateToValue(method);
		switch(variable.getType()){
		case BYTE:
			//bytes are 1, so the offset is fine
			getMemory().position(varPointer+offset);
			Debug.debug("putting byte value" + value);
			Debug.debug("at position: "+ getMemory().position());
			Debug.debug("adjusted byte value" + (byte)value);
			getMemory().put((byte)value);
			break;
		case WORD:
			//words are 2 bytes
			offset *= 2;
			getMemory().position(varPointer+offset);
			getMemory().putShort((short)value);
			break;
		case LONG:
			//longs are 4 bytes
			offset *=4;
			getMemory().position(varPointer+offset);
			getMemory().putInt(value);
			break;
		}
	}


	@Override
	public int getLocation(MethodInstance method) throws Exception{
		if (local){
			setupLocalVariable(method);
		}
		int initlocation = variable.getLocation(method);
		int modifier = 0;
		Debug.debug("ArrayAccess getLocation");
		switch(variable.getType()){
		case WORD:
			modifier = 2;
			break;
		case LONG:
			modifier = 4;
			break;
		default:
			modifier =1;
		}
		return initlocation +(exp.evaluateToValue(method)*modifier);
	}
	
	
	@Override
	public Variable evaluate(MethodInstance method) throws SpinError {
		if (local){
			setupLocalVariable(method);
		}
		return this;
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception{
		if (local){
			setupLocalVariable(method);
		}
		/*
		 * OK, so we take the variable, take the location, take the 
		 * offset multiplied by the type. Go into memory, grab the value.
		 * That is what we return. Easy peasy
		 */
		
		//evaluate the expression to get the variable value, 
		//which is a pointer into memory (presumably)
		//int varPointer = variable.evaluateToValue(method);
		int varPointer = variable.getLocation(method);
		
		//evaluate the expression within the [] brackets to get the offset
		int offset = exp.evaluateToValue(method);
		Debug.debug("Array accessing offset "+offset);
		switch(variable.getType()){
		case BYTE:
			//bytes are 1, so the offset is fine
			Debug.debug("At location "+(varPointer+offset));
			getMemory().position(varPointer+offset);
			return getMemory().get()&0xFF;
		case WORD:
			//words are 2 bytes
			offset *= 2;
			Debug.debug("At location "+(varPointer+offset));
			getMemory().position(varPointer+offset);
			return getMemory().getShort()&0xFFFF;
		case LONG:
			//longs are 4 bytes
			offset *=4;
			Debug.debug("At location "+(varPointer+offset));
			getMemory().position(varPointer+offset);
			return getMemory().getInt();
		}
		return 0;
	}
	
	@Override
	public int getValue() throws CompilerError{
		throw new CompilerError("Cannot getValue from array access: "+ variable.getName());
	}
	
	@Override
	public String toString(){
		if(local){
			return "ArrayAccess: "+var;
		}
		return "ArrayAccess: "+variable;
	}

}

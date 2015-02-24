package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;

public class Constant extends Variable {
	
	int value;
	
	public Constant(String name, int value, Memory memory){
		//I won't check for an int[] of length greater
		//than 1. You can set it, but it will only return the 
		//first value
		super (name, VariableType.LONG, memory);
		this.value = value;
	}
	
	//set the initial value for the constant, which then never changes
	public void setInitialValue() throws SpinError{

		getMemory().position(location);
		getMemory().putInt((int)value);		

	}
	
	@Override
	//can't change a constant
	public void setValue(int value, MethodInstance method) throws SyntaxError{
		//throw new ConstantAssignmentException();
		throw new SyntaxError("Constant assignment exception: "+getName());

	}

	public String toString(MethodInstance method) {
		StringBuffer s = null;
		try {
			s = new StringBuffer("Constant "+getName()+" value: "+getValue());
		} catch (CompilerError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s.toString();
	}

}

package dang.program.expressions;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import dang.exceptions.SpinError;
import dang.program.AnonVariable;
import dang.program.Block;
import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;

/**
 * The spin statement strsize
 * @author darrylhill
 *
 */
public class StrSize implements Expression {
	Expression exp;
	Memory memory;
	
	public StrSize(Expression exp, Memory memory){
		this.exp = exp;
		this.memory = memory;
	}

	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		return new AnonVariable(evaluateToValue(method));
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		int address = exp.evaluateToValue(method);
		
		int count = 0;
		byte ch = 0;
		//start at this address and read bytes until a null character is read
		do {
			ch = memory.get(address);
			count ++;
			address ++;
		}while (ch!=0);
		//return count -1 because the terminating character was counted
		return count -1;
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}

	

}

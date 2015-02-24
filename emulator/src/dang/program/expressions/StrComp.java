package dang.program.expressions;

import java.nio.ByteBuffer;

import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;

/**
 * The Spin statement strcomp
 * @author darrylhill
 *
 */
public class StrComp implements Expression {
	
	Expression ex1, ex2;
	Memory memory;
	
	public StrComp(Expression ex1, Expression ex2, Memory memory){
		this.ex1 = ex1;
		this.ex2 = ex2;
		this.memory = memory;
	}

	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		
		int str1address = ex1.evaluateToValue(method);
		int str2address = ex2.evaluateToValue(method);
		byte st1 = 0, st2 = 0;
		do{
			st1 = memory.get(str1address);
			st2 = memory.get(str2address);
			if (st1 != st2){
				return Variable.FALSE;
			}
			str1address ++;
			str2address ++;
			//if they are equal they must reach the terminal character
			//at the same time, so we only need to check 1
		}while (st1 !=0 );
		return Variable.TRUE;
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		//evaluate returns true or false, which evaluated for value
		//return -1 and 0 respectively
		return evaluate(method).evaluateToValue(method);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}
}

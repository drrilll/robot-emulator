package dang.program.statements;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

public class RepeatWhile extends Repeat {
	
	public RepeatWhile(Expression exp, Method method){
		super(exp, method);
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		//presumably there is some variable in the expression
		//that will eventually get it out of the loop.
		//Ultimately its not my problem
		while (getExp().evaluate(method)!=Expression.FALSE){
			getBlock().execute(method);
		}
		return 1;
	}

}

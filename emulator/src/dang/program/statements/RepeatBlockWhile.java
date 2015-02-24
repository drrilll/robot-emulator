package dang.program.statements;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

public class RepeatBlockWhile extends Repeat {

	public RepeatBlockWhile(Expression exp, Method method) {
		super(exp, method);
		
	}
	

	@Override
	public int execute(MethodInstance method) throws Exception {
		//presumably there is some variable in the expression
		//that will eventually get it out of the loop.
		//Ultimately its not my problem
		do {
			getBlock().execute(method);
		}while (getExp().evaluate(method)!=Expression.FALSE);
		return 1;
	}

}
